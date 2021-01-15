import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {StudyJournalModule} from './study-journal/study-journal.module';
import {ErrorComponent} from './error/error.component';

@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    StudyJournalModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
