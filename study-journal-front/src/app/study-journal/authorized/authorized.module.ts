import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuthorizedComponent} from './authorized.component';
import {MainComponent} from './main/main.component';
import {AuthorizationComponent} from './authorization/authorization.component';
import {RegistrationComponent} from './registration/registration.component';


@NgModule({
  declarations: [AuthorizedComponent, MainComponent, AuthorizationComponent, RegistrationComponent],
  imports: [
    CommonModule
  ],
  exports: [AuthorizedComponent]
})
export class AuthorizedModule { }
