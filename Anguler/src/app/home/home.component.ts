import {Component, OnDestroy, OnInit} from '@angular/core';
import {MessageService} from "../service/message.service";
import {Message} from "../Form/Message";
import {LocalStorageService} from "ngx-webstorage";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  messages: Array<Message>
  SendMess = new Message()
  private doget: number;
  constructor(private messageService: MessageService, private localStorage: LocalStorageService) { }

  ngOnInit(): void {
    this.ngGet()
    this.doget = setInterval(() => {
      this.ngGet()
    }, 2000);
  }

  ngOnDestroy() {
    if (this.doget) {
      clearInterval(this.doget);
    }
  }

  ngGet(){
    this.messageService.getAllMess().subscribe(
      data =>{
        this.messages = data
      }
    )
  }

  ngSubmit(){
    let a = this.localStorage.retrieve('username')
    alert(a)
    if(a != null){
      this.SendMess.username = 'Ẩn danh'
    }else {
      this.SendMess.username = a;
    }
    this.messageService.SendMess(this.SendMess).subscribe(
      data =>{
        this.ngGet()
        this.SendMess.message = ""
      }
    )
  }

}
