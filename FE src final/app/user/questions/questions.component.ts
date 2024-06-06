import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { QuestionService } from '../../services/question.service';
import {UserService} from "../../services/users.service";
import {StorageService} from "../../auth-service/storage-service/storage.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss']
})
  export class QuestionsComponent {
  questions: any[] = []; // Store fetched questions
  ismoderator:boolean=false;
  tagSearchQuery: string = ''; // Variable to store tag search query
  textSearchQuery: string = ''; // Variable to store text search query
  userIdSearchQuery: number|any;

  constructor(
    private questionService: QuestionService, // Inject the service
    private router: Router ,
    private userService: UserService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadQuestions(); // Load questions on initialization
    this.checkModeratorStatus();
  }

  loadQuestions(): void {
    this.questionService.getQuestions().subscribe(
      (data) => {
        this.questions = data.sort((a, b) => new Date(b.creationdatetime).getTime() - new Date(a.creationdatetime).getTime());
      },
      (error) => {
        console.error('Error fetching questions:', error);
      }
    );
  }



  getAuthorName(authorId: number): string {
    let authorName = '';
    this.userService.getUserNameById(authorId).subscribe(
      (name) => {
        authorName = name;
      },
      (error) => {
        console.error('Error fetching author name:', error);
      }
    );
    return authorName;
  }


  addVote(question: any,vote_type:string){
    console.log(vote_type);
    const data={
      vote_type:vote_type,
      userid:StorageService.getUserId(),
      questionid: question.questionid
    }
    if (question.authorid === StorageService.getUserId()) {
      this.snackBar.open('You cannot vote on your own question.', 'Close', {
        duration: 3000, // Duration in milliseconds
        verticalPosition: 'top' // Position of the snackbar
      });
      return;
    }
    this.questionService.addVoteToQuestions(data).subscribe((res)=>{
      console.log(res);
      console.log('votecount from question',question.votecount)
    })
  }

  deleteQuestion(id: number): void {
    console.log('id to delete ',id )
    this.questionService.deleteQuestionById(id).subscribe(
      (response) => {
        console.log('Question deleted successfully:', response);
        // Refresh the list of questions after deletion
        this.loadQuestions();
      },
      (error) => {
        console.error('Error deleting question:', error);
      }
    );
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

  isAuthor(questionAuthorId: number): boolean {
    // @ts-ignore
    return questionAuthorId === StorageService.getUserId();
  }


  search(): void {
    // Call backend service to fetch filtered questions
    this.questionService.filterQuestions(this.tagSearchQuery, this.textSearchQuery, this.userIdSearchQuery).subscribe(
      (data) => {
        this.questions = data;
      },
      (error) => {
        console.error('Error fetching filtered questions:', error);
      }
    );
  }

  searchByText(): void {
    if (this.textSearchQuery.trim() !== '') {
      this.questionService.searchQuestionsByTitle(this.textSearchQuery).subscribe(data => {
        this.questions = data;
      });
    } else {
      this.loadQuestions();
    }
  }
}

