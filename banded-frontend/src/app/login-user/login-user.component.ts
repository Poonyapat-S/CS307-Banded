import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {

  public username: string = "";
  public password: string = "";
  private invalidLogin: boolean = true;
  private loginSuccess: boolean = false;

  constructor(private auth: AuthenticationService) { }

  ngOnInit(): void {
  }

  login(): void {
    this.auth.authenticationService(this.username, this.password).subscribe((result)=> {
      this.invalidLogin = false;
      this.loginSuccess = true;
      alert('Login Successful.');
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
      alert("Invalid Credentials");
    });      
    
  };
}
