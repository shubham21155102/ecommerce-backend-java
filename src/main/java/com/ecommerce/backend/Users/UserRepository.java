package com.ecommerce.backend.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);
    UserModel findByCampusID(String campusID);
}
