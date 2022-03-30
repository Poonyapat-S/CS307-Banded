import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.css']
})
export class TopicsComponent implements OnInit {

// this page is to display all the topics

  constructor() { }

  ngOnInit(): void {
  }

  toggle = true;
  //both buttons change color together i am not smart enough to fix this
  status = 'Disable'; 

  // add a pass to this function that will let it differentiate which topic gets updated
  followUpdate(topicID: string, topicName: string) {
      this.toggle = !this.toggle;
      this.status = this.toggle ? 'Enable' : 'Disable';

      // if user wanst following topic (needs backend data)
      if(this.status == 'Enable') {
        //add a backend fn to follow user to topic
        alert("Followed topic " + topicName);
        return;
      } else {
        //add a backend fn to unfollow user from topic
        alert("Unfollowed topic "  + topicName);
        return;
      }
  }

}
