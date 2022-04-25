import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Injector, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { AddUserComponent } from './add-user/add-user.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { TimelineComponent } from './timeline/timeline.component';
import { ProfileComponent } from './profile/profile.component';
import { commentViewerComponent } from './commentViewer/commentViewer.component';
import { followedUsersComponent } from './followedUsers/followedUsers.component';
import { createPostComponent } from './createPost/createPost.component';
import { mySavedPostsComponent } from './mySavedPosts/mySavedPosts.component';
import { dmPageComponent } from './dmPage/dmPage.component';


import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { authInterceptorProviders } from './_helper/auth.interceptor';
import { UnauthInterceptorProvider } from './_helper/unauth.interceptor';
import { TopicsComponent } from './topics/topics.component';
import { TopicTimelineComponent, TopicTimelineComponentProvider } from './topic-timeline/topic-timeline.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { FollowingTopicComponent } from './topic/following-topic/following-topic.component';
import { conversationComponent } from './conversation/conversation.component';
@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AddUserComponent,
    HeaderComponent,
    FooterComponent,
    LoginUserComponent,
    CreateAccountComponent,
    TimelineComponent,
    ProfileComponent,
    createPostComponent,
    TopicsComponent,
    TopicTimelineComponent,
    commentViewerComponent,
    followedUsersComponent,
    mySavedPostsComponent,
    ErrorPageComponent,
    FollowingTopicComponent,
    dmPageComponent,
    conversationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
  ],
  providers: [authInterceptorProviders, UnauthInterceptorProvider, TopicTimelineComponentProvider],
  bootstrap: [AppComponent],
})
export class AppModule { }
