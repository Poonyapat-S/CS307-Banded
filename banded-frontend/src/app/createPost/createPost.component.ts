import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../class/post';
import { Profile, ProfileService } from '../service/profile/profile.service';
import { CreatePostService } from '../service/create-post/create-post.service'


declare function makeAnon():void;

@Component({
  selector: 'app-profile',
  templateUrl: './createPost.component.html',
  styleUrls: ['./createPost.component.css']
})

//Important
//Need new Service for createPost below here
//This is just a copy paste version

export class createPostComponent implements OnInit {


  constructor(private createPostService: CreatePostService, private router: Router) { }

  ngOnInit() {

  }
  @HostListener('window:onPost', ['$event.detail'])
  onPost(detail: Post) {
    console.log(detail);
    this.createPostService.create_post(detail).subscribe({next: response => this.router.navigate(['']), error: err => console.log(err)});
  }

}

