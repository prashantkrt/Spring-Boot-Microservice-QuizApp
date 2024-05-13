package com.mylearning.microservice.springbootmicroservicequestionservice.service;

import com.mylearning.microservice.springbootmicroservicequestionservice.entity.Question;
import com.mylearning.microservice.springbootmicroservicequestionservice.model.QuestionModel;
import com.mylearning.microservice.springbootmicroservicequestionservice.model.QuestionWrapper;
import com.mylearning.microservice.springbootmicroservicequestionservice.model.Response;

import java.util.List;

public interface QuestionService {

    public String addQuestion(QuestionModel question);

    public Iterable<Question> getAllQuestion();

    public String deleteQuestion(Integer questionId);

    public String updateQuestion(Integer questionId, String question, String answer, String option1, String option2, String option3, String option4, String category);

    public List<Question> findQuestionByCategory(String category);

    public String updateQuestion(QuestionModel model, Integer questionId);

    public List<Integer> getQuestionsForQuiz(String category, Integer noOfQuestions);

    public List<QuestionWrapper> getQuestions(List<Integer> questionId);

    public Integer getScore(List<Response> responses);
}
