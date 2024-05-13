package com.mylearning.microservice.springbootmicroservicequizservice.controller;

import com.mylearning.microservice.springbootmicroservicequizservice.model.QuestionWrapper;
import com.mylearning.microservice.springbootmicroservicequizservice.model.QuizDto;
import com.mylearning.microservice.springbootmicroservicequizservice.model.Response;
import com.mylearning.microservice.springbootmicroservicequizservice.service.QuizService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("createQuiz")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quiz) {
        return quizService.createQuiz(quiz);
    }

    @GetMapping("getQuiz/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer quizId) {
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response) {
        return quizService.calculateResult(id, response);
    }
}
