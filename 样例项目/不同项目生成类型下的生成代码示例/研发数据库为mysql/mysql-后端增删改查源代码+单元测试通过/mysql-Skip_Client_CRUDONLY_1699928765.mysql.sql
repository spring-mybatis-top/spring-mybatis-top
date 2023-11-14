--  Changeset config/liquibase/changelog/20111111000100_added_entity_Person.xml::20111111000100-1::jhipster
CREATE TABLE
  sampleproject_mysql.person (
    id BIGINT AUTO_INCREMENT NOT NULL,
    person_name VARCHAR(255) NOT NULL,
    age BIGINT NULL,
    myinfo_id BIGINT NULL,
    CONSTRAINT PK_PERSON PRIMARY KEY (id),
    CONSTRAINT ux_person__myinfo_id UNIQUE (myinfo_id)
  );

--  Changeset config/liquibase/changelog/20111111000200_added_entity_Info.xml::20111111000200-1::jhipster
CREATE TABLE
  sampleproject_mysql.info (
    id BIGINT AUTO_INCREMENT NOT NULL,
    comments VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    CONSTRAINT PK_INFO PRIMARY KEY (id)
  );

--  Changeset config/liquibase/changelog/20111111000300_added_entity_Employee.xml::20111111000300-1::jhipster
CREATE TABLE
  sampleproject_mysql.employee (
    id BIGINT AUTO_INCREMENT NOT NULL,
    emp_name VARCHAR(255) NOT NULL,
    salary BIGINT NULL,
    my_dept_id BIGINT NULL,
    CONSTRAINT PK_EMPLOYEE PRIMARY KEY (id)
  );

--  Changeset config/liquibase/changelog/20111111000400_added_entity_Department.xml::20111111000400-1::jhipster
CREATE TABLE
  sampleproject_mysql.department (
    id BIGINT AUTO_INCREMENT NOT NULL,
    dept_name VARCHAR(255) NOT NULL,
    emp_no INT NULL,
    CONSTRAINT PK_DEPARTMENT PRIMARY KEY (id)
  );

--  Changeset config/liquibase/changelog/20111111000500_added_entity_Student.xml::20111111000500-1::jhipster
CREATE TABLE
  sampleproject_mysql.student (
    id BIGINT AUTO_INCREMENT NOT NULL,
    stu_name VARCHAR(255) NOT NULL,
    money BIGINT NULL,
    language VARCHAR(255) NULL,
    CONSTRAINT PK_STUDENT PRIMARY KEY (id)
  );

--  Changeset config/liquibase/changelog/20111111000500_added_entity_Student.xml::20111111000500-1-relations::jhipster
CREATE TABLE
  sampleproject_mysql.rel_student__mylesn (
    mylesn_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL
  );

ALTER TABLE sampleproject_mysql.rel_student__mylesn ADD PRIMARY KEY (student_id, mylesn_id);

--  Changeset config/liquibase/changelog/20111111000600_added_entity_Lesson.xml::20111111000600-1::jhipster
CREATE TABLE
  sampleproject_mysql.lesson (
    id BIGINT AUTO_INCREMENT NOT NULL,
    lesn_name VARCHAR(255) NOT NULL,
    teacher_name VARCHAR(255) NOT NULL,
    CONSTRAINT PK_LESSON PRIMARY KEY (id)
  );

--  Changeset config/liquibase/changelog/20111111000100_added_entity_constraints_Person.xml::20111111000100-2::jhipster
ALTER TABLE sampleproject_mysql.person ADD CONSTRAINT fk_person__myinfo_id FOREIGN KEY (myinfo_id) REFERENCES sampleproject_mysql.info (id);

--  Changeset config/liquibase/changelog/20111111000300_added_entity_constraints_Employee.xml::20111111000300-2::jhipster
ALTER TABLE sampleproject_mysql.employee ADD CONSTRAINT fk_employee__my_dept_id FOREIGN KEY (my_dept_id) REFERENCES sampleproject_mysql.department (id);

--  Changeset config/liquibase/changelog/20111111000500_added_entity_constraints_Student.xml::20111111000500-2::jhipster
ALTER TABLE sampleproject_mysql.rel_student__mylesn ADD CONSTRAINT fk_rel_student__mylesn__student_id FOREIGN KEY (student_id) REFERENCES sampleproject_mysql.student (id);

ALTER TABLE sampleproject_mysql.rel_student__mylesn ADD CONSTRAINT fk_rel_student__mylesn__mylesn_id FOREIGN KEY (mylesn_id) REFERENCES sampleproject_mysql.lesson (id);
