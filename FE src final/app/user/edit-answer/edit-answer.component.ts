import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AnswerService } from '../../services/answer-service/answer.service';

@Component({
  selector: 'app-edit-answer',
  templateUrl: './edit-answer.component.html',
  styleUrls: ['./edit-answer.component.scss']
})
export class EditAnswerComponent implements OnInit {
  answerForm: FormGroup;
  answerid: number|any;
  answerData: any;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private answerService: AnswerService
  ) {
    this.answerForm = this.fb.group({
      text: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      // @ts-ignore
      const id = +params.get('answerid');
      if (id) {
        this.answerid = id;
        console.log('Answer ID:', this.answerid);
        this.loadAnswer(this.answerid);
      } else {
        console.error('Error: No answer ID found in route parameters');
      }
    });
  }

  loadAnswer(id: number): void {
    this.answerService.getAnswerById(id).subscribe(
      data => {
        console.log('Answer Data:', data);
        this.answerData = data;
        if (this.answerData) {
          this.answerForm.patchValue({
            text: this.answerData.text
          });
        } else {
          console.error('Error: Answer data is null');
        }
      },
      error => {
        console.error('Error fetching answer data:', error);
      }
    );
  }

  onSubmit(): void {
    if (this.answerForm.valid && this.answerData) {
      const updatedAnswer = {
        ...this.answerData,
        text: this.answerForm.value.text
      };
      console.log('Updated Answer:', updatedAnswer);
      this.answerService.updateAnswer(this.answerid, updatedAnswer).subscribe(
        () => {
          this.router.navigate(['/user/view-question',  updatedAnswer.questionid ]);
        },
        error => {
          console.error('Error updating answer:', error);
        }
      );
    } else {
      console.error('Form is invalid or answer data is null');
    }
  }

  cancel(): void {
    this.router.navigate(['/user/view-question']);
  }
}
