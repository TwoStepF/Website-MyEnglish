import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {InputTextModule} from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {Ng2Webstorage} from 'ngx-webstorage';
import {DropdownModule} from 'primeng/dropdown';
import {EditorModule} from 'primeng/editor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {ButtonModule} from 'primeng/button';
import {CheckboxModule} from 'primeng/checkbox';
import {HttpClientInterceptor} from "./htttp-client";
import { AuthComponent } from './auth/auth.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { HomeComponent } from './home/home.component';
import {TabMenuModule} from 'primeng/tabmenu';
import { AddVocabularyComponent } from './add-vocabulary/add-vocabulary.component';
import { AllVocabularyComponent } from './all-vocabulary/all-vocabulary.component';
import { FindVocabularyComponent } from './find-vocabulary/find-vocabulary.component';
import {RadioButtonModule} from 'primeng/radiobutton';
@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    AddVocabularyComponent,
    AllVocabularyComponent,
    FindVocabularyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    InputTextModule,
    FormsModule,
    HttpClientModule,
    Ng2Webstorage.forRoot(),
    DropdownModule,
    EditorModule,
    BrowserAnimationsModule,
    ButtonModule,
    CheckboxModule,
    TabMenuModule,
    RadioButtonModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpClientInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
