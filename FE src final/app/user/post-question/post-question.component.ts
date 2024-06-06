import { Component, OnInit } from '@angular/core';
import {QuestionService} from "../../services/question.service";
import { Router } from '@angular/router'; // For navigation after submission
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import {StorageService} from "../../auth-service/storage-service/storage.service";

@Component({
  selector: 'app-post-question',
  templateUrl: './post-question.component.html',
  styleUrls: ['./post-question.component.scss']
})
export class PostQuestionComponent implements OnInit {
  questionForm: FormGroup | any; // FormGroup for Reactive Forms
  isSubmitting = false; // Loading state for form submission

  constructor(
    private fb: FormBuilder,
    private service: QuestionService, // Inject the QuestionService
    private router: Router, // Optional, for navigation after submission
    private storageService: StorageService
  ) {
  }

  ngOnInit(): void {
    this.questionForm = this.fb.group({
      title: ['', Validators.required], // Title is required
      text: ['', Validators.required], // Body is required
      tag: ['', Validators.required], // A single tag is required
    });
  }

  postQuestion() {
    if (this.questionForm.valid && !this.isSubmitting) {
      this.isSubmitting = true; // Set loading state to true
      const formData = this.questionForm.value;

      // Use the static method to get the user ID
      const userId = StorageService.getUserId();

      // Log the user and user ID for debugging
      console.log('Current User:', StorageService.getUser());
      console.log('User ID:', userId);

      // Ensure userId is a valid string
      if (userId) {
        // Set the author field with the user's ID
        formData.authorid = userId;

        // Call the service method to post the question
        this.service.postQuestion(formData).subscribe(
          (response) => {
            // Handle successful submission
            console.log('Question posted successfully:', response);
            this.router.navigate(['/user/questions']); // Optional: Navigate to home page or any other route
          },
          (error) => {
            // Handle error
            console.error('Error posting question:', error);
            this.isSubmitting = false; // Reset loading state
          }
        );
      } else {
        console.error('No user ID found.');
        this.isSubmitting = false; // Reset loading state
      }
    }
  }
}
