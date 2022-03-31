import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Profile, ProfileService } from '../service/profile/profile.service';
import { Post } from '../class/post';
import { PostService } from '../service/post/post.service';

@Component({
  selector: 'app-timeline',
  templateUrl: './followedUsers.component.html',
  styleUrls: ['./followedUsers.component.css']
})
export class followedUsersComponent implements OnInit {
  public userNames: String[];

  constructor(private profileService: ProfileService) { this.userNames = []; this.getFollowedUserNames(); }

  ngOnInit(): void {

  }

  getFollowedUserNames() {
    this.profileService.getFollowedUserNames().subscribe(data => this.userNames=data);
  }


}
