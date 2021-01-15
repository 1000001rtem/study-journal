import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuthorizedComponent} from './authorized.component';
import {MainComponent} from '../unauthorized/main/main.component';
import {LoginComponent} from '../unauthorized/login/login.component';
import {RegistrationComponent} from '../unauthorized/registration/registration.component';


@NgModule({
  declarations: [AuthorizedComponent, MainComponent, LoginComponent, RegistrationComponent],
  imports: [
    CommonModule
  ],
  exports: [AuthorizedComponent]
})
export class AuthorizedModule {
}
