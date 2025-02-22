package com.recruitmentSolution.Recruitment_Solution.Controllers;

import com.recruitmentSolution.Recruitment_Solution.Services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api/resume")

public class Resumecontroller {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/uploadResume")
    public ResponseEntity<?> uploadResume(@RequestParam("file")MultipartFile file){

        try{
            // Process the resume file and extract structured data.
            Object resumeData = resumeService.processResume(file);
            System.out.println("Resume data: "+ resumeData);
            return ResponseEntity.ok(resumeData);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing resume");
        }
    }
}
