//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.web.rest;

import com.github.pagehelper.PageHelper;
import com.spring_mybatis.top.helloworld.domain.Department;
import com.spring_mybatis.top.helloworld.domain.DepartmentExample;
import com.spring_mybatis.top.helloworld.domain.DepartmentMapper;
import com.spring_mybatis.top.helloworld.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.spring_mybatis.top.helloworld.domain.Department}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DepartmentResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentResource.class);
    private static final String ENTITY_NAME = "department";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * {@code POST  /departments} : Create a new department.
     *
     * @param department the department to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new department, or with status {@code 400 (Bad Request)} if the department has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) throws URISyntaxException {
        log.debug("REST request to save Department : {}", department);
        if (department.getId() != null) {
            throw new BadRequestAlertException("A new department cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Department result = department;
        departmentMapper.insert(department);
        return ResponseEntity
            .created(new URI("/api/departments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /departments/:id} : Updates an existing department.
     *
     * @param id the id of the department to save.
     * @param department the department to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated department,
     * or with status {@code 400 (Bad Request)} if the department is not valid,
     * or with status {@code 500 (Internal Server Error)} if the department couldnot be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Department department
    ) throws URISyntaxException {
        log.debug("REST request to update Department : {}, {}", id, department);
        if (department.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, department.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (departmentMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Department result = department;
        departmentMapper.updateByPrimaryKey(department);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, department.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /departments/:id} : Partial updates given fields of an existing department, field will ignore if it is null
     *
     * @param id the id of the department to save.
     * @param department the department to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated department,
     * or with status {@code 400 (Bad Request)} if the department is not valid,
     * or with status {@code 404 (Not Found)} if the department is not found,
     * or with status {@code 500 (Internal Server Error)} if the department couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/departments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Department> partialUpdateDepartment(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Department department
    ) throws URISyntaxException {
        log.debug("REST request to partial update Department partially : {}, {}", id, department);
        if (department.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, department.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (departmentMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Department> result = Optional.of(department);
        departmentMapper.updateByPrimaryKeySelective(department);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, department.getId().toString())
        );
    }

    /**
     * {@code GET  /departments} : get all the departments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of departments in body.
     */
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String filter
    ) {
        if ("all".equals(filter)) {
            log.debug("REST request to get all Department ");

            return new ResponseEntity<>(departmentMapper.selectByExample(new DepartmentExample()), HttpStatus.OK);
        }
        log.debug("REST request to get a page of Departments");
        DepartmentExample exampleWithOrderBy = new DepartmentExample();
        exampleWithOrderBy.setOrderByClause(validateAndSanitizeOrderBy(pageable.getSort()));

        long totalrows = departmentMapper.countByExample(exampleWithOrderBy);
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize(), false);
        List<Department> resultList = departmentMapper.selectByExample(exampleWithOrderBy);

        Page<Department> page = new PageImpl<>(resultList, pageable, totalrows);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /departments/:id} : get the "id" department.
     *
     * @param id the id of the department to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the department, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        log.debug("REST request to get Department : {}", id);

        Department tmpDepartment = departmentMapper.selectByPrimaryKey(id);
        Optional<Department> department = Optional.ofNullable(tmpDepartment);

        return ResponseUtil.wrapOrNotFound(department);
    }

    /**
     * {@code DELETE  /departments/:id} : delete the "id" department.
     *
     * @param id the id of the department to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        log.debug("REST request to delete Department : {}", id);

        Department department = departmentMapper.selectByPrimaryKey(id);

        departmentMapper.deleteByPrimaryKey(id);

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
        allowedColumns.put("deptName", "dept_name");
        allowedColumns.put("empNo", "emp_no");

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
