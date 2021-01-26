import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  MIN_PASSWORD_LENGTH = 6;

  loginForm: FormGroup;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
        login: this.fb.control(null, [Validators.required, Validators.email]),
        password: this.fb.control(null, [Validators.required, Validators.minLength(this.MIN_PASSWORD_LENGTH)])
      }
    );
  }

  submit = () => {

  };

  logs = () => {
    console.log(this.loginForm);
  }
}
