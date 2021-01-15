import {Role} from './role';

export class User {
  id: string;
  email: string;
  password: string;
  name: string;
  surname: string;
  patronymic: string;
  birthday: Date;
}

export class CurrentUser {
  id: string;
  email: string;
  token: string;
  role: Role;
}
