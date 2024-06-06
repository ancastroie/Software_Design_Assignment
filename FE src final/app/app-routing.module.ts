import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login-component/login.component";
import {QuestionsComponent} from "./user/questions/questions.component";
import { SignupComponent } from "./signup/signup.component";
import {PostQuestionComponent} from "./user/post-question/post-question.component";
import {HeaderComponent} from "./user/header/header.component";

const routes: Routes = [
  {path:'login', component:LoginComponent},
  { path: 'signup', component: SignupComponent },
  {path:'user',loadChildren:()=>import ("./user/user.module").then(m=>m.UserModule)}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
