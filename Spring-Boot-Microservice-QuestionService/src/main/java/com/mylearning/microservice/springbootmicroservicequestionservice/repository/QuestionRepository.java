package com.mylearning.microservice.springbootmicroservicequestionservice.repository;
import com.mylearning.microservice.springbootmicroservicequestionservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    public List<Question> findByCategory(String category);

    @Query(value = "Select id from Question_Details q where q.category=:category ORDER BY RANDOM() LIMIT :count",nativeQuery = true)
    public List<Integer> findRandomQuestionsByCategory(String category, int count);
}
