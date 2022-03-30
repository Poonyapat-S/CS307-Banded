import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../class/post';
import { PostService } from '../service/post/post.service';

@Component({
  selector: 'app-timeline',
  templateUrl: './postViewingPage.component.html',
  styleUrls: ['./postViewingPage.component.css']
})
export class postViewingPageComponent implements OnInit {

  public currPost: any;
  constructor(private router: Router, private route: ActivatedRoute, private postService: PostService) {this.route.params.subscribe(params => this.loadPost(params['postID']))};

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

  getPostService(): PostService {
    return this.postService;
  }

  @HostListener('window:reply', ['$event.detail'])
  reply(detail: Post) {
    var post = detail;
    post.isAnon=false;
    post.postTitle="Reply to "+this.currPost.postTitle;
    post.postID = this.currPost.postID;
    this.postService.replyPost(post).subscribe({next: response => alert("Replied to post."), error: err => console.log(err)});
  }
}


