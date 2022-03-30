import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from 'src/app/class/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient: HttpClient) { }

  public get_timeline() {
    return this.httpClient.get<Post[]>("http://localhost:8080/api/posts");
  }
}
