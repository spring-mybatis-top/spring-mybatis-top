import { IInfo } from '@/shared/model/info.model';

export interface IPerson {
  id?: number;
  personName?: string;
  age?: number | null;
  myinfo?: IInfo | null;
}

export class Person implements IPerson {
  constructor(public id?: number, public personName?: string, public age?: number | null, public myinfo?: IInfo | null) {}
}
