import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { ViewQuestionComponent } from './view-question/view-question.component';
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import { EditQuestionComponent } from './edit-question/edit-question.component';
import { EditAnswerComponent } from './edit-answer/edit-answer.component';
import { UserManagementComponent } from './user-management/user-management.component';
import {MatTableModule} from "@angular/material/table";


@NgModule({
  declarations: [
    ViewQuestionComponent,
    EditQuestionComponent,
    EditAnswerComponent,
    UserManagementComponent
  ],
    imports: [
        CommonModule,
        UserRoutingModule,
        MatInputModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatIconModule,
        MatTableModule
    ]
})
export class UserModule { }
