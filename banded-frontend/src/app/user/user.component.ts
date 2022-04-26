import { Component, OnInit } from '@angular/core';
import { HttpClientService, User } from '../service/http-client.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users:User[];

  constructor(private httpClientService:HttpClientService) { this.users = [];}

  

  ngOnInit(): void {
    this.httpClientService.getUsers().subscribe(response => {this.users = response});
  }

  deleteUser(user: User): void {
    this.httpClientService.deleteUsers(user).subscribe( data => {this.users = this.users.filter(u => u !== user)})
  }

}
