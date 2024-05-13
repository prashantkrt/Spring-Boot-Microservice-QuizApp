package com.mylearning.microservice.springbootmicroservicequizservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
    private String questionId;
    private String answer;
}
