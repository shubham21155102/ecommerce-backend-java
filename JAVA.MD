## implements and extends IN JAVA
## 1. implements: class implements interface
## 2. extends: class extends class
# _implements:_
- When a class implements an interface,
- it means that the class promises to provide definitions (implementations) for all the methods declared in that interface.
- For example:
```java
  public interface CrudRepository<T, ID> {
    // method declarations
       }

public class UserRepository implements CrudRepository<UserModel, Long> {
// Class UserRepository provides implementations for the methods declared in CrudRepository
}
```
- In the above example, the class UserRepository implements the interface CrudRepository.
- This means that the class UserRepository provides implementations for all the methods declared in the interface CrudRepository.
- The class UserRepository is said to implement the interface CrudRepository.
# _extends:_
- When a class extends another class,
- it means that the class inherits all the methods and properties from the class it extends.
- For example:
```java
public class UserRepository extends CrudRepository<UserModel, Long> {
// Class UserRepository inherits all the methods and properties from the class CrudRepository
}
```
- In the above example, the class UserRepository extends the class CrudRepository.
- This means that the class UserRepository inherits all the methods and properties from the class CrudRepository.
- The class UserRepository is said to extend the class CrudRepository.
## Now first construct this 
```java
package com.ecommerce.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @Column(name = "id",nullable = false, updatable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username",unique = true,nullable = false)
    private String username;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email", nullable = false, updatable = false,unique = true)
    private String email;
    @Column(name="phone")
    private String phone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

```
## Now create a repository
```java
package com.ecommerce.backend.repositories;

import com.ecommerce.backend.models.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findByUsername(String username);

    UserModel findByEmail(String email);
}
```
## Now start routing process
```java
package com.ecommerce.backend;

import com.ecommerce.backend.models.UserModel;
import com.ecommerce.backend.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RestTest {
    private final UserRepository userRepository;

    public RestTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/t")
    public String Home(){
        return "test";
    }
    @GetMapping("/{id}")
    public String Home2(@PathVariable String id){

        return "{\"id\": \"" + id + "\"}";

    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);


        return username + " " + password;
    }
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String fname,
            @RequestParam String lname,
            @RequestParam String phone,
            @RequestParam String cpassword
    ) {
        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);
        System.out.println("Received email: " + email);
        System.out.println("Received fname: " + fname);
        System.out.println("Received lname: " + lname);
        System.out.println("Received phone: " + phone);


        if (!password.equals(cpassword))
            return "Password and Confirm Password do not match";
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setPhone(phone);
        // Check if the email already exists
        Optional<UserModel> existingUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (existingUser.isPresent()) {
            return "Email already exists";
        }
        userRepository.save(user);


        return "Registration successful for: " + username;
    }
}

```
## Now create a service for wrapping the Main java file that can be enlarged dut to previous code
```java
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

```
## Now Current Java file
```java
package com.ecommerce.backend;
import com.ecommerce.backend.services.UserServices;
import org.springframework.web.bind.annotation.*;



@RestController
public class RestTest {

private final UserServices userService;

    public RestTest(UserServices userService) {
        this.userService = userService;
    }

    @GetMapping("/t")
    public String Home(){
        return "test";
    }
    @GetMapping("/{id}")
    public String Home2(@PathVariable String id){

        return "{\"id\": \"" + id + "\"}";

    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);
        return username + " " + password;
    }
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String fname,
            @RequestParam String lname,
            @RequestParam String phone,
            @RequestParam String cpassword
    ) {

        return userService.registerUser(username, password, email, fname, lname, phone, cpassword);
    }
}

```