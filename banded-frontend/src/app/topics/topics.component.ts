import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Topic } from '../class/topic';
import { TopicService } from '../service/topic/topic.service';
import { TopicTimelineComponent } from '../topic-timeline/topic-timeline.component';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.css']
})
export class TopicsComponent implements OnInit {

// this page is to display all the topics
  // this page is to display all the topics

  public topics!: Topic[];
  constructor(public router: Router, private topicTimeline: TopicTimelineComponent, private topicService: TopicService, private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      if(params['following'] == "following") {
        this.topicService.getUserTopics().subscribe({next: data=>this.topics=data, error: err=>alert("Error")});
      }
      else {
        alert("all topics")
        this.topicService.getTopics().subscribe({next: data=>this.topics=data, error: err=>alert("Error")});
      }
    })
  }


  ngOnInit(): void {
    // this.topicService.getTopics().subscribe(data => this.topics = data);
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

  navigate(topicID: string) {
    this.router.navigate(['topic/'+topicID]);
  }

}
