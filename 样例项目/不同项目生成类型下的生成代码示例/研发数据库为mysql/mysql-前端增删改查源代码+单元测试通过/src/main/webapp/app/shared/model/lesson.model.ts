import { IStudent } from '@/shared/model/student.model';

export interface ILesson {
  id?: number;
  lesnName?: string;
  teacherName?: string;
  mystdts?: IStudent[] | null;
}

export class Lesson implements ILesson {
  constructor(public id?: number, public lesnName?: string, public teacherName?: string, public mystdts?: IStudent[] | null) {}
}
