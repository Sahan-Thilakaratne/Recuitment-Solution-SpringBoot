package com.recruitmentSolution.Recruitment_Solution.Services;


import com.recruitmentSolution.Recruitment_Solution.Models.User;
import com.recruitmentSolution.Recruitment_Solution.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already in use!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public boolean authenticateUser(String email, String rawPassword){

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isPresent()){
            User user = userOptional.get();

            return passwordEncoder.matches(rawPassword, user.getPassword());
        }

        return false;
    }
}
