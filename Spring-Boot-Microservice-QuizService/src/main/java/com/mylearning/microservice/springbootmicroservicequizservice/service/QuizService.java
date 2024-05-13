package com.mylearning.microservice.springbootmicroservicequizservice.service;

import com.mylearning.microservice.springbootmicroservicequizservice.model.QuestionWrapper;
import com.mylearning.microservice.springbootmicroservicequizservice.model.QuizDto;
import com.mylearning.microservice.springbootmicroservicequizservice.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {
    public ResponseEntity<String> createQuiz(QuizDto quiz);

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer quizId);

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response);
}
