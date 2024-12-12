package com.ashok.QuizApp.Service;

import com.ashok.QuizApp.Model.Question;
import com.ashok.QuizApp.Model.QuestionWrapper;
import com.ashok.QuizApp.Model.Quiz;
import com.ashok.QuizApp.Model.Response;
import com.ashok.QuizApp.Repo.QuestionRepository;
import com.ashok.QuizApp.Repo.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title)
    {
        List<Question> questions = questionRepository.findQuestionByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsToUsers = new ArrayList<>();
        for(Question q : questionsFromDB)
        {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(),
                    q.getOption2(), q.getOption3(), q.getOption4());
            questionsToUsers.add(qw);
        }
        return new ResponseEntity<>(questionsToUsers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int rightCounter=0;
        int i=0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                rightCounter++;

            i++;
        }
        return new ResponseEntity<>(rightCounter, HttpStatus.OK);
    }
}
