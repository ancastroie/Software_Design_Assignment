import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login-component/login.component";
import {QuestionsComponent} from "./questions/questions.component";
import { SignupComponent } from "./signup/signup.component";
import {PostQuestionComponent} from "./post-question/post-question.component";

const routes: Routes = [
  {path:'login', component:LoginComponent},
  {path:'questions', component:QuestionsComponent},
  { path: 'signup', component: SignupComponent },
  {path: 'postquestion',component:PostQuestionComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
