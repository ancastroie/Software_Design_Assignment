package com.utcn.demo.controller;
import com.utcn.demo.entity.Questions;
import com.utcn.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Questions> getAllQuestions() {
        List<Questions> questions =this.questionService.retrieveQuestions();
        return questions;
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Questions getQuestionById(@PathVariable int id) {
        Questions questions =this.questionService.retrieveQuestionById(id);
        return questions;
    }
    @GetMapping("/getById")
    @ResponseBody
    public Questions getQuestionByIdVar2(@RequestParam("id") int id) {
        Questions questions =this.questionService.retrieveQuestionById(id);
        return questions;
    }
    @PostMapping("/addQuestion")
    @ResponseBody
    public Questions addQuestion(@RequestBody Questions questions){
        return  this.questionService.addQuestion(questions);
    }

    @PutMapping("/updateQuestion")
    @ResponseBody
    public Questions updateQuestion(@RequestBody Questions questions){
        return  this.questionService.addQuestion(questions);
    }

    @DeleteMapping("/deleteById")
    @ResponseBody
    public String deleteQuestionById(@RequestParam int id){
        return this.questionService.deleteQuestionById(id);
    }
}
