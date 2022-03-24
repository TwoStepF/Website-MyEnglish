import { Injectable } from '@angular/core';
import {Vocabulary} from "../Form/Vocabulary";
import {Observable} from "rxjs";
import {Status} from "../Form/status";
import {HttpClient} from "@angular/common/http";
import {StatusVocabulary} from "../Form/StatusVocabulary";

@Injectable({
  providedIn: 'root'
})
export class VocabularyService {
  private url = 'http://localhost:8082/api/Vocabulary';
  constructor(private http: HttpClient) { }
  addVocabulary(Infor: Vocabulary): Observable<Status>{
    return this.http.post<Status>(this.url + "/new", Infor);
  }
  AllVocabulary(): Observable<Array<Vocabulary>>{
    return this.http.get<Array<Vocabulary>>(this.url);
  }

  findVocabularyByEnglish(Infor: Vocabulary): Observable<Array<StatusVocabulary>> {
    return this.http.post<Array<StatusVocabulary>>(this.url + "/find/english", Infor)
  }

  findVocabularyByVietnam(Infor: Vocabulary): Observable<Array<StatusVocabulary>> {
    return this.http.post<Array<StatusVocabulary>>(this.url + "/find/vietnam", Infor)
  }
}
