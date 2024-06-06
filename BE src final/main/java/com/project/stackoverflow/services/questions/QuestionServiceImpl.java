package com.project.stackoverflow.services.questions;
import com.project.stackoverflow.dtos.QuestionDTO;
import com.project.stackoverflow.entities.Questions;
import com.project.stackoverflow.entities.User;
import com.project.stackoverflow.repositories.QuestionRepository;
import com.project.stackoverflow.repositories.TagRespository;
import com.project.stackoverflow.repositories.UserRepository;
import com.project.stackoverflow.services.answer.AnswerServiceImpl;
import com.project.stackoverflow.services.user.UserService;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerServiceImpl answerService;

    @Autowired
    private TagRespository tagRespository;

    public static final Logger logger = Logger.getLogger(QuestionServiceImpl.class.getName());

    public List<Questions> retrieveQuestions(){

        List<Questions> questionsList = (List<Questions>) questionRepository.findAll();
        for (Questions question : questionsList) {
            String authorName = userService.getUserNameById(question.getAuthorid());

            question.setAuthorName(authorName);
            User author = userService.getUserById(question.getAuthorid());
            question.setUserscore(author.getUserscore());
            logger.info("Question ID: " + question.getQuestionid() + ", Author Name: " + authorName);
        }
        return questionsList;
    }

    public Questions retrieveQuestionById(int id){

        Optional<Questions> questionsOptional = questionRepository.findById(id);
        if (questionsOptional.isPresent()) {
            Questions question = questionsOptional.get();
            String authorName = userService.getUserNameById(question.getAuthorid());
            question.setAuthorName(authorName);
            return question;
        } else {
            return null;
        }

    }
    public Questions addQuestion(Questions questions){
        return this.questionRepository.save(questions);
    }

    public String deleteQuestionById(int id){
        try{
            answerService.deleteAnswersByQuestionid(id);

            this.questionRepository.deleteById(id);
            return "Entry successfully deleted!";
        }catch (Exception e){
            return "Failed to delete entry with id:" + id;
        }
    }

    public Questions updateQuestion(int id, Questions questions) {
        Optional<Questions> existingQuestionOpt = questionRepository.findById(id);
        if (existingQuestionOpt.isPresent()) {
            Questions existingQuestion = existingQuestionOpt.get();
            existingQuestion.setTitle(questions.getTitle());
            existingQuestion.setText(questions.getText());
            // Update other fields as needed
            return questionRepository.save(existingQuestion);
        } else {
            throw new RuntimeException("Question not found with id: " + questions.getQuestionid());
        }
    }
    public List<Questions> filterByTitle(String searchText) {
        System.out.println("searching by"+searchText);
        List<Questions> allQuestions = (List<Questions>) questionRepository.findAll();
        return allQuestions.stream()
                .filter(question -> searchText == null || question.getTitle().contains(searchText))
                .collect(Collectors.toList());
    }

    public List<Questions> filterQuestions(String tag, String searchText, Integer userId) {
        List<Questions> allQuestions = (List<Questions>) questionRepository.findAll();

        return allQuestions.stream()
                .filter(question -> {
                    boolean matchesTag = (tag == null || question.getTags().contains(tag));
                    boolean matchesText = (searchText == null || question.getTitle().contains(searchText));
                    boolean matchesUser = (userId == null || userId == 0 || Integer.valueOf(question.getAuthorid()).equals(userId));

                    // Log the filtering process
                    logger.info("Question ID: " + question.getQuestionid() +
                            ", matchesTag: " + matchesTag +
                            ", matchesText: " + matchesText +
                            ", matchesUser: " + matchesUser);

                    return matchesTag && matchesText && matchesUser;
                })
                .collect(Collectors.toList());
    }
}

