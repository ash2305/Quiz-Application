package com.ashok.QuizApp.Controller;

import com.ashok.QuizApp.Model.Question;
import com.ashok.QuizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
       return questionService.findAllQuestions();
    }

    @GetMapping("/category/{text}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable("text") String category)
    {
        return questionService.findAllQuestionsByCategory(category);
    }

    @PostMapping("/questions")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    @GetMapping("questions/{id}")
    public Optional<Question> getQuestionById(@PathVariable int id)
    {
        return questionService.findQuestionById(id);
    }

    @DeleteMapping("/questions/{id}")
    public String deleteQuestionById(@PathVariable int id)
    {
        return questionService.deleteQuestionById(id);
    }
 }
