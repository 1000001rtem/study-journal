import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuthorizedComponent} from './authorized.component';
import {AuthorizedRoutingModule} from './authorized-routing.module';


@NgModule({
  declarations: [AuthorizedComponent],
  imports: [
    CommonModule,
    AuthorizedRoutingModule
  ],
  exports: [AuthorizedComponent]
})
export class AuthorizedModule {
}
