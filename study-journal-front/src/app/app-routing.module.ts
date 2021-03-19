import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ErrorComponent} from './error/error.component';
import {MainComponent} from './study-journal/unauthorized/main/main.component';
import {LoginComponent} from './study-journal/unauthorized/login/login.component';
import {RegistrationComponent} from './study-journal/unauthorized/registration/registration.component';
import {AuthGuard} from './study-journal/_service/auth/auth.guard';

const routes: Routes = [
  {
    path: 'main',
    component: MainComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'authorized',
    canActivate: [AuthGuard],
    loadChildren: () => import('./study-journal/authorized/authorized.module').then(m => m.AuthorizedModule)
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/main'
  },
  {
    path: '404',
    component: ErrorComponent
  },
  {
    path: '**',
    redirectTo: '/404'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
