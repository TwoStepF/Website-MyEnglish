import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { LocalStorageService } from 'ngx-webstorage';
import { Injectable } from '@angular/core';
import {catchError} from "rxjs/operators";
import {AuthService} from "./service/auth.service";
import {Router} from "@angular/router";

@Injectable()
export class HttpClientInterceptor implements HttpInterceptor {
    constructor(private localStorage: LocalStorageService,  private router: Router) {

    }

  intercept(req: HttpRequest<any>,
            next: HttpHandler): Observable<HttpEvent<any>> {

    const token = this.localStorage.retrieve('authenticationToken');
    if (token) {
      try {
        const cloned = req.clone({
          headers: req.headers.set("Authorization",
            "Bearer " + token)
        });
        return next.handle(cloned);
      }catch (e){
        alert('Hãy đăng nhập lại')
        this.localStorage.clear()
        this.router.navigateByUrl("auth/login")
      }
    }
    return next.handle(req);
  }

}
