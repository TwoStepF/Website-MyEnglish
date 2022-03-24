import { Component, OnInit } from '@angular/core';
import {Vocabulary} from "../Form/Vocabulary";
import {AuthService} from "../service/auth.service";
import {VocabularyService} from "../service/vocabulary.service";
import {dashCaseToCamelCase} from "@angular/compiler/src/util";

@Component({
  selector: 'app-add-vocabulary',
  templateUrl: './add-vocabulary.component.html',
  styleUrls: ['./add-vocabulary.component.css']
})
export class AddVocabularyComponent implements OnInit {

  constructor(private vocabularyService: VocabularyService) { }
  Infor= new Vocabulary();
  ngOnInit(): void {
  }
  ngSubmit(){
    this.vocabularyService.addVocabulary(this.Infor).subscribe(
      data =>{
        alert(data.message)
      },
      error => {
        alert(error.message)
      }
    );
  }

}
