import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUserComponent } from './add-user/add-user.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { UserComponent } from './user/user.component';
import { TimelineComponent } from './timeline/timeline.component';
import { ProfileComponent } from './profile/profile.component';
import { createPostComponent } from './createPost/createPost.component';
import { postViewingPageComponent } from './postViewingPage/postViewingPage.component';
import { TopicsComponent } from './topics/topics.component';
import { TopicTimelineComponent } from './topic-timeline/topic-timeline.component';
import { commentViewerComponent } from './commentViewer/commentViewer.component';
import { followedUsersComponent } from './followedUsers/followedUsers.component';
import { mySavedPostsComponent } from './mySavedPosts/mySavedPosts.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { dmPageComponent } from './dmPage/dmPage.component';
import { conversationComponent } from './conversation/conversation.component';

const routes: Routes = [
  { path: '', component: TimelineComponent},
  { path:'view-users', component: UserComponent},
  { path:'adduser', component: AddUserComponent},
  { path: 'login', component: LoginUserComponent},
  { path: 'create-account', component: CreateAccountComponent},
  { path: 'profile', component: ProfileComponent},
  { path: 'createPost', component: createPostComponent},
  {path: 'postViewingPage/:postID', component: postViewingPageComponent},
  { path: 'topics', component: TopicsComponent},
  { path: 'topic/:topicID', component: TopicTimelineComponent},
  { path: 'topics/:following', component: TopicsComponent},
  { path: 'commentViewer', component: commentViewerComponent},
  {path: 'followedUsers', component: followedUsersComponent},
  {path: 'mySavedPosts', component: mySavedPostsComponent},
  { path: 'profile/:userName', component: ProfileComponent},
  { path: 'dmPage', component: dmPageComponent },
  { path: 'conversation', component: conversationComponent}

  // // put all other paths above
  // { path: '404', component: ErrorPageComponent},
  // { path: '**', redirectTo: '404'}, // if page not found go to timeline
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
