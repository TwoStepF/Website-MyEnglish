import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Login} from "../Form/Login";
import {Observable} from "rxjs";
import {StatusLogin} from "../Form/statusLogin";
import {SignIn} from "../Form/SignIn";
import {Status} from "../Form/status";
import {LocalStorageService} from "ngx-webstorage";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = 'http://localhost:8082/api/auth';

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) {
  }

  login(Infor: Login):  Observable<StatusLogin>{
    return this.http.post<StatusLogin>(this.url + "/login", Infor);
  }

  SignIn(Infor: SignIn):  Observable<Status>{
    return this.http.post<Status>(this.url + "/signin", Infor);
  }
  logout(Infor: Login): Observable<Status>{
    this.localStorageService.clear();
    return this.http.post<Status>(this.url + "/logout", Infor);
  }

}
