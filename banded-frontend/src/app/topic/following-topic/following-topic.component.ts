import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-following-topic',
  templateUrl: './following-topic.component.html',
  styleUrls: ['./following-topic.component.css']
})
export class FollowingTopicComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  navigate(topicID: string) {
    this.router.navigate(['topic/'+topicID]);
  }

}
