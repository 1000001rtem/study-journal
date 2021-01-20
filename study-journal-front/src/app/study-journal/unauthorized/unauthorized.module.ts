import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UnauthorizedComponent} from './unauthorized.component';
import {LoginComponent} from './login/login.component';
import {MainComponent} from './main/main.component';
import {RegistrationComponent} from './registration/registration.component';


@NgModule({
  declarations: [UnauthorizedComponent, MainComponent, LoginComponent, RegistrationComponent],
  imports: [
    CommonModule
  ],
  exports: [UnauthorizedComponent]
})
export class UnauthorizedModule {
}
