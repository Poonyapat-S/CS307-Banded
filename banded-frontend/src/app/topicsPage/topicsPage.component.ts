import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Profile, ProfileService } from '../service/profile/profile.service';


declare function makeAnon():void;

@Component({
  selector: 'app-profile',
  templateUrl: './topicsPage.component.html',
  styleUrls: ['./topicsPage.component.css']
})

//Important
//Need new Service for createPost below here
//This is just a copy paste version
export class topicsPageComponent implements OnInit {
  

  constructor() { }

  ngOnInit() {
   
  }


}
