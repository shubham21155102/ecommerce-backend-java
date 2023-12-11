package com.ecommerce.backend;

import com.ecommerce.backend.models.UserModel;
import com.ecommerce.backend.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RestTest {
    private final UserRepository userRepository;

    public RestTest(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public String login(@RequestParam String username, @RequestParam String password) {

        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);


        return username + " " + password;
    }
    @PostMapping("/register")
    public String register(
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


        if (!password.equals(cpassword))
            return "Password and Confirm Password do not match";
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setPhone(phone);
        // Check if the email already exists
        Optional<UserModel> existingUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (existingUser.isPresent()) {
            return "Email already exists";
        }
        userRepository.save(user);


        return "Registration successful for: " + username;
    }
}
