import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UnauthorizedComponent} from './unauthorized.component';


@NgModule({
  declarations: [UnauthorizedComponent],
  imports: [
    CommonModule
  ],
  exports: [UnauthorizedComponent]
})
export class UnauthorizedModule { }
