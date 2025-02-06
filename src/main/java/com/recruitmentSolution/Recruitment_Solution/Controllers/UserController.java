package com.recruitmentSolution.Recruitment_Solution.Controllers;


import com.recruitmentSolution.Recruitment_Solution.Models.DTO.LoginRequest;
import com.recruitmentSolution.Recruitment_Solution.Models.User;
import com.recruitmentSolution.Recruitment_Solution.Services.AuthService;
import com.recruitmentSolution.Recruitment_Solution.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        boolean isAuthenticated = authService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated){
            return ResponseEntity.ok("Login Successful");
        }else{
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

}
