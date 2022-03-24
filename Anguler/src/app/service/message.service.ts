import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Message} from "../Form/Message";


@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private url = 'http://localhost:8082/api/message';

  constructor(private http: HttpClient) { }

  getAllMess(): Observable<Array<Message>> {
    return this.http.get<Array<Message>>(this.url)
  }

  SendMess(Infor: Message): Observable<any> {
    return this.http.post(this.url + "/send", Infor)
  }
}
