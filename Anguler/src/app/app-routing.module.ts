import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./auth/login/login.component";
import {RegisterComponent} from "./auth/register/register.component";
import {HomeComponent} from "./home/home.component";
import {AddVocabularyComponent} from "./add-vocabulary/add-vocabulary.component";
import {AllVocabularyComponent} from "./all-vocabulary/all-vocabulary.component";
import {FindVocabularyComponent} from "./find-vocabulary/find-vocabulary.component";

const routes: Routes = [
  { path: '', pathMatch: 'full' ,component: HomeComponent},
  { path: 'auth/register', component: RegisterComponent },
  { path: 'auth/login', component: LoginComponent },
  { path: 'vocabulary/add', component: AddVocabularyComponent },
  { path: 'vocabulary', component: AllVocabularyComponent },
  { path: 'vocabulary/find', component: FindVocabularyComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
