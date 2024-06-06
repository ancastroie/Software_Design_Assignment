import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {QuestionService} from "../../services/question.service";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StorageService} from "../../auth-service/storage-service/storage.service";
import {AnswerService} from "../../services/answer-service/answer.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {UserService} from "../../services/users.service";

@Component({
  selector: 'app-view-question',
  templateUrl: './view-question.component.html',
  styleUrls: ['./view-question.component.scss']
})
export class ViewQuestionComponent implements OnInit {

  questionid:number=this.activatedRouter.snapshot.params["questionid"];
  question:any;
  validateForm:FormGroup|any;
  answers: any[]=[];
  ismoderator:boolean=false;

  constructor(
    private questionService:QuestionService,
    private router:Router,
    private activatedRouter:ActivatedRoute,
    private fb:FormBuilder,
    private answerService:AnswerService,
    private snackBar:MatSnackBar,
    private userService:UserService
  ) { }

  ngOnInit(): void {
    this.validateForm=this.fb.group({
      text:[null,Validators.required]
    })
    this.getQuestionById();
    this.getAnswersByQuestionid();
    this.checkModeratorStatus();
  }

  getQuestionById(){
    this.questionService.getQuestionById(this.questionid).subscribe(
      (res)=>{
        console.log(res);
        this.question=res;
        //console.log(this.question);

      }
    )
  }
  addAnswer(){
    console.log(this.validateForm.value);
    const data=this.validateForm.value;
    data.questionid=this.questionid;
    data.userid=StorageService.getUserId();
    console.log(data);
    this.answerService.postAnswer(data).subscribe(
      (res)=>{
        console.log(res);
      }
    )
  }

  private getAnswersByQuestionid() {
    this.answerService.getAnswersByQuestionid(this.questionid).subscribe(
      (res) => {
        console.log(res);
        this.answers = res.sort((a, b) => b.votecount - a.votecount);
      }
    );
  }

  addVote(answer: any,vote_type:string){
    console.log(vote_type);
    const data={
      vote_type:vote_type,
      userid:StorageService.getUserId(),
      answerid: answer.answerid
    }
    if (answer.authorid === StorageService.getUserId()) {
      this.snackBar.open('You cannot vote on your own answer.', 'Close', {
        duration: 3000, // Duration in milliseconds
        verticalPosition: 'top' // Position of the snackbar
      });
      return;
    }
    this.answerService.addVoteToAnswer(data).subscribe((res)=>{
      console.log(res);
      console.log('votecount from answer',answer.votecount)
    })
  }

  isAuthor(questionAuthorId: number): boolean {
    // @ts-ignore
    return questionAuthorId === StorageService.getUserId();
  }

  private checkModeratorStatus() {
    const userId = StorageService.getUserId(); // Get the user ID from StorageService
    this.userService.getIsModeratorById(userId).subscribe(
      (isMod) => {
        this.ismoderator = isMod;
      },
      (error) => {
        console.error('Error fetching moderator status:', error);
      }
    );
  }

  deleteAnswer(answerid: number) {
    console.log('id to delete ',answerid )
    this.answerService.deleteAnswerById(answerid).subscribe(
      (response) => {
        console.log('Question deleted successfully:', response);
        // Refresh the list of questions after deletion
        this.getAnswersByQuestionid();
      },
      (error) => {
        console.error('Error deleting question:', error);
      }
    );
  }
}
