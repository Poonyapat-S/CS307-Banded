import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/class/topic';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  constructor(private httpClient: HttpClient) { }

  public getTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(environment.API_URL+"/topics");
  }

  public getUserTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(environment.API_URL+"/api/followcontrol/getfollowedtopics");
  }

  public getCurrentTopic(topicID: string): Observable<Topic> {
    return this.httpClient.get<Topic>(environment.API_URL+"/topics/getTopic/"+topicID);
  }

  public getIsFollowing(topicID: string): Observable<boolean> {
    return this.httpClient.get<boolean>(environment.API_URL+"/api/followcontrol/topic/isFollowing/"+topicID);
  }

  public followTopic(topicName: string) {
    return this.httpClient.post(environment.API_URL+"/api/followcontrol/followtopic", topicName);
  }

  public unFollowTopic(topicName: string) {
    return this.httpClient.post(environment.API_URL+"/api/followcontrol/unfollowtopic", topicName);
  }
}
