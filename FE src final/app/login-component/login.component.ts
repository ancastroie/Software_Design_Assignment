import {Component, Input, Output , EventEmitter} from "@angular/core";
import { HttpClient } from '@angular/common/http';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import {AuthService} from "../auth-service/auth-service/auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {UserService} from "../services/users.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup | any;

  constructor(
    private service: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  login() {
    console.log(this.loginForm.value);
    this.service.login(this.loginForm.value).subscribe(
      (response: any) => {
        console.log(response);
        const userId = response?.body?.userId; // Extract userId from the response body

        if (!userId) {
          // Handle case where userId is not available
          console.error('User ID not found in the response');
          return;
        }

        // Make a separate request to fetch the banned status
        this.userService.getIsBannedById(userId).subscribe(
          (isBanned: boolean) => {
            console.log("is banned", isBanned);
            if (isBanned) {
              // Display message if user is banned
              this.snackBar.open('You are banned from the site.', 'Close', {duration: 4000});
            } else {
              // Navigate to questions page if user is not banned
              this.router.navigateByUrl("user/questions");
            }
          },
          (error: any) => {
            console.error('Error fetching banned status:', error);
          }
        );
      },
      (error: any) => {
        this.snackBar.open('Bad credentials', 'Close', {duration: 4000, panelClass: 'error-snackbar'});
      }
    );
  }
}
