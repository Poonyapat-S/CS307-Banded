import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../class/post';
import { PostService } from '../service/post/post.service';

declare function loadRedHeart(): any;
declare function loadGreyHeart(): any;
declare function loadSaveButton(): any;
declare function loadUnsaveButton(): any;

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
      console.log(data);
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

  @HostListener('window:likePost')
  likePost() {
    this.postService.likePost(this.currPost.postID).subscribe({next: response => alert("Liked post!"), error: err => console.log(err)});
  }

  @HostListener('window:unlikePost')
  unlikePost() {
    this.postService.unlikePost(this.currPost.postID).subscribe({next: response => alert("Unliked post!"), error: err => console.log(err)});
  }

  @HostListener('window:savePost')
  savePost() {
    this.postService.savePost(this.currPost.postID).subscribe({next: response => alert("Saved post!"), error: err => console.log(err)});
  }

  @HostListener('window:unsavePost')
  unsavePost() {
    this.postService.unsavePost(this.currPost.postID).subscribe({next: response => alert("Unsaved post!"), error: err => console.log(err)});
  }

  @HostListener('window:checkInteractionStatus')
  checkInteractionStatus() {
    var isLiked = this.postService.getIsLiked(this.currPost.postID);
    var isSaved = this.postService.getIsSaved(this.currPost.postID);
    if (!isLiked) {
      loadGreyHeart();
    } else {
      loadRedHeart();
    }
    if (!isSaved) {
      loadSaveButton();
    } else {
      loadUnsaveButton();
    }
  }
}


