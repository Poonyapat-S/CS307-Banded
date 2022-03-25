import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Profile, ProfileService } from '../service/profile/profile.service';


declare function makeAnon():void;

@Component({
  selector: 'app-profile',
  templateUrl: './createPost.component.html',
  styleUrls: ['./createPost.component.css']
})

//Important
//Need new Service for createPost below here
export class createPostComponent implements OnInit {
  public currProfile: Profile;

  constructor(private profileService: ProfileService, private route: ActivatedRoute) {this.currProfile = new Profile("", "", "", "", "", ""); }

  ngOnInit() {
    this.currProfile = this.route.snapshot.data['profileget'];
    console.log(this.currProfile)
  }

  public async loadProfile(): Promise<any> {
    this.profileService.getProfile().subscribe((data: Profile) => {
      console.log(data);
      this.currProfile = data
    });
  }

}

