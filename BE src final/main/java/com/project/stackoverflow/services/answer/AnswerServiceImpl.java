package com.project.stackoverflow.services.answer;

import com.project.stackoverflow.dtos.AnswerDTO;
import com.project.stackoverflow.entities.Answers;
import com.project.stackoverflow.entities.Questions;
import com.project.stackoverflow.repositories.AnswerRepository;
import com.project.stackoverflow.repositories.QuestionRepository;
import com.project.stackoverflow.repositories.UserRepository;
import com.project.stackoverflow.services.questions.QuestionServiceImpl;
import com.project.stackoverflow.services.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static com.project.stackoverflow.services.questions.QuestionServiceImpl.logger;

@Service
public class AnswerServiceImpl {


    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(QuestionServiceImpl.class.getName());


    public List<Answers> retrieveAnswers(){

        List<Answers> answersList = (List<Answers>) answerRepository.findAll();
        for (Answers answers : answersList) {
            String authorName = userService.getUserNameById(answers.getAuthorid());
            answers.setAuthorName(authorName);
            logger.info("Ans ID: " + answers.getAnswerid() + ", Author Name: " + authorName);
        }
        return answersList;
    }

    public Answers retrieveAnswerById(Long id){

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

    public String deleteAnswerById(Long id){
        try{
            this.answerRepository.deleteById(id);
            return "Entry successfully deleted!";
        }catch (Exception e){
            return "Failed to delete entry with id:" + id;
        }
    }
    public List<Answers> getAnswersByQuestionid(Long questionid) {
        List<Answers> answersList = answerRepository.findByQuestionid(questionid);
        for (Answers answers : answersList) {
            populateAuthorName(answers);
        }
        return answersList;
    }

    private void populateAuthorName(Answers answers) {
        String authorName = userService.getUserNameById(answers.getAuthorid());
        answers.setAuthorName(authorName);
        logger.info("Ans ID: " + answers.getAnswerid() + ", Author Name: " + authorName);
    }

    @Transactional
    public void deleteAnswersByQuestionid(int questionid) {
        answerRepository.deleteByQuestionid(questionid);
    }

    public Answers updateAnswer(Long id, Answers updatedAnswer) {
        Optional<Answers> existingAnswerOpt = answerRepository.findById(id);
        if (existingAnswerOpt.isPresent()) {
            Answers existingAnswer = existingAnswerOpt.get();
            existingAnswer.setText(updatedAnswer.getText());
            // Update other fields as needed
            return answerRepository.save(existingAnswer);
        } else {
            throw new RuntimeException("Answer not found with id: " + id);
        }
    }
}
