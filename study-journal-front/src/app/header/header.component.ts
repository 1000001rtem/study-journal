import {Component, DoCheck, OnInit} from '@angular/core';
import {AuthenticationService} from '../study-journal/_service/auth/authentication.service';
import {CurrentUser} from '../study-journal/_model/user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, DoCheck {
  currentUser: CurrentUser;

  constructor(
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit(): void {
    this.currentUser = this.authenticationService.getCurrentUser();
  }

  ngDoCheck(): void {
    this.currentUser = this.authenticationService.getCurrentUser();
  }

  logout = () => this.authenticationService.logout();

}
