package com.ecommerce.backend.repositories;

import com.ecommerce.backend.models.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findByUsername(String username);

    UserModel findByEmail(String email);
}
//public class UserRepository implements CrudRepository<UserModel, Long> {
//
//    @Override
//    public <S extends UserModel> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends UserModel> Iterable<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<UserModel> findById(Long aLong) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return false;
//    }
//
//    @Override
//    public Iterable<UserModel> findAll() {
//        return null;
//    }
//
//    @Override
//    public Iterable<UserModel> findAllById(Iterable<Long> longs) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//
//    }
//
//    @Override
//    public void delete(UserModel entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Long> longs) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends UserModel> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//}
