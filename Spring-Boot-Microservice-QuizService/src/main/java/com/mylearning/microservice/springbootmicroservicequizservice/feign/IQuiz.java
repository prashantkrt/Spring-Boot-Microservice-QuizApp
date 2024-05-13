package com.mylearning.microservice.springbootmicroservicequizservice.feign;

import com.mylearning.microservice.springbootmicroservicequizservice.model.QuestionWrapper;
import com.mylearning.microservice.springbootmicroservicequizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("Spring-Boot-Microservice-QuestionService")
public interface IQuiz {
    //generate
    @GetMapping("question/generateQuestions")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam Integer noOfQuestions);

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionById(@RequestBody List<Integer> questionId);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
