package com.recruitmentSolution.Recruitment_Solution.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResumeData {

    @JsonProperty("Full Name")
    private String fullName;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("Work Experience")
    private List<WorkExperience> workExperience;

    @JsonProperty("Education")
    private List<Education> education;

    @JsonProperty("Skills")
    private List<String> skills;

    // Getters and setters
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public List<WorkExperience> getWorkExperience() {
        return workExperience;
    }
    public void setWorkExperience(List<WorkExperience> workExperience) {
        this.workExperience = workExperience;
    }
    public List<Education> getEducation() {
        return education;
    }
    public void setEducation(List<Education> education) {
        this.education = education;
    }
    public List<String> getSkills() {
        return skills;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WorkExperience {
        @JsonProperty("Position")
        private String position;

        @JsonProperty("Company")
        private String company;

        @JsonProperty("Location")
        private String location;

        @JsonProperty("Duration")
        private String duration;

        @JsonProperty("Responsibilities")
        private List<String> responsibilities;

        // Getters and setters
        public String getPosition() {
            return position;
        }
        public void setPosition(String position) {
            this.position = position;
        }
        public String getCompany() {
            return company;
        }
        public void setCompany(String company) {
            this.company = company;
        }
        public String getLocation() {
            return location;
        }
        public void setLocation(String location) {
            this.location = location;
        }
        public String getDuration() {
            return duration;
        }
        public void setDuration(String duration) {
            this.duration = duration;
        }
        public List<String> getResponsibilities() {
            return responsibilities;
        }
        public void setResponsibilities(List<String> responsibilities) {
            this.responsibilities = responsibilities;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Education {
        @JsonProperty("Degree")
        private String degree;

        @JsonProperty("Institution")
        private String institution;

        @JsonProperty("Duration")
        private String duration;

        @JsonProperty("School")
        private String school;

        @JsonProperty("Qualification")
        private String qualification;

        @JsonProperty("Year")
        private String year;

        @JsonProperty("Subjects")
        private Map<String, String> subjects;

        // If you expect a "Location" field in education and want to capture it, uncomment below:
        // @JsonProperty("Location")
        // private String location;

        // Getters and setters
        public String getDegree() {
            return degree;
        }
        public void setDegree(String degree) {
            this.degree = degree;
        }
        public String getInstitution() {
            return institution;
        }
        public void setInstitution(String institution) {
            this.institution = institution;
        }
        public String getDuration() {
            return duration;
        }
        public void setDuration(String duration) {
            this.duration = duration;
        }
        public String getSchool() {
            return school;
        }
        public void setSchool(String school) {
            this.school = school;
        }
        public String getQualification() {
            return qualification;
        }
        public void setQualification(String qualification) {
            this.qualification = qualification;
        }
        public String getYear() {
            return year;
        }
        public void setYear(String year) {
            this.year = year;
        }
        public Map<String, String> getSubjects() {
            return subjects;
        }
        public void setSubjects(Map<String, String> subjects) {
            this.subjects = subjects;
        }
    }
}
