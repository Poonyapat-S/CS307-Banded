import { Component, OnInit } from '@angular/core';
import { HttpClientService, User } from '../service/http-client.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  user: User = new User("", "", "", "");

  constructor(private httpClientService: HttpClientService) { }

  ngOnInit(): void {
  }

  createUser(): void {
    this.httpClientService.createUser(this.user).subscribe(data => {alert("User created successfully")});
  };
}
