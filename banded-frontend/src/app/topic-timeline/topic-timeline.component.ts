import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { delay } from 'rxjs';
import { Post } from '../class/post';
import { Topic } from '../class/topic';
import { PostService } from '../service/post/post.service';
import { Profile, ProfileService } from '../service/profile/profile.service';
import { TopicService } from '../service/topic/topic.service';

@Component({
  selector: 'app-topic-timeline',
  templateUrl: './topic-timeline.component.html',
  styleUrls: ['./topic-timeline.component.css']
})
export class TopicTimelineComponent implements OnInit {
  public currProfile: Profile;
  public currTopic: Topic;
  public posts: Post[];
  public isFollowing: boolean;
  private count: number;

  constructor(private profileService: ProfileService, private topicService: TopicService, private postService: PostService, private route: ActivatedRoute, private router: Router) {
    this.currProfile = new Profile("", "", "", "", "","");
    this.currTopic = new Topic("", "");
    this.posts=[];
    this.count=0;
    this.isFollowing = false;
  }

  ngOnInit(): void {
    var topicID="";
    this.route.params.subscribe(params => topicID=params["topicID"]);
    this.topicService.getCurrentTopic(topicID).pipe(delay(500)).subscribe(data => {this.currTopic=data});
    this.topicService.getIsFollowing(topicID).subscribe(data => this.isFollowing=data);
    this.postService.get_topic_timeline(topicID, this.count).pipe(delay(500)).subscribe((data: Post[]) => {this.posts=data; console.log(data);});
    this.profileService.getProfile().subscribe(data => this.currProfile=data);
  }

  public load_page(topicName: string, count: number) {
    this.postService.get_topic_timeline(topicName, this.count).pipe(delay(500)).subscribe((data: Post[]) => {this.posts=data; console.log(data);});
    this.profileService.getProfile().subscribe(data => this.currProfile=data);
    console.log(this.currProfile)
  }

  public async loadProfile(): Promise<any> {
    this.profileService.getProfile().subscribe((data: Profile) => {
      console.log(data);
      this.currProfile = data
    });
  }

  reloadPage(): void {
    window.location.reload();
  }


  public getPostService(): PostService {
    return this.postService;
  }

  searchUser(title:string) {
    //also check if username is valid
    if(title!="") {
     alert(title);
    } else {
    alert("Please type a valid username");
    }
  }

  convertDateTime(postTime: Date): string {
    // console.log(postTime);
    // postTime = new Date(postTime);
    // console.log(postTime);
    // return postTime.toISOString().slice(0, 10) + " " + postTime.toISOString().slice(11, 19)
    return formatDate(postTime, 'yyyy/MM/dd hh:mm a', "en-US");

  }

  public unFollowTopic() {
    this.topicService.unFollowTopic(this.currTopic.topicName).subscribe();
    this.isFollowing = false;
  }

  public followTopic() {
    this.topicService.followTopic(this.currTopic.topicName).subscribe();
    this.isFollowing=true;
  }


}
export const TopicTimelineComponentProvider = [
  { provide: TopicTimelineComponent, useClass: TopicTimelineComponent, multi: true }
];
