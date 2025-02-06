package com.recruitmentSolution.Recruitment_Solution.Repositories;

import com.recruitmentSolution.Recruitment_Solution.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    Optional<User> findByEmail(String email); // Find user by email
    boolean existsByEmail(String email); // Check if email exists
}
