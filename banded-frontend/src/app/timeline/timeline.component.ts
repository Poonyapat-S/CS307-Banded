import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { delay } from 'rxjs';
import { Post } from '../class/post';
import { TokenService } from '../service/auth/token.service';
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
  private count: number;

  constructor(private router: Router, private profileService: ProfileService, private postService: PostService, private route: ActivatedRoute, private tokenService: TokenService) {this.currProfile = new Profile("", "", "", "", "", ""); this.posts=[], this.count=0}

  ngOnInit() {
    if(this.tokenService.getUser()) {
      this.postService.get_timeline(0).pipe(delay(500)).subscribe((data: Post[]) => {this.posts=data; console.log(data)});
      this.profileService.getProfile().subscribe(data => this.currProfile=data);
    }
    else {
      console.log("guest");
      this.postService.get_guest_timeline().pipe(delay(500)).subscribe((data: Post[]) => this.posts=data);
    }
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


  public getPostService(): PostService {
    return this.postService;
  }

  searchUser(userName:string) {
    if(userName!="") {
      this.router.navigate(['/profile/'+ userName])
      alert('Searchign for profile ' + userName + "...");
    } else {
    alert("Please type a valid username");
    }
  }

  convertDateTime(postTime: Date): string {
    // console.log(postTime);
    // postTime = new Date(postTime);
    // console.log(postTime);
    // return postTime.toISOString().slice(0, 10) + " " + postTime.toISOString().slice(11, 19)
    return formatDate(postTime, 'yyyy/MM/dd hh:mm a', "en-US");

  }
}
