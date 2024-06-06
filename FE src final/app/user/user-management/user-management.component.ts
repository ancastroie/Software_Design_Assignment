import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/users.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.scss']
})
export class UserManagementComponent implements OnInit {
  users: any[] = [];

  constructor(
    private userService: UserService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.fetchUsers();
  }

  toggleBanStatus(user: any): void {
    if (user.banned) {
      this.userService.unbanUser(user.id).subscribe(
        (response) => {
          user.banned = false;
          this.snackBar.open('User unbanned successfully', 'Close', { duration: 3000 });
        },
        (error) => {
          this.snackBar.open('Error unbanning user: ' + error.message, 'Close', { duration: 3000 });
        }
      );
    } else {
      this.userService.banUser(user.id).subscribe(
        (response) => {
          user.banned = true;
          this.snackBar.open('User banned successfully', 'Close', { duration: 3000 });
        },
        (error) => {
          this.snackBar.open('Error banning user: ' + error.message, 'Close', { duration: 3000 });
        }
      );
    }
  }


  fetchUsers(): void {
    this.userService.getAllUsers().subscribe(
      (data: any[]) => {
        this.users = data;
      },
      (error) => {
        this.snackBar.open('Error fetching users: ' + error.message, 'Close', {
          duration: 3000,
        });
      }
    );
  }
}
