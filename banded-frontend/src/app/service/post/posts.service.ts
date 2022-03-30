import { Injectable } from '@angular/core';
import { Post } from 'src/app/class/post';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private httpClient: HttpClient) { }

  public getPostFromId(id: string) {
    return this.httpClient.get<Post>("http://localhost:8080/api/posts/" + id);
  }
}
