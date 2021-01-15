import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CurrentUser} from '../../_model/user';
import {Role} from '../../_model/role';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {environment} from 'src/environments/environment';

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
  getCurrentUser = (): CurrentUser => JSON.parse(this.storage.getItem('currentUser"'));

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
    return this.httpClient.post(environment.apiUrl + '/auth', {
      login,
      password,
      role
    }).pipe(map(user => {
      const currentUser = user as CurrentUser;
      if (currentUser && currentUser.token) {
        this.storage.setItem('currentUser', JSON.stringify(currentUser));
      }
      return currentUser;
    }));
  };

  logout = () => localStorage.removeItem('currentUser');
}
