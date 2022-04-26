import { Component, OnInit } from '@angular/core';
import { AuthenticationService, Credentials } from '../service/auth/authentication.service';
import { TokenService } from '../service/auth/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {

  public credentials: Credentials = new Credentials("", "");
  submitted = false;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];


  constructor(private router: Router, private auth: AuthenticationService, private tokenService: TokenService) { }

  ngOnInit(): void {
  }

  login(): void {
    this.auth.login(this.credentials).subscribe({next: (data) => {this.tokenService.saveToken(data.accessToken);
        this.tokenService.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenService.getUser().roles;
        this.submitted = true;
        alert("Login Successful");
        this.router.navigate(['profile'])},
      error: (err) => {
        console.log(err.status);
        console.log(err);
        this.errorMessage = err.error;
        this.submitted = true;
        this.isLoginFailed = true;
      }})

  };

  reloadPage(): void {
    window.location.reload();
  }

}
