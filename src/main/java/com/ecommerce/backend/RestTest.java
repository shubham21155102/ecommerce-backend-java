package com.ecommerce.backend;
import com.ecommerce.backend.Utils.ResponseMessage;
import com.ecommerce.backend.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = {"http://localhost:3000", "https://ecommerce.shubhamiitbhu.in"})
@RestController
//@RequestMapping("/api/v1/users")
public class RestTest {
private final UserServices userService;
    public RestTest(UserServices userService) {
        this.userService = userService;
    }
    @GetMapping("/t")
    public String Home(){
        return "test";
    }
    @GetMapping("/{id}")
    public String Home2(@PathVariable String id){
        return "{\"id\": \"" + id + "\"}";
    }
    @PostMapping("/login")
    public ResponseMessage login(@RequestParam String username, @RequestParam String password) {

        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);
        return userService.logIn(username, password);
    }
    @PostMapping("/register")
    public ResponseMessage register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String fname,
            @RequestParam String lname,
            @RequestParam String phone,
            @RequestParam String cpassword
    ) {
        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);
        System.out.println("Received email: " + email);
        System.out.println("Received fname: " + fname);
        System.out.println("Received lname: " + lname);
        System.out.println("Received phone: " + phone);
//        return new ResponseMessage("Registration successful for: " + username, HttpStatus.CREATED);
        return userService.registerUser(username, password, email, fname, lname, phone, cpassword);
    }
//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users")
    public ResponseMessage getAllUsers() {
        return userService.showAllUsers();
    }
    @GetMapping("current-use")
    public ResponseMessage getCurrentUser(Principal p){
        return new ResponseMessage(p.getName(),HttpStatus.OK);
    }

}
