import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UnauthorizedComponent} from './unauthorized.component';
import {LoginComponent} from './login/login.component';
import {MainComponent} from './main/main.component';
import {RegistrationComponent} from './registration/registration.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';


@NgModule({
  declarations: [UnauthorizedComponent, MainComponent, LoginComponent, RegistrationComponent],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatIconModule,
    ReactiveFormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule
  ],
  exports: [UnauthorizedComponent]
})
export class UnauthorizedModule {
}
