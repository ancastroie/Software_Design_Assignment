import { Component, OnInit } from '@angular/core';
import {QuestionService} from "../services/question.service";
import { Router } from '@angular/router'; // For navigation after submission
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
@Component({
  selector: 'app-post-question',
  templateUrl: './post-question.component.html',
  styleUrls: ['./post-question.component.scss']
})
export class PostQuestionComponent implements OnInit {
  questionForm: FormGroup |any; // FormGroup for Reactive Forms
  isSubmitting = false; // Loading state for form submission

  constructor(
    private fb: FormBuilder,
    private questionService: QuestionService, // Inject the QuestionService
    private router: Router // Optional, for navigation after submission
  ) {}

  ngOnInit(): void {
    this.questionForm = this.fb.group({
      title: ['', Validators.required], // Title is required
      body: ['', Validators.required], // Body is required
      tag: ['', Validators.required], // A single tag is required
    });
  }

  submitQuestion(): void {
    if (this.questionForm.valid) {
      this.isSubmitting = true; // Set loading state

      const questionData = this.questionForm.value; // Get form data

      // Use the QuestionService to save the question to JSON Server
      this.questionService.askQuestion(questionData).subscribe(
        (response) => {
          console.log('Question posted successfully:', response);
          this.isSubmitting = false; // Reset loading state
          this.router.navigate(['/questions']); // Navigate to the questions page
        },
        (error) => {
          console.error('Error posting question:', error);
          this.isSubmitting = false; // Reset loading state
        }
      );
    } else {
      console.error('Form is invalid');
    }
  }
}
