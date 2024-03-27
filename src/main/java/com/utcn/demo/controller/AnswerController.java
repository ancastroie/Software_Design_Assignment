package com.utcn.demo.controller;
import com.utcn.demo.entity.Answers;
import com.utcn.demo.service.AnswerService;
import com.utcn.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Answers> getAllQuestions() {
        List<Answers> answers =this.answerService.retrieveAnswers();
        return answers;
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Answers getAnswerById(@PathVariable int id) {
        Answers answers =this.answerService.retrieveAnswerById(id);
        return answers;
    }
    @GetMapping("/getById")
    @ResponseBody
    public Answers getAnswerByIdVar2(@RequestParam("id") int id) {
        Answers answers =this.answerService.retrieveAnswerById(id);
        return answers;
    }
    @PostMapping("/addAnswer")
    @ResponseBody
    public Answers addAnswer(@RequestBody Answers answers){
        return  this.answerService.addAnswer(answers);
    }

    @PutMapping("/updateAnswer")
    @ResponseBody
    public Answers updateAnswer(@RequestBody Answers answers){
        return  this.answerService.addAnswer(answers);
    }

    @DeleteMapping("/deleteById")
    @ResponseBody
    public String deleteQuestionById(@RequestParam int id){
        return this.answerService.deleteAnswerById(id);
    }
}