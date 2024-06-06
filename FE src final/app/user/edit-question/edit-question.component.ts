import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionService } from '../../services/question.service';

@Component({
  selector: 'app-edit-question',
  templateUrl: './edit-question.component.html',
  styleUrls: ['./edit-question.component.scss']
})
export class EditQuestionComponent implements OnInit {
  questionForm: FormGroup|any;
  questionid: number|any;
  questionData: any;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private questionService: QuestionService
  ) { }

  ngOnInit(): void {
    this.questionForm = this.fb.group({
      title: ['', Validators.required],
      text: ['', Validators.required]
    });

    this.route.paramMap.subscribe(params => {
      // @ts-ignore
      const id = +params.get('questionid'); // Correct parameter name here
      if (id) {
        this.questionid = id;
        this.loadQuestion(this.questionid);
      } else {
        console.error('Error: No question ID found in route parameters');
      }
    });
  }

  loadQuestion(id: number): void {
    this.questionService.getQuestionById(id).subscribe(
      data => {
        this.questionData = data;
        if (this.questionData) {
          this.questionForm.patchValue({
            title: this.questionData.title,
            text: this.questionData.text
          });
        } else {
          console.error('Error: Question data is null');
        }
      },
      error => {
        console.error('Error fetching question data:', error);
      }
    );
  }

  onSubmit(): void {
    if (this.questionForm.valid && this.questionData) {
      const updatedQuestion = {
        ...this.questionData,
        title: this.questionForm.value.title,
        text: this.questionForm.value.text
      };

      this.questionService.updateQuestion(this.questionid, updatedQuestion).subscribe(
        () => {
          this.router.navigate(['/user/questions']);
        },
        error => {
          console.error('Error updating question:', error);
        }
      );
    } else {
      console.error('Form is invalid or question data is null');
    }
  }

  cancel(): void {
    this.router.navigate(['/user/questions']);
  }
}
