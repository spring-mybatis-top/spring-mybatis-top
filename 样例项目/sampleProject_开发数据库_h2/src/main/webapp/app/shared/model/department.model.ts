import { IEmployee } from '@/shared/model/employee.model';

export interface IDepartment {
  id?: number;
  deptName?: string;
  empNo?: number | null;
  myEmployees?: IEmployee[] | null;
}

export class Department implements IDepartment {
  constructor(public id?: number, public deptName?: string, public empNo?: number | null, public myEmployees?: IEmployee[] | null) {}
}
