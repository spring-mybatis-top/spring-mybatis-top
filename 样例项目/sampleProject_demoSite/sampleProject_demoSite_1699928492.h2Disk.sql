-- Changeset config/liquibase/changelog/20111111000100_added_entity_Person.xml::20111111000100-1::jhipster
CREATE TABLE
  person (
    id BIGINT AUTO_INCREMENT NOT NULL,
    person_name VARCHAR(255) NOT NULL,
    age BIGINT,
    myinfo_id BIGINT,
    CONSTRAINT PK_PERSON PRIMARY KEY (id),
    CONSTRAINT ux_person__myinfo_id UNIQUE (myinfo_id)
  );

-- Changeset config/liquibase/changelog/20111111000200_added_entity_Info.xml::20111111000200-1::jhipster
CREATE TABLE
  info (
    id BIGINT AUTO_INCREMENT NOT NULL,
    comments VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    CONSTRAINT PK_INFO PRIMARY KEY (id)
  );

-- Changeset config/liquibase/changelog/20111111000300_added_entity_Employee.xml::20111111000300-1::jhipster
CREATE TABLE
  employee (
    id BIGINT AUTO_INCREMENT NOT NULL,
    emp_name VARCHAR(255) NOT NULL,
    salary BIGINT,
    my_dept_id BIGINT,
    CONSTRAINT PK_EMPLOYEE PRIMARY KEY (id)
  );

-- Changeset config/liquibase/changelog/20111111000400_added_entity_Department.xml::20111111000400-1::jhipster
CREATE TABLE
  department (
    id BIGINT AUTO_INCREMENT NOT NULL,
    dept_name VARCHAR(255) NOT NULL,
    emp_no INT,
    CONSTRAINT PK_DEPARTMENT PRIMARY KEY (id)
  );

-- Changeset config/liquibase/changelog/20111111000500_added_entity_Student.xml::20111111000500-1::jhipster
CREATE TABLE
  student (
    id BIGINT AUTO_INCREMENT NOT NULL,
    stu_name VARCHAR(255) NOT NULL,
    money BIGINT,
    language VARCHAR(255),
    CONSTRAINT PK_STUDENT PRIMARY KEY (id)
  );

-- Changeset config/liquibase/changelog/20111111000500_added_entity_Student.xml::20111111000500-1-relations::jhipster
CREATE TABLE
  rel_student__mylesn (
    mylesn_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL
  );

ALTER TABLE rel_student__mylesn ADD PRIMARY KEY (student_id, mylesn_id);

-- Changeset config/liquibase/changelog/20111111000600_added_entity_Lesson.xml::20111111000600-1::jhipster
CREATE TABLE
  lesson (
    id BIGINT AUTO_INCREMENT NOT NULL,
    lesn_name VARCHAR(255) NOT NULL,
    teacher_name VARCHAR(255) NOT NULL,
    CONSTRAINT PK_LESSON PRIMARY KEY (id)
  );

-- Changeset config/liquibase/changelog/20111111000700_added_entity_AllField.xml::20111111000700-1::jhipster
CREATE TABLE
  all_field (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    name_integer INT NOT NULL,
    name_long BIGINT NOT NULL,
    name_big_decimal DECIMAL(21, 2) NOT NULL,
    name_float FLOAT4 NOT NULL,
    name_double DOUBLE NOT NULL,
    name_boolean BOOLEAN NOT NULL,
    name_local_date date NOT NULL,
    name_zoned_date_time TIMESTAMP NOT NULL,
    name_instant TIMESTAMP NOT NULL,
    name_blob BLOB NOT NULL,
    name_blob_content_type VARCHAR(255) NOT NULL,
    name_any_blob BLOB NOT NULL,
    name_any_blob_content_type VARCHAR(255) NOT NULL,
    name_image_blob BLOB NOT NULL,
    name_image_blob_content_type VARCHAR(255) NOT NULL,
    name_text_blob CLOB NOT NULL,
    CONSTRAINT PK_ALL_FIELD PRIMARY KEY (id)
  );

-- Changeset config/liquibase/changelog/20111111000100_added_entity_constraints_Person.xml::20111111000100-2::jhipster
ALTER TABLE person ADD CONSTRAINT fk_person__myinfo_id FOREIGN KEY (myinfo_id) REFERENCES info (id);

-- Changeset config/liquibase/changelog/20111111000300_added_entity_constraints_Employee.xml::20111111000300-2::jhipster
ALTER TABLE employee ADD CONSTRAINT fk_employee__my_dept_id FOREIGN KEY (my_dept_id) REFERENCES department (id);

-- Changeset config/liquibase/changelog/20111111000500_added_entity_constraints_Student.xml::20111111000500-2::jhipster
ALTER TABLE rel_student__mylesn ADD CONSTRAINT fk_rel_student__mylesn__student_id FOREIGN KEY (student_id) REFERENCES student (id);

ALTER TABLE rel_student__mylesn ADD CONSTRAINT fk_rel_student__mylesn__mylesn_id FOREIGN KEY (mylesn_id) REFERENCES lesson (id);
