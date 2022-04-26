import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Post } from 'src/app/class/post';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CreatePostService {

  constructor(private httpClient: HttpClient) { }

  public create_post(detail: Post) {
    return this.httpClient.post(environment.API_URL+"/api/posts", detail);
  }
}
