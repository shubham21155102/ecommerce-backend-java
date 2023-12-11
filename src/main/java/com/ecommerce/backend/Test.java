package com.ecommerce.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {
    @GetMapping("/")
    public String test(){
        return "test";
    }
    @GetMapping("/login")
    public String login(){
        return "LogIn";
    }
    @GetMapping("/register")
    public String register(){
        return "SignUp";
    }

}
