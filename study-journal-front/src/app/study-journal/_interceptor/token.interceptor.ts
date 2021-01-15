import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthenticationService} from '../_service/authentication.service';
import {environment} from 'src/environments/environment';

/**
 * Интерсептор, добавляющий токен авторизации в хедеры запроса
 */
@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const currentUser = this.authenticationService.getCurrentUser();
    const isApiUrl = request.url.startsWith(environment.apiUrl);
    if (currentUser && currentUser.token && isApiUrl) {
      request.clone({
        setHeaders: {
          Authorization: `Bearer ${currentUser.token}`
        }
      });
    }
    return next.handle(request);
  }
}
