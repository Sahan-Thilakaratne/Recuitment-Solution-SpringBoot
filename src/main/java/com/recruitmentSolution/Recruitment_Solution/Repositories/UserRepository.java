package com.recruitmentSolution.Recruitment_Solution.Repositories;

import com.recruitmentSolution.Recruitment_Solution.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    User findByEmail(String email);
}
