package com.ecommerce.backend.repositories;

import com.ecommerce.backend.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findByUsername(String username);

    UserModel findByEmail(String email);
}
