package com.recruitmentSolution.Recruitment_Solution;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.load();

    public static String getOpenApiKey(){
        return dotenv.get("openAPI");
    }
}
