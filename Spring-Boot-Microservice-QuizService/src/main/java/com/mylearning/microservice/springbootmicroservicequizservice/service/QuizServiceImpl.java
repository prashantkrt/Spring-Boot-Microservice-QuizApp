package com.mylearning.microservice.springbootmicroservicequizservice.service;

import com.mylearning.microservice.springbootmicroservicequizservice.entity.Quiz;
import com.mylearning.microservice.springbootmicroservicequizservice.feign.IQuiz;
import com.mylearning.microservice.springbootmicroservicequizservice.model.QuestionWrapper;
import com.mylearning.microservice.springbootmicroservicequizservice.model.QuizDto;
import com.mylearning.microservice.springbootmicroservicequizservice.model.Response;
import com.mylearning.microservice.springbootmicroservicequizservice.repository.QuizRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private IQuiz quizFeigh;

    @Override
    public ResponseEntity<String> createQuiz(QuizDto quizDto) {
        try {
            List<Integer> questions = quizFeigh.generateQuestions(quizDto.getCategory(), quizDto.getNoOfQuestions()).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(quizDto.getTitle());
            quiz.setQuestions(questions);
            quizRepository.save(quiz);
            return new ResponseEntity<>("Quiz created", HttpStatus.OK);
        } catch (Exception e) {
            log.error(" Exception caused ->  {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questions = quiz.getQuestions();
        List<QuestionWrapper> questionWrappers = quizFeigh.getQuestionById(questions).getBody();
        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
        return new ResponseEntity<>(quizFeigh.getScore(response).getBody(), HttpStatus.OK);
    }
}
