package com.ecommerce.backend.models;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "jwt_response")
public class JWTResponse {
    @Column(name = "jwt_token")
    private String jwtToken;
    @Column(name = "username")
    private String username;
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;
    public JWTResponse(String token, String username, Long id) {
        this.jwtToken = token;
        this.username = username;
//        this.id = id;

    }


    public void setToken(String token) {
        this.jwtToken = token;
    }

    public void setUname(String uName) {
        this.username = uName;
    }
}
