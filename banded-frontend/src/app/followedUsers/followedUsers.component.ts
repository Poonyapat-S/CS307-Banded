import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../class/post';
import { PostService } from '../service/post/post.service';

@Component({
  selector: 'app-timeline',
  templateUrl: './followedUsers.component.html',
  styleUrls: ['./followedUsers.component.css']
})
export class followedUsersComponent implements OnInit {


  constructor() { }

  ngOnInit(): void {

  }
}