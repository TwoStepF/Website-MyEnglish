import {Component, OnInit} from '@angular/core';
import {SignIn} from '../../Form/SignIn';
import {Login} from '../../Form/Login';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';
import {LocalStorageService} from "ngx-webstorage";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router, private localStorageService: LocalStorageService) {
  }

  ngOnInit(): void {

  }

  public model = new Login('', '');


  onSubmit(model: Login) {
    this.authService.login(model).subscribe(
      data => {
        if(data.status == "ok") {
          alert(data.message)
          this.localStorageService.store('authenticationToken', data.authenticationToken);
          this.localStorageService.store('username', data.username);
          this.localStorageService.store('role', data.role);
        }else{
          alert(data.message)
        }
      },
      error => {
        alert("Đăng nhập thất bại")
      }
    )
  }
}
