import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {StorageService} from "../../auth-service/storage-service/storage.service";

const BASIC_URL=["http://localhost:8080/"]
@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  constructor(private http: HttpClient) {
  }


  postAnswer(answerDto: any): Observable<any> {
    answerDto.authorid = StorageService.getUserId();
    console.log(answerDto);
    return this.http.post<[]>(BASIC_URL + "answers/addAnswer", answerDto)
  }

  getAnswersByQuestionid(questionid: number): Observable<any[]> {
    // @ts-ignore
    return this.http.get<any[]>(BASIC_URL + `answers/getByQuestionId/${questionid}`);
  }

  addVoteToAnswer(voteAnswerDto: any): Observable<any> {
    voteAnswerDto.userid = StorageService.getUserId();
    console.log(voteAnswerDto);
    return this.http.post<[]>(BASIC_URL + "votes/answer/addVote", voteAnswerDto);
  }

  deleteAnswerById(id: number): Observable<any> {
    return this.http.delete<any>(BASIC_URL + `answers/deleteById?id=${id}`);
  }

  getAnswerById(answerid: number): Observable<any> {
    return this.http.get<any>(BASIC_URL + `answers/getById/${answerid}`);
  }

  // @ts-ignore
  updateAnswer(answerid: any, updatedAnswer: any): Observable<any> {
    return this.http.put<any>(BASIC_URL + `answers/update/${answerid}`, updatedAnswer);

  }
}
