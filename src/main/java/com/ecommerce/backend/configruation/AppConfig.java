package com.ecommerce.backend.configruation;

import com.ecommerce.backend.models.UserModel;
import com.ecommerce.backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;

@Configuration
public class AppConfig {
    private final UserRepository userRepository;
    public AppConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Bean
    public UserDetailsService userDetailsService() {
        Iterable<UserModel> users = userRepository.findAll();
        Logger logger = LoggerFactory.getLogger(AuthController.class);
        ArrayList<UserDetails> userDetailsArrayList = new ArrayList<>();

        for (UserModel user : users) {
            logger.info("Username : {}", user.getUsername());
            logger.info("Password : {}", user.getPassword());
            UserDetails user1 = User.builder()
                    .username(user.getUsername())
                    .password(passwordEncoder().encode(user.getPassword()))
                    .roles("ADMIN")
                    .build();
            userDetailsArrayList.add(user1);
        }

        return new InMemoryUserDetailsManager(userDetailsArrayList);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
