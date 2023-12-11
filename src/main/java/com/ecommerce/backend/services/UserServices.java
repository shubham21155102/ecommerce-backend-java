package com.ecommerce.backend.services;

import com.ecommerce.backend.models.UserModel;
import com.ecommerce.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices implements UserServiceInterface{
    private final UserRepository userRepository;
    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public String registerUser(String username, String password, String email, String fname, String lname, String phone, String cpassword) {
        Optional<UserModel> existingUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (existingUser.isPresent()) {
            return "Email already exists";
        }
        if (!password.equals(cpassword)) {
            return "Password and Confirm Password do not match";
        }

        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setPhone(phone);

        userRepository.save(user);
        return "Registration successful for: " + username;
    }

}
