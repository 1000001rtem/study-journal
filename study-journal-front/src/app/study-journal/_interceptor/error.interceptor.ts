import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {AuthenticationService} from '../_service/auth/authentication.service';
import {catchError} from 'rxjs/operators';

/**
 * Интерсептор перехватывающий ошибки
 */
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(catchError(err => {
      if ([401, 403].indexOf(err.status) !== -1) {
        this.authenticationService.logout();
        // TODO: redirect to login page
      }

      const error = err.error.message || err.statusText;
      return throwError(error);
    }));
  }
}
