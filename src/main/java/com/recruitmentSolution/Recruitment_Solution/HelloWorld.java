package com.recruitmentSolution.Recruitment_Solution;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorld {

    @RequestMapping("/")
    public String greet(){
        return "Hello Sahan";
    }

    @RequestMapping("/ado")
    public Map<String, String> greett() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello Sahan");
        return response;
    }
}
