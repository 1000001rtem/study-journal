import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthorizedComponent} from './authorized.component';

const routes: Routes = [
  {
    path: '',
    // canActivate: [AuthGuard],
    component: AuthorizedComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthorizedRoutingModule {
}
