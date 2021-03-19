import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UnauthorizedModule} from './unauthorized/unauthorized.module';
import {StudyJournalComponent} from './study-journal.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ErrorInterceptor} from './_interceptor/error.interceptor';
import {TokenInterceptor} from './_interceptor/token.interceptor';
import {AppRoutingModule} from '../app-routing.module';


@NgModule({
  declarations: [StudyJournalComponent],
  imports: [
    CommonModule,
    UnauthorizedModule,
    AppRoutingModule,
    HttpClientModule
  ],
  exports: [StudyJournalComponent],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ]
})
export class StudyJournalModule {
}
