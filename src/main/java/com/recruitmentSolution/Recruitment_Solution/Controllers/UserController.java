package com.recruitmentSolution.Recruitment_Solution.Controllers;


import com.recruitmentSolution.Recruitment_Solution.Models.DTO.LoginRequest;
import com.recruitmentSolution.Recruitment_Solution.Models.User;
import com.recruitmentSolution.Recruitment_Solution.Services.AuthService;
import com.recruitmentSolution.Recruitment_Solution.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    // Get all users
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // Add a new user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            System.out.println(user.getPassword());
            System.out.println(user.getAge());
            User saveduser = authService.registerUser(user);

            return ResponseEntity.ok("User registered successfully with email: "+ saveduser.getEmail());

        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){

        Optional<User> userOptional = authService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (userOptional.isPresent()){
            User user = userOptional.get();

            // Create a response map with the message and user details
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Login Successful");
            responseBody.put("fullname", user.getName()); // Assuming User has a getFullName() method
            responseBody.put("email", user.getEmail());

            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @PostMapping("/getUserDetails")
    public ResponseEntity<?> getUserDetails(@RequestBody User user){

        System.out.println("Incoming user data: "+ user.getEmail());

        Optional<User> userOptional = authService.getUserDetails(user.getEmail());

        if(userOptional.isPresent()){
            User user1 = userOptional.get();

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("fullname", user1.getName());

            responseBody.put("email", user1.getEmail());

            return ResponseEntity.ok(responseBody);
        } else {
            return  ResponseEntity.status(401).body("Invalid email");
        }
    }

}
