import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TopicTimelineComponent } from '../topic-timeline/topic-timeline.component';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.css']
})
export class TopicsComponent implements OnInit {

// this page is to display all the topics

  constructor(private router: Router, private topicTimeline: TopicTimelineComponent) { }

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

  toTopic(topicName: string) {
    this.topicTimeline.load_page(topicName, 0);
    this.router.navigate(['topic'])
    
  }

}
