import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUserComponent } from './add-user/add-user.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { UserComponent } from './user/user.component';
import { TimelineComponent } from './timeline/timeline.component';

const routes: Routes = [
  { path: '', component: TimelineComponent},
  { path:'view-users', component: UserComponent},
  { path:'adduser', component: AddUserComponent},
  { path: 'login', component: LoginUserComponent},
  { path: 'create-account', component: CreateAccountComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
