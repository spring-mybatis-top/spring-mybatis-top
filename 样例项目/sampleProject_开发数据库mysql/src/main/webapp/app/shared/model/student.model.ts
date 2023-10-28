import { ILesson } from '@/shared/model/lesson.model';

import { Language } from '@/shared/model/enumerations/language.model';
export interface IStudent {
  id?: number;
  stuName?: string;
  money?: number | null;
  language?: Language | null;
  mylesns?: ILesson[] | null;
}

export class Student implements IStudent {
  constructor(
    public id?: number,
    public stuName?: string,
    public money?: number | null,
    public language?: Language | null,
    public mylesns?: ILesson[] | null
  ) {}
}
