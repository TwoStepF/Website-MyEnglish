import { Component, OnInit } from '@angular/core';
import {Vocabulary} from "../Form/Vocabulary";
import {VocabularyService} from "../service/vocabulary.service";
import {dashCaseToCamelCase} from "@angular/compiler/src/util";
import {StatusVocabulary} from "../Form/StatusVocabulary";
import {Router} from "@angular/router";

@Component({
  selector: 'app-find-vocabulary',
  templateUrl: './find-vocabulary.component.html',
  styleUrls: ['./find-vocabulary.component.css']
})
export class FindVocabularyComponent implements OnInit {
  Infor = new Vocabulary();
  Result: Array<StatusVocabulary>;
  bl = true;
  bl2 = false;
  Result2: Array<StatusVocabulary>;
  constructor(private vocabularyService: VocabularyService, private router: Router) { }

  ngOnInit(): void {
  }


  ngSubmit() {
    this.vocabularyService.findVocabularyByEnglish(this.Infor).subscribe(
      data => {
        if(data[0].status == "ok"){
          this.Result = data;
        }else {
          if(window.confirm(data[0].message)) {
            this.router.navigateByUrl('vocabulary/add')
          }
        }
      },
      error => {
        alert(error.message)
      }
    )
  }

  ngChange() {
    if(this.bl) {
      this.bl = false;
    }else
      this.bl = true;
  }

  ngSubmitV() {
    this.vocabularyService.findVocabularyByVietnam(this.Infor).subscribe(
      data => {
        if(data[0].status == "ok"){
            this.Result2 = data;
        }else {
          if(window.confirm(data[0].message)) {
              this.router.navigateByUrl('vocabulary/add')
          }
        }
      },
      error => {
        alert(error.message)
      }
    )
  }
}
