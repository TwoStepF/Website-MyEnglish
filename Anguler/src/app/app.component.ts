import {Component,OnInit} from '@angular/core';
import {LocalStorageService} from "ngx-webstorage";
import {Login} from "./Form/Login";
import {AuthService} from "./service/auth.service";



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  private Infor: Login = new Login(this.localStorage.retrieve('username'), "");
  constructor(public localStorage: LocalStorageService, public authService: AuthService) {
  }

  ngOnInit(): void {

  }

  logout(){
    alert(this.Infor.username)
    this.authService.logout(this.Infor).subscribe(
      data => {
        if(data.status = "ok"){
          alert(data.message)
        }else {
          alert(data.message)
        }
      },
      error =>{
        alert(error.message())
      }
    );
  }

  logtest() {
    alert("fsafa")
  }
}
