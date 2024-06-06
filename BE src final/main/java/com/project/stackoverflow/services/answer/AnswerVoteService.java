package com.project.stackoverflow.services.answer;


import com.project.stackoverflow.entities.*;
import com.project.stackoverflow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerVoteService {
    @Autowired
    private AnswerVoteRepository answerVoteRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;
    public List<AnswerVotes> retrieveAnswerVotes(){

        List<AnswerVotes> answerVotesList = (List<AnswerVotes>) answerVoteRepository.findAll();
        return answerVotesList;
    }
    public List<AnswerVotes> getVotesByAnswerid(int answerid) {
        return answerVoteRepository.findByAnswerid(answerid);
    }

    // Retrieve votes by user
    public List<AnswerVotes> getVotesByUserId(Long userid) {
        return answerVoteRepository.findByUserid(userid);
    }

    public AnswerVotes addVote(AnswerVotes answerVotes) {
        // Check if the user has already voted on this answer
        Optional<AnswerVotes> existingVote = answerVoteRepository.findByUseridAndAnswerid(
                answerVotes.getUserid(), answerVotes.getAnswerid());
        System.out.println(answerVotes.getUserid()+" "+answerVotes.getAnswerid()+" "+answerVotes.getId()+" "+answerVotes.getVote_type());

        Optional<Answers> optionalAnswers=answerRepository.findById(answerVotes.getAnswerid());
        Answers existingAnswer=optionalAnswers.get();


        Optional<User> answerAuthor=userRepository.findById(existingAnswer.getAuthorid());
        User author=answerAuthor.get();

        if (author.getUserscore() == null) {
            author.setUserscore(0.0);
        }

        Optional<User> votingUser = userRepository.findById(answerVotes.getUserid());
        User voter = votingUser.get();


        if (existingVote.isPresent()) {
            // If vote exists, update the voteType

            AnswerVotes voteToUpdate = existingVote.get();
            System.out.println("Existing vote found. Updating vote type to: " + answerVotes.getVote_type());
            if(voteToUpdate.getVote_type()!=answerVotes.getVote_type()){
                if(answerVotes.getVote_type()) {
                    existingAnswer.setVotecount(existingAnswer.getVotecount()+2);
                    answerRepository.save(existingAnswer);
                    author.setUserscore(author.getUserscore()+5.0+2.5);
                    voter.setUserscore(voter.getUserscore() + 1.5);
                }
                else {
                    existingAnswer.setVotecount(existingAnswer.getVotecount()-2);
                    answerRepository.save(existingAnswer);
                    author.setUserscore(author.getUserscore()-2.5-5);
                    voter.setUserscore(voter.getUserscore() - 1.5);

                }
            }
            voteToUpdate.setVote_type(answerVotes.getVote_type());
            return answerVoteRepository.save(voteToUpdate);
        } else {
            // If vote does not exist, create a new vote
            if(answerVotes.getVote_type()) {
                existingAnswer.setVotecount(existingAnswer.getVotecount()+1);
                answerRepository.save(existingAnswer);
                author.setUserscore(author.getUserscore()+5.0);
            }
            else {
                existingAnswer.setVotecount(existingAnswer.getVotecount()-1);
                answerRepository.save(existingAnswer);
                author.setUserscore(author.getUserscore()-2.5);
                voter.setUserscore(voter.getUserscore() - 1.5);
            }

            System.out.println("No existing vote. Creating a new vote with vote type: " + answerVotes.getVote_type());

            return answerVoteRepository.save(answerVotes);
        }
    }
}

