import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Post } from 'src/app/class/post';

@Injectable({
  providedIn: 'root'
})
export class CreatePostService {

  constructor(private httpClient: HttpClient) { }

  public create_post(detail: Post) {
    return this.httpClient.post("http://localhost:8080/api/posts", detail);
  }
}
