import { Component, OnInit } from '@angular/core';
import { HttpClientService, User } from '../service/http-client.service';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {


  user: User = new User("", "", "", "", "");

  constructor(private httpClientService: HttpClientService) { }

  ngOnInit(): void {
  }

  createUser(): void {
    
    this.httpClientService.createUser(this.user);
  };
}
