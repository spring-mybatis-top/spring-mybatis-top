//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.web.rest;

import com.github.pagehelper.PageHelper;
import com.spring_mybatis.top.helloworld.domain.Lesson;
import com.spring_mybatis.top.helloworld.domain.LessonExample;
import com.spring_mybatis.top.helloworld.domain.LessonMapper;
import com.spring_mybatis.top.helloworld.domain.RelStudentMylesn;
import com.spring_mybatis.top.helloworld.domain.RelStudentMylesnExample;
import com.spring_mybatis.top.helloworld.domain.RelStudentMylesnKey;
import com.spring_mybatis.top.helloworld.domain.RelStudentMylesnMapper;
import com.spring_mybatis.top.helloworld.domain.Student;
import com.spring_mybatis.top.helloworld.domain.StudentExample;
import com.spring_mybatis.top.helloworld.domain.StudentMapper;
import com.spring_mybatis.top.helloworld.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.spring_mybatis.top.helloworld.domain.Student}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StudentResource {

    private final Logger log = LoggerFactory.getLogger(StudentResource.class);

    private static final String ENTITY_NAME = "student";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    LessonMapper lessonMapper;

    @Autowired
    RelStudentMylesnMapper relStudentMylesnMapper;

    /**
     * {@code POST  /students} : Create a new student.
     *
     * @param student the student to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new student, or with status {@code 400 (Bad Request)} if the student has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) throws URISyntaxException {
        log.debug("REST request to save Student : {}", student);
        if (student.getId() != null) {
            throw new BadRequestAlertException("A new student cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Student result = student;
        studentMapper.insert(student);

        student
            .getMylesns()
            .stream()
            .forEach(newMylesn ->
                relStudentMylesnMapper.insert(new RelStudentMylesnKey().studentId(student.getId()).mylesnId(newMylesn.getId()))
            );

        return ResponseEntity
            .created(new URI("/api/students/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /students/:id} : Updates an existing student.
     *
     * @param id the id of the student to save.
     * @param student the student to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated student,
     * or with status {@code 400 (Bad Request)} if the student is not valid,
     * or with status {@code 500 (Internal Server Error)} if the student couldnot be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Student student
    ) throws URISyntaxException {
        log.debug("REST request to update Student : {}, {}", id, student);
        if (student.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, student.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (studentMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Student result = student;
        studentMapper.updateByPrimaryKey(student);

        //find all RelStudentMylesn with studentId and delete
        RelStudentMylesnExample toDeleteRelStudentMylesnExample = new RelStudentMylesnExample();
        toDeleteRelStudentMylesnExample.createCriteria().andStudentIdEqualTo(student.getId());
        relStudentMylesnMapper.deleteByExample(toDeleteRelStudentMylesnExample);

        //insert new RelStudentMylesn
        student
            .getMylesns()
            .stream()
            .forEach(newMylesn ->
                relStudentMylesnMapper.insert(new RelStudentMylesnKey().studentId(student.getId()).mylesnId(newMylesn.getId()))
            );

        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, student.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /students/:id} : Partial updates given fields of an existing student, field will ignore if it is null
     *
     * @param id the id of the student to save.
     * @param student the student to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated student,
     * or with status {@code 400 (Bad Request)} if the student is not valid,
     * or with status {@code 404 (Not Found)} if the student is not found,
     * or with status {@code 500 (Internal Server Error)} if the student couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/students/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Student> partialUpdateStudent(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Student student
    ) throws URISyntaxException {
        log.debug("REST request to partial update Student partially : {}, {}", id, student);
        if (student.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, student.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (studentMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Student> result = Optional.of(student);
        studentMapper.updateByPrimaryKeySelective(student);

        // for partial update, if and only if there are new Mylesns
        if (student.getMylesns() != null && student.getMylesns().size() > 0) {
            //find all RelStudentMylesn with studentId and delete
            RelStudentMylesnExample toDeleteRelStudentMylesnExample = new RelStudentMylesnExample();
            toDeleteRelStudentMylesnExample.createCriteria().andStudentIdEqualTo(student.getId());
            relStudentMylesnMapper.deleteByExample(toDeleteRelStudentMylesnExample);

            //insert new RelStudentMylesn
            student
                .getMylesns()
                .stream()
                .forEach(newMylesn ->
                    relStudentMylesnMapper.insert(new RelStudentMylesnKey().studentId(student.getId()).mylesnId(newMylesn.getId()))
                );
        }

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, student.getId().toString())
        );
    }

    /**
     * {@code GET  /students} : get all the students.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of students in body.
     */
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Students");
        StudentExample exampleWithOrderBy = new StudentExample();
        exampleWithOrderBy.setOrderByClause(validateAndSanitizeOrderBy(pageable.getSort()));

        long totalrows = studentMapper.countByExample(exampleWithOrderBy);
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize(), false);
        List<Student> resultList = studentMapper.selectByExample(exampleWithOrderBy);

        for (Student student : resultList) {
            Set<Lesson> mylesns = new HashSet<>();
            RelStudentMylesnExample relStudentMylesnExample = new RelStudentMylesnExample();
            relStudentMylesnExample.createCriteria().andStudentIdEqualTo(student.getId());
            relStudentMylesnMapper
                .selectByExample(relStudentMylesnExample)
                .stream()
                .forEach(relStudentMylesn -> mylesns.add(lessonMapper.selectByPrimaryKey(relStudentMylesn.getMylesnId())));
            student.setMylesns(mylesns);
        } //print_for

        Page<Student> page = new PageImpl<>(resultList, pageable, totalrows);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /students/:id} : get the "id" student.
     *
     * @param id the id of the student to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the student, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        log.debug("REST request to get Student : {}", id);

        Student tmpStudent = studentMapper.selectByPrimaryKey(id);
        Optional<Student> student = Optional.ofNullable(tmpStudent);

        if (student.isPresent()) {
            Set<Lesson> mylesns = new HashSet<>();
            RelStudentMylesnExample relStudentMylesnExample = new RelStudentMylesnExample();
            relStudentMylesnExample.createCriteria().andStudentIdEqualTo(tmpStudent.getId());
            relStudentMylesnMapper
                .selectByExample(relStudentMylesnExample)
                .stream()
                .forEach(relStudentMylesn -> mylesns.add(lessonMapper.selectByPrimaryKey(relStudentMylesn.getMylesnId())));
            tmpStudent.setMylesns(mylesns);
        }

        return ResponseUtil.wrapOrNotFound(student);
    }

    /**
     * {@code DELETE  /students/:id} : delete the "id" student.
     *
     * @param id the id of the student to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.debug("REST request to delete Student : {}", id);

        Student student = studentMapper.selectByPrimaryKey(id);

        if (student != null) {
            //find all RelStudentMylesn with studentId and delete
            RelStudentMylesnExample toDeleteRelStudentMylesnExample = new RelStudentMylesnExample();
            toDeleteRelStudentMylesnExample.createCriteria().andStudentIdEqualTo(student.getId());
            relStudentMylesnMapper.deleteByExample(toDeleteRelStudentMylesnExample);
        }

        studentMapper.deleteByPrimaryKey(id);

        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    private String validateAndSanitizeOrderBy(Sort sort) {
        if (sort == null || sort.isUnsorted()) {
            return "id ASC";
        }

        // Define a whitelist of allowed columns and their corresponding database column names
        Map<String, String> allowedColumns = new HashMap<>();
        allowedColumns.put("id", "id");
        allowedColumns.put("stuName", "stu_name");
        allowedColumns.put("money", "money");
        allowedColumns.put("language", "language");

        // Validate and sanitize the Sort object
        List<String> sanitizedOrders = new ArrayList<>();

        for (Sort.Order order : sort) {
            String property = order.getProperty();
            String direction = order.getDirection().name();

            // Check if the property is in the whitelist
            if (allowedColumns.containsKey(property)) {
                // Get the corresponding database column name
                String dbColumn = allowedColumns.get(property);

                // Build the sanitized order string
                String sanitizedOrder = dbColumn + " " + direction;
                sanitizedOrders.add(sanitizedOrder);
            } else {
                throw new RuntimeException(property + " is not found in the list");
            }
        }

        // Join the sanitized orders into a comma-separated string
        String orderBy = String.join(", ", sanitizedOrders);
        log.debug("orderby is |{}|", orderBy);
        if (orderBy.length() < 2) return "id ASC";
        return orderBy;
    }
}
