package com.utcn.demo.service;
import com.utcn.demo.entity.Answers;
import com.utcn.demo.repository.AnswerRepository;
import com.utcn.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public List<Answers> retrieveAnswers(){
        return (List<Answers>) this.answerRepository.findAll();
    }

    public Answers retrieveAnswerById(int id){

        Optional<Answers> answers=this.answerRepository.findById(id);
        if(answers.isPresent()){
            return answers.get();
        }else{
            return null;
        }

    }
    public Answers addAnswer(Answers answers){
        return this.answerRepository.save(answers);
    }

    public String deleteAnswerById(int id){
        try{
            this.answerRepository.deleteById(id);
            return "Entry successfully deleted!";
        }catch (Exception e){
            return "Failed to delete entry with id:" + id;
        }
    }
}
