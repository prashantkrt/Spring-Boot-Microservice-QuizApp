package com.mylearning.microservice.springbootmicroservicequizservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizDto {
    private String category;
    private Integer noOfQuestions;
    private String title;
}
