package com.ecommerce.backend.services;

import com.ecommerce.backend.models.UserModel;
import com.ecommerce.backend.repositories.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices implements UserServiceInterface{
    private final UserRepository userRepository;
    private final Gson gson;
    @Autowired
    public UserServices(UserRepository userRepository,Gson gson) {
        this.userRepository = userRepository;
        this.gson = gson;
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
    @Override
    public String getAllUsers(){
        Iterable<UserModel> users = userRepository.findAll();
        return gson.toJson(users);

//        StringBuilder sb = new StringBuilder();
//        sb.append("[");
//        for (UserModel user : users) {
//            sb.append("{\"id\": \"" + user.getId() + "\",");
//            sb.append("\"username\": \"" + user.getUsername() + "\",");
//            sb.append("\"email\": \"" + user.getEmail() + "\",");
//            sb.append("\"fname\": \"" + user.getFirstName() + "\",");
//            sb.append("\"lname\": \"" + user.getLastName() + "\",");
//            sb.append("\"phone\": \"" + user.getPhone() + "\"},");
//        }
//        sb.deleteCharAt(sb.length() - 1);
//        sb.append("]");
//        //Change this to return a JSON object
//        return sb.toString();
    }

}
