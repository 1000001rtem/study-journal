import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UnauthorizedComponent} from './unauthorized.component';
import {LoginComponent} from './login/login.component';


@NgModule({
  declarations: [UnauthorizedComponent, LoginComponent],
  imports: [
    CommonModule
  ],
  exports: [UnauthorizedComponent]
})
export class UnauthorizedModule { }
