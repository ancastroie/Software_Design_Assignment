package com.utcn.demo.service;
import com.utcn.demo.entity.Questions;
import com.utcn.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Questions> retrieveQuestions(){
        return (List<Questions>) this.questionRepository.findAll();
    }

    public Questions retrieveQuestionById(int id){

        Optional<Questions> questions=this.questionRepository.findById(id);
        if(questions.isPresent()){
            return questions.get();
        }else{
            return null;
        }

    }
    public Questions addQuestion(Questions questions){
        return this.questionRepository.save(questions);
    }

    public String deleteQuestionById(int id){
        try{
            this.questionRepository.deleteById(id);
            return "Entry successfully deleted!";
        }catch (Exception e){
            return "Failed to delete entry with id:" + id;
        }
    }
}
