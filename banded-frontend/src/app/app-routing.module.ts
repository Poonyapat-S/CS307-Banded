import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUserComponent } from './add-user/add-user.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { UserComponent } from './user/user.component';
import { TimelineComponent } from './timeline/timeline.component';
import { ProfileComponent } from './profile/profile.component';
import { ProfileGetResolver } from './service/profile/profile-get';
import { createPostComponent } from './createPost/createPost.component';
import { topicsPageComponent } from './topicsPage/topicsPage.component';
import { postViewingPageComponent } from './postViewingPage/postViewingPage.component';

const routes: Routes = [
  { path: '', component: TimelineComponent},
  { path:'view-users', component: UserComponent},
  { path:'adduser', component: AddUserComponent},
  { path: 'login', component: LoginUserComponent},
  { path: 'create-account', component: CreateAccountComponent},
  { path: 'profile', component: ProfileComponent, resolve: {profileget: ProfileGetResolver}},
  { path: 'createPost', component: createPostComponent},
  {path: 'topicsPage', component: topicsPageComponent} ,
  {path: 'postViewingPage', component: postViewingPageComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
