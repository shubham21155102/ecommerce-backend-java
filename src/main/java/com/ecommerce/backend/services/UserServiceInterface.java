package com.ecommerce.backend.services;

import com.ecommerce.backend.Utils.ResponseMessage;

public interface UserServiceInterface {
    ResponseMessage registerUser(String username, String password, String email, String fname, String lname, String phone, String cpassword);

    String getAllUsers();

    ResponseMessage logIn(String username, String password);

    ResponseMessage showAllUsers();
}
