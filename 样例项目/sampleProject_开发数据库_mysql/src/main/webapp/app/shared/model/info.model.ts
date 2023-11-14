import { IPerson } from '@/shared/model/person.model';

export interface IInfo {
  id?: number;
  comments?: string;
  image?: string;
  myperson?: IPerson | null;
}

export class Info implements IInfo {
  constructor(public id?: number, public comments?: string, public image?: string, public myperson?: IPerson | null) {}
}
