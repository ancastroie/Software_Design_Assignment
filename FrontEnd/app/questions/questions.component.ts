import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { QuestionService } from '../services/question.service';
@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss']
})
  export class QuestionsComponent {
  questions: any[] = []; // Store fetched questions

  constructor(
    private questionService: QuestionService, // Inject the service
    private router: Router // For navigation
  ) {}

  ngOnInit(): void {
    this.loadQuestions(); // Load questions on initialization
  }

  loadQuestions(): void {
    this.questionService.getQuestions().subscribe(
      (data) => {
        this.questions = data; // Store fetched questions
      },
      (error) => {
        console.error('Error fetching questions:', error); // Error handling
      }
    );
  }

  redirectToAskQuestion(): void {
    this.router.navigate(['/postquestion']); // Navigate to the "Ask a Question" page
  }
}

