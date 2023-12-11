package com.ecommerce.backend;
import com.ecommerce.backend.services.UserServices;
import org.springframework.web.bind.annotation.*;



@RestController
//@RequestMapping("/api/v1/users")
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
    @GetMapping("/users")
    public String getAllUsers() {
        return userService.getAllUsers();
    }
}
