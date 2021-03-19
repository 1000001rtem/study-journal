import {User} from './user';
import {Mark} from './mark';

export class Student extends User {
  group: string;
  marks: Mark[];
}
