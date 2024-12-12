package com.ashok.QuizApp.Service;

import com.ashok.QuizApp.Model.Question;
import com.ashok.QuizApp.Repo.QuestionRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> findAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> findAllQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findQuestionByCategory(category), HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
        questionRepository.save(question);
        return new ResponseEntity<>("Successfully added", HttpStatus.CREATED);
    }catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error in creating the record", HttpStatus.BAD_REQUEST);
    }

    public Optional<Question> findQuestionById(int id) {
        return questionRepository.findById(id);
    }

    public String deleteQuestionById(int id) {
       questionRepository.deleteById(id);
       return "Deleted Successfully";
    }
}
