import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

const Person = () => import('@/entities/person/person.vue');
const PersonUpdate = () => import('@/entities/person/person-update.vue');
const PersonDetails = () => import('@/entities/person/person-details.vue');

const Info = () => import('@/entities/info/info.vue');
const InfoUpdate = () => import('@/entities/info/info-update.vue');
const InfoDetails = () => import('@/entities/info/info-details.vue');

const Employee = () => import('@/entities/employee/employee.vue');
const EmployeeUpdate = () => import('@/entities/employee/employee-update.vue');
const EmployeeDetails = () => import('@/entities/employee/employee-details.vue');

const Department = () => import('@/entities/department/department.vue');
const DepartmentUpdate = () => import('@/entities/department/department-update.vue');
const DepartmentDetails = () => import('@/entities/department/department-details.vue');

const Student = () => import('@/entities/student/student.vue');
const StudentUpdate = () => import('@/entities/student/student-update.vue');
const StudentDetails = () => import('@/entities/student/student-details.vue');

const Lesson = () => import('@/entities/lesson/lesson.vue');
const LessonUpdate = () => import('@/entities/lesson/lesson-update.vue');
const LessonDetails = () => import('@/entities/lesson/lesson-details.vue');

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'person',
      name: 'Person',
      component: Person,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'person/new',
      name: 'PersonCreate',
      component: PersonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'person/:personId/edit',
      name: 'PersonEdit',
      component: PersonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'person/:personId/view',
      name: 'PersonView',
      component: PersonDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'info',
      name: 'Info',
      component: Info,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'info/new',
      name: 'InfoCreate',
      component: InfoUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'info/:infoId/edit',
      name: 'InfoEdit',
      component: InfoUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'info/:infoId/view',
      name: 'InfoView',
      component: InfoDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'employee',
      name: 'Employee',
      component: Employee,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'employee/new',
      name: 'EmployeeCreate',
      component: EmployeeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'employee/:employeeId/edit',
      name: 'EmployeeEdit',
      component: EmployeeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'employee/:employeeId/view',
      name: 'EmployeeView',
      component: EmployeeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department',
      name: 'Department',
      component: Department,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department/new',
      name: 'DepartmentCreate',
      component: DepartmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department/:departmentId/edit',
      name: 'DepartmentEdit',
      component: DepartmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department/:departmentId/view',
      name: 'DepartmentView',
      component: DepartmentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'student',
      name: 'Student',
      component: Student,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'student/new',
      name: 'StudentCreate',
      component: StudentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'student/:studentId/edit',
      name: 'StudentEdit',
      component: StudentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'student/:studentId/view',
      name: 'StudentView',
      component: StudentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lesson',
      name: 'Lesson',
      component: Lesson,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lesson/new',
      name: 'LessonCreate',
      component: LessonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lesson/:lessonId/edit',
      name: 'LessonEdit',
      component: LessonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lesson/:lessonId/view',
      name: 'LessonView',
      component: LessonDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
