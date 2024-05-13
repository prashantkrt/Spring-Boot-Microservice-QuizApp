package com.mylearning.microservice.springbootmicroservicequestionservice.service;

import com.mylearning.microservice.springbootmicroservicequestionservice.entity.Question;
import com.mylearning.microservice.springbootmicroservicequestionservice.model.QuestionModel;
import com.mylearning.microservice.springbootmicroservicequestionservice.model.QuestionWrapper;
import com.mylearning.microservice.springbootmicroservicequestionservice.model.Response;
import com.mylearning.microservice.springbootmicroservicequestionservice.repository.QuestionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public String addQuestion(QuestionModel question) {
        Question questionEntity = new Question();
        BeanUtils.copyProperties(question, questionEntity);
        Example<Question> example = Example.of(questionEntity);
        if (questionRepository.exists(example)) {
            return "Question already exists";
        }
        questionRepository.save(questionEntity);
        return "Question added";
    }

    @Override
    public Iterable<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    @Override
    public String deleteQuestion(Integer questionId) {
        if (questionRepository.existsById(questionId)) {
            questionRepository.deleteById(questionId);
            return "Question deleted";
        } else
            return "Question not found";
    }

    @Override
    public String updateQuestion(Integer questionId, String question, String answer, String option1, String option2, String option3, String option4, String category) {
        if (questionRepository.findById(questionId).isPresent()) {
            Question questionEntity = questionRepository.findById(questionId).get();
            questionEntity.setQuestion(question);
            questionEntity.setAnswer(answer);
            questionEntity.setOption1(option1);
            questionEntity.setOption2(option2);
            questionEntity.setOption3(option3);
            questionEntity.setOption4(option4);
            questionEntity.setCategory(category);
            questionRepository.save(questionEntity);
            return "Question updated";
        } else
            return "Question not found";
    }

    @Override
    public List<Question> findQuestionByCategory(String category) {
        List<Question> questions = questionRepository.findByCategory(category);
        if (!questions.isEmpty()) {
            return questions;
        } else
            return null;
    }

    @Override
    public String updateQuestion(QuestionModel model, Integer questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            BeanUtils.copyProperties(model, question.get());
            questionRepository.save(question.get());
            return "Updated question";
        } else
            return "Question not found";
    }

    @Override
    public List<Integer> getQuestionsForQuiz(String category, Integer noOfQuestions) {
        return questionRepository.findRandomQuestionsByCategory(category, noOfQuestions);
    }

    @Override
    public List<QuestionWrapper> getQuestions(List<Integer> questionId) {
        List<Question> questions = questionRepository.findAllById(questionId);
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        questions.forEach(question -> {
            QuestionWrapper questionWrapper = new QuestionWrapper();
            BeanUtils.copyProperties(question, questionWrapper);
            questionWrappers.add(questionWrapper);
        });
        return questionWrappers;
    }

    @Override
    public Integer getScore(List<Response> responses) {
        AtomicInteger score = new AtomicInteger(0);
        responses.forEach(response -> {
            Optional<Question> question = questionRepository.findById(Integer.parseInt(response.getQuestionId()));
            if (question.isPresent()) {
                if (question.get().getAnswer().equalsIgnoreCase(response.getAnswer()))
                    score.getAndIncrement();
            }
        });
        return score.get();
    }
}
