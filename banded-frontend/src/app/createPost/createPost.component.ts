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
//This is just a copy paste version
export class createPostComponent implements OnInit {
  

  constructor() { }

  ngOnInit() {
   
  }


}

