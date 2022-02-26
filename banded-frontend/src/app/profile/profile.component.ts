import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Profile, ProfileService } from '../service/profile/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
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
