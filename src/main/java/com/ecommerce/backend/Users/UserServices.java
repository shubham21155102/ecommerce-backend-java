package com.ecommerce.backend.Users;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private final UserRepository userRepository;
    public UserServices(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    //create
    public UserModel createUser(UserModel user){
        UserModel presentUser=userRepository.findByEmail(user.getEmail());
        return Objects.requireNonNullElseGet(presentUser,()->userRepository.save(user));
    }
    //read
    public UserModel getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    //getAll users
    public Iterable<UserModel> getAllUsers(){
        return userRepository.findAll();
    }
    
}
