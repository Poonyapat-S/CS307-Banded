import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../class/post';
import { PostService } from '../service/post/post.service';

@Component({
  selector: 'app-timeline',
  templateUrl: './commentViewer.component.html',
  styleUrls: ['./commentViewer.component.css']
})
export class commentViewerComponent implements OnInit {

  public currPost: any;
  constructor() { }

  ngOnInit(): void {

  }
}