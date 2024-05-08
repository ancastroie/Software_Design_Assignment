import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private apiUrl = 'http://localhost:3000/questions'; // Use JSON Server endpoint

  constructor(private http: HttpClient) {}

  getQuestions(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  askQuestion(question: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, question);
  }
}
