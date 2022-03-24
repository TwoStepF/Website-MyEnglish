import { Component, OnInit } from '@angular/core';
import {Vocabulary} from "../Form/Vocabulary";
import {VocabularyService} from "../service/vocabulary.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-all-vocabulary',
  templateUrl: './all-vocabulary.component.html',
  styleUrls: ['./all-vocabulary.component.css']
})
export class AllVocabularyComponent implements OnInit {
  vocabularys: Observable<Array<Vocabulary>>;

  constructor(private vocabularyService: VocabularyService) { }

  ngOnInit(): void {
    this.vocabularys = this.vocabularyService.AllVocabulary()
  }

}
