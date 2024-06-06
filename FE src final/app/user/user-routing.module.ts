import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {QuestionsComponent} from "./questions/questions.component";
import {PostQuestionComponent} from "./post-question/post-question.component";
import {HeaderComponent} from "./header/header.component";
import {ViewQuestionComponent} from "./view-question/view-question.component";
import {EditQuestionComponent} from "./edit-question/edit-question.component";
import {EditAnswerComponent} from "./edit-answer/edit-answer.component";
import {UserManagementComponent} from "./user-management/user-management.component";

const routes: Routes = [
  {path:'questions', component:QuestionsComponent},
  {path: 'postquestion',component:PostQuestionComponent},
  {path: 'header',component:HeaderComponent},
  {path: 'view-question/:questionid',component:ViewQuestionComponent},
  {path:'edit-question/:questionid',component:EditQuestionComponent},
  {path:'edit-answer/:answerid',component:EditAnswerComponent},
  {path:'user-management',component:UserManagementComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
