import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/class/topic';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  constructor(private httpClient: HttpClient) { }

  public getTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>("http://localhost:8080/topics");
  }
}
