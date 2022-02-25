import { Component, OnInit } from '@angular/core';
import { CreateUserService, NewUser } from '../service/CreateAccount/create-user-service.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  user: NewUser = new NewUser("", "", "", "", "");

  constructor(private httpClientService: CreateUserService) { }

  ngOnInit(): void {
  }

  createUser(): void {

    if (this.user.name == "") {
      alert("Name can't be empty!!");
      return;
    }
    if (this.user.userName == "") {
      alert("Username can't be empty!!");
      return;
    }
    if (this.user.email == "") {
      alert("Email can't be empty!!");
      return;
    }
    if (this.user.password == "") {
      alert("Password can't be empty!!");
      return;
    }
    if (this.user.confPassword != this.user.password) {
      alert("Password doesn't match");
      return;
    }
    if(this.user.password.length < 8 || this.user.password.length > 24) {
      alert("Password length should be between 8 to 24 characters");
      return;
    }
    this.httpClientService.createUser(this.user).subscribe({next: data => alert("Registration Successful!"), error: err => alert(err.error)});
  };
}
