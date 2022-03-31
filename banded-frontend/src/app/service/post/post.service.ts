import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from 'src/app/class/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient: HttpClient) { }

  public get_timeline(count: number) {
    return this.httpClient.get<Post[]>("http://localhost:8080/api/posts/timeline?count="+count.toString());
  }

  public get_topic_timeline(topicName:string, count: number) {
    return this.httpClient.get<Post[]>("http://localhost:8080/api/posts/topic/"+topicName+"?count="+count.toString());
  }
  public get_guest_timeline() {
    return this.httpClient.get<Post[]>("http://localhost:8080/api/posts/guest/timeline");
  }

  public convertDateTime(postTime: Date): string {
    // console.log(postTime);
    // postTime = new Date(postTime);
    // console.log(postTime);
    // return postTime.toISOString().slice(0, 10) + " " + postTime.toISOString().slice(11, 19)
    return formatDate(postTime, 'yyyy/MM/dd hh:mm a', "en-US");
  }

  public getPostFromId(id: string) {
    return this.httpClient.get<Post>("http://localhost:8080/api/posts/" + id);
  }

  public replyPost(post: Post) {
    return this.httpClient.post("http://localhost:8080/api/posts/"+post.postID, post);
  }

  public getIsLiked(id : number) {
    return this.httpClient.get("http://localhost:8080/api/interaction/getlikestatus" + id.toString());
  }

  public likePost(id : number) {
    return this.httpClient.post("http://localhost:8080/api/interaction/like", id);
  }

  public unlikePost(id : number) {
    return this.httpClient.post("http://localhost:8080/api/interaction/unlike", id);
  }

  public getIsSaved(id : number) {
    return this.httpClient.get("http://localhost:8080/api/interaction/getsavestatus" + id.toString());
  }

  public savePost(id : number) {
    return this.httpClient.post("http://localhost:8080/api/interaction/savepost", id);
  }

  public unsavePost(id : number) {
    return this.httpClient.post("http://localhost:8080/api/interaction/unsavepost", id);
  }
}
