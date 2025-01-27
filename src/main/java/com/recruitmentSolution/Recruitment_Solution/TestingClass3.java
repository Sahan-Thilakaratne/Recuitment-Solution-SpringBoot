package com.recruitmentSolution.Recruitment_Solution;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class TestingClass3 implements TestingClassInterface {


    public void print(){
        System.out.println("Printing from Testing class 3");
    }
}
