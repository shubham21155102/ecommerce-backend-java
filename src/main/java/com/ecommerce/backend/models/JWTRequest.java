package com.ecommerce.backend.models;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class JWTRequest{
    private String username;
    private String password;
}
