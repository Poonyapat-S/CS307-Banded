import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Profile, ProfileService } from '../service/profile/profile.service';
import { TokenService } from '../service/auth/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  public currProfile: Profile;
  public editBioText: string;

  constructor(private profileService: ProfileService, private tokenService: TokenService, private router: Router, private route: ActivatedRoute) {
    this.currProfile = new Profile("", "", "", "", "", "");
    this.editBioText = "";
  }

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
  public editProfile() {
    this.profileService.editBio(this.currProfile.userName, this.editBioText).subscribe({next: response => window.location.reload(), error: err => alert("Unauthorized")});
    // window.location.reload();
  }

  public deleteProfile() {
    this.profileService.deleteProfile(this.currProfile.userName).subscribe({next: response => {alert("Profile Deleted"); this.tokenService.signOut(); this.router.navigate(['']);}, error: err => alert("Unauthorized")});
  }

}
