package com.recruitmentSolution.Recruitment_Solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestingClass {

    @Autowired
    private TestingClass2 testingClass2;

    /*You can't leave creating just the class reference and call the methods of that class
    like this:
        private TestingClass2 testingClass2
        testingClass2.print();

     It will give null error. instead you should create the constructor or use the @Autowired
     keyword.

     public TestingClass2(TestingClass2 testingClassTwo){
            this.testingClass2 = testingClassTwo;

       }
       or

       @Autowired
       private TestingClass2 testingClass2;
     */


    /*
    TestingClassInterface is the interface and TestingClass2 implements the interface.
    I use Autowired like this:
    @Autowired
    Private TestingClassInterface tes;

    tes.print();

    This will print "Printing from Testing class 2" because TestingClass2 is a type of TestingClassInterface.

    **If there more than 1 class implements from same interface, the class u want to invoke should have the @Primary keyword.

    or

    Use @Qualifier when you need to specify explicitly.

    @Qualifier("testingClass2")  // Use the bean name to specify
    private TestingClassInterface test;


     */

    @Autowired
    private TestingClassInterface test;




    public void helloFunction(){

        testingClass2.print();
        System.out.println("Testing class printing...");
        test.print();
    }
}
