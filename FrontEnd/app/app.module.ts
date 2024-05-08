import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from "./login-component/login.component";
import {ButtonModule} from "primeng/button";
import {FormsModule} from "@angular/forms";
import { HeaderComponent } from './header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatListModule} from "@angular/material/list";
import {MatSidenavModule} from "@angular/material/sidenav";
import { QuestionsComponent } from './questions/questions.component';
import { RouterModule, Routes } from '@angular/router';
import {MatInputModule} from "@angular/material/input";
import { SignupComponent } from './signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { PostQuestionComponent } from './post-question/post-question.component';
import {MatChipsModule} from "@angular/material/chips";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner"; // Import HttpClientModule
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    QuestionsComponent,
    SignupComponent,
    PostQuestionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ButtonModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatExpansionModule,
    MatListModule,
    MatSidenavModule,
    MatInputModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatChipsModule,
    MatProgressSpinnerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
