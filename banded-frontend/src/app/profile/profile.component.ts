import { Component, OnInit } from '@angular/core';


declare function openform():void;
declare function closeform():void;
declare function submitForm():void;
declare function openformDelete():void;
declare function closeformDelete():void;
declare function submitDeletion():void;
declare const val:string;
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  
  

  ngOnInit(): void {
  }

}
