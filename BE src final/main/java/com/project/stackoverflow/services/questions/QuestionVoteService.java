package com.project.stackoverflow.services.questions;

import com.project.stackoverflow.entities.QuestionVotes;
import com.project.stackoverflow.entities.Questions;
import com.project.stackoverflow.entities.User;
import com.project.stackoverflow.repositories.QuestionRepository;
import com.project.stackoverflow.repositories.QuestionVoteRepository;
import com.project.stackoverflow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class QuestionVoteService {
    @Autowired
    private QuestionVoteRepository questionVoteRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<QuestionVotes> retrieveQuestionsVotes(){

        List<QuestionVotes> questionVotesList = (List<QuestionVotes>) questionVoteRepository.findAll();
        return questionVotesList;
    }
    public List<QuestionVotes> getVotesByQuestionId(int questionid) {
        return questionVoteRepository.findByQuestionid(questionid);
    }

    // Retrieve votes by user
    public List<QuestionVotes> getVotesByUserId(Long userid) {
        return questionVoteRepository.findByUserid(userid);
    }

    public QuestionVotes addVote(QuestionVotes questionVote) {
        // Check if the user has already voted on this question
        Optional<QuestionVotes> existingVote = questionVoteRepository.findByUseridAndQuestionid(
                questionVote.getUserid(), questionVote.getQuestionid());
        System.out.println(questionVote.getUserid()+" "+questionVote.getQuestionid()+" "+questionVote.getId()+" "+questionVote.getVote_type());

        Optional<Questions> optionalQuestions=questionRepository.findById(questionVote.getQuestionid());
        Questions existingQuestion=optionalQuestions.get();

        Optional<User> questionAuthor=userRepository.findById(existingQuestion.getAuthorid());
        User author=questionAuthor.get();

        if (author.getUserscore() == null) {
            author.setUserscore(0.0);
        }

        if (existingVote.isPresent()) {
            // If vote exists, update the voteType

            QuestionVotes voteToUpdate = existingVote.get();
            System.out.println("Existing vote found. Updating vote type to: " + questionVote.getVote_type());
            if(voteToUpdate.getVote_type()!=questionVote.getVote_type()){
                if(questionVote.getVote_type()) {
                    existingQuestion.setVotecount(existingQuestion.getVotecount()+2);
                    questionRepository.save(existingQuestion);
                    author.setUserscore(author.getUserscore()+1.5+2.5);

                }
                else {
                    existingQuestion.setVotecount(existingQuestion.getVotecount()-2);
                    questionRepository.save(existingQuestion);
                    author.setUserscore(author.getUserscore()-2.5-1.5);
                }
            }
            voteToUpdate.setVote_type(questionVote.getVote_type());
            return questionVoteRepository.save(voteToUpdate);
        } else {
            // If vote does not exist, create a new vote
            if(questionVote.getVote_type()) {
                existingQuestion.setVotecount(existingQuestion.getVotecount()+1);
                questionRepository.save(existingQuestion);
                author.setUserscore(author.getUserscore() + 2.5);
            }
            else {
                existingQuestion.setVotecount(existingQuestion.getVotecount()-1);
                questionRepository.save(existingQuestion);
                author.setUserscore(author.getUserscore() -1.5);
            }

            System.out.println("No existing vote. Creating a new vote with vote type: " + questionVote.getVote_type());

            return questionVoteRepository.save(questionVote);
        }
    }
}
