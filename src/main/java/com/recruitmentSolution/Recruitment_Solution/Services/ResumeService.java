package com.recruitmentSolution.Recruitment_Solution.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitmentSolution.Recruitment_Solution.Config;
import com.recruitmentSolution.Recruitment_Solution.Models.ResumeData;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.*;

import java.io.File;
import java.io.FileOutputStream;

import java.util.HashMap;
import java.util.Map;

@Service

public class ResumeService {

    //@Value("${openai.api.key}")
    private String openaiApiKey;

    private RestTemplate restTemplate = new RestTemplate();



    public Object processResume(MultipartFile file) throws Exception{

        openaiApiKey = Config.getOpenApiKey();
        // Save the uploaded file to a temporary file.
        File tempFile = File.createTempFile("resume", ".pdf");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }

        // Extract text from the PDF.
        String extractedText = extractTextFromPDF(tempFile);
        tempFile.delete();  // Delete temp file

        System.out.println("Calling open APIs");

        // Call ChatGPT (OpenAI) API to parse the resume.
        String structuredResponse = callOpenAiApi(extractedText);

        String finalResponse = callOpenApiForBreakdownString(structuredResponse);

        // Convert the JSON response into a Java object.
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(finalResponse);
        System.out.println("Final output from open api: "+finalResponse);
        // Extract the assistant's message content.
        String content = jsonNode.path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();

        // Clean the markdown formatting (remove ```json and ```)
        String cleanedContent = content.replace("```json", "")
                .replace("```", "")
                .trim();

        System.out.println("Clened content: "+cleanedContent);

        // Parse the cleaned JSON into our ResumeData object.
        ResumeData resumeData = mapper.readValue(cleanedContent, ResumeData.class);
        return cleanedContent;

    }

    private String extractTextFromPDF(File pdfFile) throws Exception {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }


    private String callOpenAiApi(String resumeText) throws Exception {
        String url = "https://api.openai.com/v1/chat/completions";

        // Prepare the messages for ChatGPT.
        Map<String, Object> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are an expert resume parser. Extract and return structured JSON data with the following fields: Full Name, Email, Phone, Work Experience, Education, and Skills.");

        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", "Extract structured data from the following resume text and output in the JSON format described above:\n" + resumeText);


        // Assemble the API request body.
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4o");
        requestBody.put("messages", new Object[]{systemMessage, userMessage});
        requestBody.put("temperature", 0);

        // Prepare HTTP headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        HttpEntity<Map<String, Object>> request  = new HttpEntity<>(requestBody, headers);

        //Make post request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            return response.getBody();
        }else {
            throw new Exception("Error calling openai api: "+ response.getStatusCode());
        }
    }

    private String callOpenApiForBreakdownString(String resumeJson) throws Exception{

        String url = "https://api.openai.com/v1/chat/completions";


        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", "Extract structured data (only the necessary data that should needed from resume ex: personal info, experiences, education, skills, projects etc.) from the following data text and output in the JSON format that can easily read and extract from a javascript frontend to render:\n"+ resumeJson);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4o");
        requestBody.put("messages", new Object[]{ userMessage});
        requestBody.put("temperature", 0);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        HttpEntity<Map<String, Object>> request  = new HttpEntity<>(requestBody, headers);

        //Make post request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            return response.getBody();
        }else {
            throw new Exception("Error calling openai api: "+ response.getStatusCode());
        }


    }




}
