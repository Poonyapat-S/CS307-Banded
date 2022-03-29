import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { delay } from 'rxjs';
import { Post } from '../class/post';
import { PostService } from '../service/post/post.service';
import { Profile, ProfileService } from '../service/profile/profile.service';

@Component({
  selector: 'app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.css']
})
export class TimelineComponent implements OnInit {
  public currProfile: Profile;
  public posts: Post[];

  constructor(private profileService: ProfileService, private postService: PostService, private route: ActivatedRoute) {this.currProfile = new Profile("", "", "", "", "", ""); this.posts=[]}

  ngOnInit() {
    this.postService.get_timeline().pipe(delay(500)).subscribe((data: Post[]) => {this.posts=data; console.log(data)});
    this.profileService.getProfile().subscribe(data => this.currProfile=data);
    console.log(this.currProfile)
  }

  public async loadProfile(): Promise<any> {
    this.profileService.getProfile().subscribe((data: Profile) => {
      console.log(data);
      this.currProfile = data
    });
  }

  reloadPage(): void {
    window.location.reload();
  }

  convertDateTime(postTime: Date): string {
    // console.log(postTime);
    // postTime = new Date(postTime);
    // console.log(postTime);
    // return postTime.toISOString().slice(0, 10) + " " + postTime.toISOString().slice(11, 19)
    return formatDate(postTime, 'yyyy/MM/dd hh:mm a', "en-US");
  }

}
