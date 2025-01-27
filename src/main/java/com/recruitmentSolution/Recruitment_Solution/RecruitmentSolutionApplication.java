package com.recruitmentSolution.Recruitment_Solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RecruitmentSolutionApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(RecruitmentSolutionApplication.class, args);

		TestingClass obj1 =  context.getBean(TestingClass.class);

		obj1.helloFunction();
	}

}
