package com.mylearning.microservice.springbootmicroservicequestionservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QuestionModel {
    private String question;
    private String answer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String category;
}
