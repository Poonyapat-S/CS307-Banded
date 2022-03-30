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


import { createPostComponent } from './createPost/createPost.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { authInterceptorProviders } from './_helper/auth.interceptor';
import { UnauthInterceptorProvider } from './_helper/unauth.interceptor';
import { TopicsComponent } from './topics/topics.component';
import { TopicTimelineComponent } from './topic-timeline/topic-timeline.component';
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
    TopicTimelineComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
  ],
  providers: [authInterceptorProviders, UnauthInterceptorProvider],
  bootstrap: [AppComponent],
})
export class AppModule { }
