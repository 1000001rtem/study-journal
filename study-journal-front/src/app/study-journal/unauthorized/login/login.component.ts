import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../_service/auth/authentication.service';
import {Role} from '../../_model/role';
import {ActivatedRoute, Router} from '@angular/router';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  MIN_PASSWORD_LENGTH = 6;

  loginForm: FormGroup;
  returnUrl: string;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit(): void {
    this.createForm();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  private createForm(): void {
    this.loginForm = this.fb.group({
        login: this.fb.control(null, [Validators.required, Validators.email]),
        password: this.fb.control(null, [Validators.required, Validators.minLength(this.MIN_PASSWORD_LENGTH)])
      }
    );
  }

  submit = () => {
    this.authenticationService.login(
      this.loginForm.controls.login.value,
      this.loginForm.controls.password.value,
      Role.Teacher
    )
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate([this.returnUrl]);
        },
        error => {
          console.log(error); // todo
        });
  };
}
