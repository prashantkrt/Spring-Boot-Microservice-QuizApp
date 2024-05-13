package com.mylearning.microservice.springbootmicroservicequestionservice.controller;

import com.mylearning.microservice.springbootmicroservicequestionservice.entity.Question;
import com.mylearning.microservice.springbootmicroservicequestionservice.model.QuestionModel;
import com.mylearning.microservice.springbootmicroservicequestionservice.model.QuestionWrapper;
import com.mylearning.microservice.springbootmicroservicequestionservice.model.Response;
import com.mylearning.microservice.springbootmicroservicequestionservice.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    Environment environment;

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionModel question) {
        String response = questionService.addQuestion(question);
        if (response == null) {
            return new ResponseEntity<>("Question already exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Added Successfully ", HttpStatus.OK);
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<Object> getALLQuestions() {
        List<Question> questions = (List<Question>) questionService.getAllQuestion();
        if (questions == null) {
            return new ResponseEntity<>("No questions found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/allQuestionsByCategory")
    public ResponseEntity<Object> getAllQuestionsByCategory(@RequestParam("category") String category) {
        List<Question> questions = questionService.findQuestionByCategory(category);
        return new ResponseEntity<>(Objects.requireNonNullElse(questions, "Couldn't find the required questions based on the category"), HttpStatus.OK);
    }

    @DeleteMapping("deleteQuestion")
    public ResponseEntity<String> deleteQuestion(@RequestParam Integer questionId) {
        String response = questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("updateQuestion")
    public ResponseEntity<String> updateQuestion(@RequestParam Integer questionId, @RequestParam("question") String question, @RequestParam("category") String category, @RequestParam String option1, @RequestParam String option2, @RequestParam String option3, @RequestParam String option4, @RequestParam String answer) {
        String response = questionService.updateQuestion(questionId, question, answer, option1, option2, option3, option4, category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("updateQuestion2")
    public ResponseEntity<String> updateQuestion(@RequestBody QuestionModel model, @RequestParam Integer questionId) {
        return new ResponseEntity<>(questionService.updateQuestion(model, questionId), HttpStatus.OK);
    }

    //generate
    @GetMapping("/generateQuestions")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam Integer noOfQuestions) {
        return new ResponseEntity<>(questionService.getQuestionsForQuiz(category, noOfQuestions), HttpStatus.OK);
    }

    //getQuestion
    @PostMapping("/getQuestions")
    private ResponseEntity<List<QuestionWrapper>> getQuestionById(@RequestBody List<Integer> questionId) {
        System.out.println(environment.getProperty("local.server.port"));//will give us which port it's running
        return new ResponseEntity<>(questionService.getQuestions(questionId), HttpStatus.OK);
    }

    //getScore
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return new ResponseEntity<>(questionService.getScore(responses), HttpStatus.OK);
    }
}
