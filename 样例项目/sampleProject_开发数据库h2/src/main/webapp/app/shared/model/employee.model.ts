import { IDepartment } from '@/shared/model/department.model';

export interface IEmployee {
  id?: number;
  empName?: string;
  salary?: number | null;
  myDept?: IDepartment | null;
}

export class Employee implements IEmployee {
  constructor(public id?: number, public empName?: string, public salary?: number | null, public myDept?: IDepartment | null) {}
}
