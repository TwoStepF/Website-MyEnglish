import {Component, OnInit} from '@angular/core';
import {SignIn} from '../../Form/SignIn';
import {Router} from '@angular/router';
import {AuthService} from "../../service/auth.service";
import {MenuItem} from 'primeng/api';
import {Login} from "../../Form/Login";
import {LocalStorageService} from "ngx-webstorage";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private authservice: AuthService, private router: Router) {
  }
  scrollableItems: MenuItem[];

  activeItem: MenuItem;

  activeItem2: MenuItem;
  ngOnInit(): void {
    this.scrollableItems = Array.from({ length: 50 }, (_, i) => ({label: `Tab ${i + 1}`}));

    this.activeItem2 = this.scrollableItems[0];
  }

  public model = new SignIn('', '', '', "");
  private Infor: Login;

  onSubmit(inFor: SignIn): void {
    this.authservice.SignIn(inFor).subscribe(
      data => {
        alert(data.status)
        if(data.status == "ok") {
          alert(data.message);
          this.router.navigateByUrl("/auth/login");
        }else{
          alert(data.message)
        }
      },
      error => {
        alert(error.message);
      }
    );
  }
}
