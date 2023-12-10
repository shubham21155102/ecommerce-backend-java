package com.ecommerce.backend.Users;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class AddressModel {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "streetAddress")
    private String streetAddress;
    @Column(name="city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zip_code")
    private String zipCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel User;
    public AddressModel(){

    }
}
