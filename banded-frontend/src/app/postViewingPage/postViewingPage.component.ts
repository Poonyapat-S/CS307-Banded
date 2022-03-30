import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../class/post';
import { PostsService } from '../service/post/posts.service';

@Component({
  selector: 'app-timeline',
  templateUrl: './postViewingPage.component.html',
  styleUrls: ['./postViewingPage.component.css']
})
export class postViewingPageComponent implements OnInit {

  public currPost: any;
  constructor(private router: Router, private route: ActivatedRoute, private postService: PostsService) {this.route.params.subscribe(params => this.loadPost(params['postID']))};

  ngOnInit(): void {

  }

  showOrSave(data: Post) {
    if(data == null) {
      alert("Post not found");
      this.router.navigate(['']);
    }
    else {
      this.currPost = data;
      console.log(this.currPost);
    }
  }

  loadPost(postID: string) {
    this.postService.getPostFromId(postID).subscribe({next: data => {this.showOrSave(data)}});
  }
}


