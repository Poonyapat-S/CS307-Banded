import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Profile, ProfileService } from '../service/profile/profile.service';
import { Post } from '../class/post';
import { PostService } from '../service/post/post.service';

@Component({
  selector: 'app-profile',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.css']
})
export class conversationComponent implements OnInit {
  

  constructor() {  }

  ngOnInit(): void {

  }

}