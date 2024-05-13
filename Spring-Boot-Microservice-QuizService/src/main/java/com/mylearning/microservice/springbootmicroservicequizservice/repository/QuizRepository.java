package com.mylearning.microservice.springbootmicroservicequizservice.repository;


import com.mylearning.microservice.springbootmicroservicequizservice.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
