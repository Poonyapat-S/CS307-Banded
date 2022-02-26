import { Component, OnInit } from '@angular/core';
import { Profile, ProfileService } from '../service/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  public currProfile: Profile;

  constructor(private profileService: ProfileService) {this.currProfile = new Profile("", "", "", "", "", ""); }

  async ngOnInit() {
    await this.loadProfile();
    console.log(this.currProfile)
  }

  public async loadProfile() {
    this.profileService.getProfile().subscribe((data: Profile) => {
      console.log(data);
      this.currProfile = data
    });
  }

}
