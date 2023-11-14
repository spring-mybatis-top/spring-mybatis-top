import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import PersonService from './person/person.service';
import InfoService from './info/info.service';
import EmployeeService from './employee/employee.service';
import DepartmentService from './department/department.service';
import StudentService from './student/student.service';
import LessonService from './lesson/lesson.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('personService') private personService = () => new PersonService();
  @Provide('infoService') private infoService = () => new InfoService();
  @Provide('employeeService') private employeeService = () => new EmployeeService();
  @Provide('departmentService') private departmentService = () => new DepartmentService();
  @Provide('studentService') private studentService = () => new StudentService();
  @Provide('lessonService') private lessonService = () => new LessonService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
