import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CurrentUser} from '../../_model/user';
import {Role} from '../../_model/role';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {environment} from 'src/environments/environment';
import {Response} from '../../_model/response';

/**
 * Сервис аутентификации
 */
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private storage;

  constructor(private httpClient: HttpClient) {
    this.storage = window.localStorage;
  }

  /**
   * Предоставление текущего авторизированный пользователя
   * @returns текущий авторизированный пользователь
   */
  getCurrentUser(): CurrentUser {
    const u = this.storage.getItem('currentUser');
    return JSON.parse(u);
  }

  /**
   * Авторизация
   *
   * @param login логин пользователя
   * @param password пароль пользователя
   * @param role роль пользователя {@link Role}
   *
   * @returns Observable<CurrentUser> авторизованного пользовтеля
   */
  login = (login: string, password: string, role: Role): Observable<CurrentUser> => {
    return this.httpClient.post(environment.apiUrl + '/v1/auth', {
      login,
      password,
      role
    }).pipe(map((response: Response) => {
      const currentUser = response.data as CurrentUser;
      if (currentUser && currentUser.token) {
        this.storage.setItem('currentUser', JSON.stringify(currentUser));
      }
      return currentUser;
    }));
  };

  logout = () => localStorage.removeItem('currentUser');
}
