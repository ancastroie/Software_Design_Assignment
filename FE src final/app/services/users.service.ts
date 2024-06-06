import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/users'; // Base URL for the user API

  constructor(private http: HttpClient) { }

  // Method to fetch user's name by ID
  getUserNameById(userId: number): Observable<string> {
    return this.http.get<string>(`${this.baseUrl}/getById/${userId}`);
  }

  getIsModeratorById(userId: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}/ismoderator/${userId}`);
  }

  banUser(userId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/ban/${userId}`, {});
  }

  unbanUser(userId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/unban/${userId}`, {});
  }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/getAll`);
  }

  getIsBannedById(userId: number): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}/isbanned/${userId}`);
  }
}

export class UsersService {
}
