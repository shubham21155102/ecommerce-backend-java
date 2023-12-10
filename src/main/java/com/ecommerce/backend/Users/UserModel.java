package com.ecommerce.backend.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "campus_id", unique = true)
    private String campusID;

    @Column(name = "hostel")
    private String hostel;

    @Column(name = "phone")
    private String phone;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getCampusID(){
        return campusID;
    }
    public void setCampusID(String campusID){
        this.campusID=campusID;
    }
    public String getHostel(){
        return hostel;
    }
    public void setHostel(String hostel){
        this.hostel=hostel;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
