package com.project.stackoverflow.controllers;

import com.project.stackoverflow.entities.AnswerVotes;
import com.project.stackoverflow.entities.QuestionVotes;
import com.project.stackoverflow.entities.Questions;
import com.project.stackoverflow.services.answer.AnswerVoteService;
import com.project.stackoverflow.services.questions.QuestionVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/votes")
public class VotesController {
    @Autowired
    private QuestionVoteService questionVoteService;

    @Autowired
    private AnswerVoteService answerVoteService;

    @GetMapping("/question/getAll")
    @ResponseBody
    public List<QuestionVotes> getAllQuestionsVotes() {
        List<QuestionVotes> questionVotes =this.questionVoteService.retrieveQuestionsVotes();
        return questionVotes;
    }

    @PostMapping("/question/addVote")
    @ResponseBody
    public QuestionVotes addQuestionVote(@RequestBody QuestionVotes questionVote) {
        System.out.println(questionVote.getId()+" "+questionVote.getQuestionid()+" "+questionVote.getUserid()+" "+questionVote.getVote_type());
        QuestionVotes createdVote = questionVoteService.addVote(questionVote);
        return createdVote;
    }

    ////////////////////////

    @GetMapping("/answer/getAll")
    @ResponseBody
    public List<AnswerVotes> getAllAnswersVotes() {
        List<AnswerVotes> answerVotes =this.answerVoteService.retrieveAnswerVotes();
        return answerVotes;
    }

    @PostMapping("/answer/addVote")
    @ResponseBody
    public AnswerVotes addAnswerVote(@RequestBody AnswerVotes answerVotes) {
       // System.out.println(questionVote.getId()+" "+questionVote.getQuestionid()+" "+questionVote.getUserid()+" "+questionVote.getVote_type());
        AnswerVotes createdVote = answerVoteService.addVote(answerVotes);
        return createdVote;
    }

}
