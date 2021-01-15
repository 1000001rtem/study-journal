import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuthorizedModule} from './authorized/authorized.module';
import {UnauthorizedModule} from './unauthorized/unauthorized.module';
import {StudyJournalComponent} from './study-journal.component';


@NgModule({
  declarations: [StudyJournalComponent],
  imports: [
    CommonModule,
    AuthorizedModule,
    UnauthorizedModule
  ],
  exports: [StudyJournalComponent]
})
export class StudyJournalModule { }
