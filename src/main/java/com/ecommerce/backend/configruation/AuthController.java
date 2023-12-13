package com.ecommerce.backend.configruation;

import com.ecommerce.backend.models.JWTRequest;
import com.ecommerce.backend.models.JWTResponse;
import com.ecommerce.backend.security.JwtHelper;
import com.ecommerce.backend.services.JWTResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JWTResponseService JWTResponseService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(com.ecommerce.backend.services.JWTResponseService jwtResponseService) {
        JWTResponseService = jwtResponseService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JWTRequest request) throws Exception {
       System.out.println("Login");

        logger.info("Username : {}", request.getUsername());
        logger.info("Password : {}", request.getPassword());
        try {
            manager.authenticate(new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials !!", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = helper.generateToken(userDetails);
        final String getAll = helper.getAllClaimsFromToken(token).toString();
        logger.info("Token : {}", token);
        final String uName=request.getUsername();
        JWTResponse db=new JWTResponse();
        db.setToken(token);
        db.setUname(uName);
       JWTResponseService.saveOrUpdateJWTResponse(db);

        return ResponseEntity.ok(new JWTResponse(token, uName, helper.getAllClaimsFromToken(token).get("id", Long.class)));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public HttpStatus exceptionHandler(){
        return HttpStatus.UNAUTHORIZED;
    }

}
