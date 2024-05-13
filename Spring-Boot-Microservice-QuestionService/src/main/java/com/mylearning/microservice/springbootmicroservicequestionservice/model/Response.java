package com.mylearning.microservice.springbootmicroservicequestionservice.model;

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
