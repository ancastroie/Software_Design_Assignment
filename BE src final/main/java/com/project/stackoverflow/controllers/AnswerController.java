package com.project.stackoverflow.controllers;

import com.project.stackoverflow.dtos.AnswerDTO;
import com.project.stackoverflow.entities.Answers;
import com.project.stackoverflow.entities.Questions;
import com.project.stackoverflow.services.answer.AnswerService;
import com.project.stackoverflow.services.answer.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerServiceImpl answerService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Answers> getAllQuestions() {
        List<Answers> answers =this.answerService.retrieveAnswers();
        return answers;
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Answers getAnswerById(@PathVariable Long id) {
        Answers answers =this.answerService.retrieveAnswerById(id);
        return answers;
    }
    @GetMapping("/getById")
    @ResponseBody
    public Answers getAnswerByIdVar2(@RequestParam("id") Long id) {
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
    public ResponseEntity<?> deleteAnswerById(@RequestParam Long id) {
        try {
            String result = answerService.deleteAnswerById(id);
            // Return a JSON response
            return ResponseEntity.ok().body(Map.of("message", result));
        } catch (Exception e) {
            // Return a JSON error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/getByQuestionId/{questionid}")
    @ResponseBody
    public List<Answers> getAnswersByQuestionId(@PathVariable Long questionid) {
        List<Answers> answers = this.answerService.getAnswersByQuestionid(questionid);
        return answers;
    }

    @DeleteMapping("/deleteByQuestionid")
    @ResponseBody
    public void deleteAnswerByQuestionid(@RequestParam int id){
         this.answerService.deleteAnswersByQuestionid(id);
    }

    @PutMapping("/update/{answerid}")
    @ResponseBody
    public Answers updateAnswer(@PathVariable("answerid") Long id, @RequestBody Answers answers) {
        System.out.println("answer id " + id);
        return this.answerService.updateAnswer(id, answers);
    }

}