package com.project.stackoverflow.controllers;
import com.project.stackoverflow.dtos.QuestionDTO;
import com.project.stackoverflow.entities.Questions;
import com.project.stackoverflow.services.questions.QuestionService;
import com.project.stackoverflow.services.questions.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsController {
    @Autowired
    private QuestionServiceImpl questionService;

    @CrossOrigin(origins = "http://localhost:4200")
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

    @PutMapping("/update/{id}")
    @ResponseBody
    public Questions updateQuestion(@PathVariable int id, @RequestBody Questions questions) {
        System.out.println("question id "+id);
        return this.questionService.updateQuestion(id, questions);
    }
    @GetMapping("/search")
    public List<Questions> searchQuestions(
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Integer userId) {

        return questionService.filterQuestions(tag, searchText, userId != null ? userId : 0);
    }
    @GetMapping("/searchByTitle")
    public List<Questions> searchQuestionsByTitle(@RequestParam String searchText) {
        return questionService.filterByTitle(searchText);
    }

}
