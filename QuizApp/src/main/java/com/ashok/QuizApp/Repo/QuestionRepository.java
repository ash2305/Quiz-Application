package com.ashok.QuizApp.Repo;

import com.ashok.QuizApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findQuestionByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category LIMIT :numQ", nativeQuery = true)
    List<Question> findQuestionByCategory(String category, int numQ);
}
