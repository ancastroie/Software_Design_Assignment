import { Component } from '@angular/core';
import {StorageService} from "./auth-service/storage-service/storage.service";
import {NavigationEnd, Router} from "@angular/router";
import {UserService} from "./services/users.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'firstangapp';
  isUserLoggedIn: boolean | any;
  isModerator: boolean = false;

  constructor(
    private router:Router,
    private userService: UserService
  ) {
  }

  ngOnInit(){
    this.updateUserLoggedInStat();
    this.router.events.subscribe(event=>{
      if(event instanceof NavigationEnd){
        this.updateUserLoggedInStat();

      }
    })
  }
  private updateUserLoggedInStat():void{
    this.isUserLoggedIn=StorageService.isUserLoggedIn();
    if (this.isUserLoggedIn) {
      const id = StorageService.getUserId();
      this.checkModeratorStatus(id);
    }
  }

  logout(){
    StorageService.logout();
    this.router.navigateByUrl("login");
  }

  private checkModeratorStatus(userId: string): void {
    this.userService.getIsModeratorById(userId).subscribe(
      (isMod) => {
        this.isModerator = isMod;
      },
      (error) => {
        console.error('Error fetching moderator status:', error);
      }
    );
  }
}
