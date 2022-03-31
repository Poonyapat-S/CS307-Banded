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

  public getUserTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>("http://localhost:8080/api/followcontrol/getfollowedtopics");
  }

  public getCurrentTopic(topicID: string): Observable<Topic> {
    return this.httpClient.get<Topic>("http://localhost:8080/topics/getTopic/"+topicID);
  }

  public getIsFollowing(topicID: string): Observable<boolean> {
    return this.httpClient.get<boolean>("http://localhost:8080/api/followcontrol/topic/isFollowing/"+topicID);
  }

  public followTopic(topicName: string) {
    return this.httpClient.post("http://localhost:8080/api/followcontrol/followtopic", topicName);
  }

  public unFollowTopic(topicName: string) {
    return this.httpClient.post("http://localhost:8080/api/followcontrol/unfollowtopic", topicName);
  }
}
