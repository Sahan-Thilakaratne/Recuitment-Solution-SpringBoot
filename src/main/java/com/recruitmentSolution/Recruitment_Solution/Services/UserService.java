package com.recruitmentSolution.Recruitment_Solution.Services;

import com.recruitmentSolution.Recruitment_Solution.Models.User;
import com.recruitmentSolution.Recruitment_Solution.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Get all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Add new user
    public User addUser(User user){
        return userRepository.save(user);
    }
}
