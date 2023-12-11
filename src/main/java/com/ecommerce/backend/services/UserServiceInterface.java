package com.ecommerce.backend.services;

public interface UserServiceInterface {
    String registerUser(String username, String password, String email, String fname, String lname, String phone, String cpassword);
}
