import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {StorageService} from "../auth-service/storage-service/storage.service";

const BASIC_URL=["http://localhost:8080/"]
@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  constructor(private http:HttpClient) {

  }
  // @ts-ignore
  postQuestion(questionDto:any):Observable<any> {
    return this.http.post<[]>(BASIC_URL+"questions/addQuestion",questionDto)
  }

  getQuestions(): Observable<any[]> {
    return this.http.get<any[]>(BASIC_URL + "questions/getAll");
  }

  // @ts-ignore
  getQuestionById(questionid:number):Observable<any>{
    return this.http.get<any[]>(BASIC_URL + `questions/getById/${questionid}`);
  }

  addVoteToQuestions(voteQuestionDto:any):Observable<any> {
    voteQuestionDto.userid=StorageService.getUserId();
    console.log(voteQuestionDto);
    return this.http.post<[]>(BASIC_URL+"votes/question/addVote",voteQuestionDto);
  }

  updateQuestion(questionId: number, updatedQuestion: any): Observable<any> {
    return this.http.put<any>(BASIC_URL + `questions/update/${questionId}`, updatedQuestion);
  }

  // @ts-ignore
  deleteQuestionById(id: number):Observable<any> {
    return this.http.delete<any>(BASIC_URL+`questions/deleteById?id=${id}`);
  }

  filterQuestions(tag: string, searchText: string, userId: number): Observable<any[]> {
    const queryParams: any = {};

    // Add tag query parameter if provided
    if (tag && tag.trim() !== '') {
      queryParams.tag = tag.trim();
    }

    // Add searchText query parameter if provided
    if (searchText && searchText.trim() !== '') {
      queryParams.searchText = searchText.trim();
    }

    // Add userId query parameter if provided
    if (userId && userId > 0) {
      queryParams.userId = userId;
    }

    return this.http.get<any[]>(BASIC_URL + 'questions/filter', { params: queryParams });
  }

  searchQuestionsByTitle(searchText: string): Observable<any[]> {
    return this.http.get<any[]>(BASIC_URL + 'questions/searchByTitle', {
      params: { searchText }
    });
  }
}
