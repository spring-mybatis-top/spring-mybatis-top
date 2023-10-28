//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.sample.web.rest;

import com.github.pagehelper.PageHelper;
import com.spring_mybatis.top.sample.domain.Employee;
import com.spring_mybatis.top.sample.domain.EmployeeExample;
import com.spring_mybatis.top.sample.domain.EmployeeMapper;
import com.spring_mybatis.top.sample.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.spring_mybatis.top.sample.domain.Employee}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EmployeeResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);
    private static final String ENTITY_NAME = "employee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * {@code POST  /employees} : Create a new employee.
     *
     * @param employee the employee to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employee, or with status {@code 400 (Bad Request)} if the employee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) throws URISyntaxException {
        log.debug("REST request to save Employee : {}", employee);
        if (employee.getId() != null) {
            throw new BadRequestAlertException("A new employee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Employee result = employee;
        employeeMapper.insert(employee);
        return ResponseEntity
            .created(new URI("/api/employees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /employees/:id} : Updates an existing employee.
     *
     * @param id the id of the employee to save.
     * @param employee the employee to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employee,
     * or with status {@code 400 (Bad Request)} if the employee is not valid,
     * or with status {@code 500 (Internal Server Error)} if the employee couldnot be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Employee employee
    ) throws URISyntaxException {
        log.debug("REST request to update Employee : {}, {}", id, employee);
        if (employee.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, employee.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (employeeMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Employee result = employee;
        employeeMapper.updateByPrimaryKey(employee);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employee.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /employees/:id} : Partial updates given fields of an existing employee, field will ignore if it is null
     *
     * @param id the id of the employee to save.
     * @param employee the employee to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employee,
     * or with status {@code 400 (Bad Request)} if the employee is not valid,
     * or with status {@code 404 (Not Found)} if the employee is not found,
     * or with status {@code 500 (Internal Server Error)} if the employee couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/employees/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Employee> partialUpdateEmployee(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Employee employee
    ) throws URISyntaxException {
        log.debug("REST request to partial update Employee partially : {}, {}", id, employee);
        if (employee.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, employee.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (employeeMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Employee> result = Optional.of(employee);
        employeeMapper.updateByPrimaryKeySelective(employee);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employee.getId().toString())
        );
    }

    /**
     * {@code GET  /employees} : get all the employees.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employees in body.
     */
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Employees");
        EmployeeExample exampleWithOrderBy = new EmployeeExample();
        exampleWithOrderBy.setOrderByClause(validateAndSanitizeOrderBy(pageable.getSort()));

        long totalrows = employeeMapper.countByExample(exampleWithOrderBy);
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize(), false);
        List<Employee> resultList = employeeMapper.selectByExample(exampleWithOrderBy);

        Page<Employee> page = new PageImpl<>(resultList, pageable, totalrows);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /employees/:id} : get the "id" employee.
     *
     * @param id the id of the employee to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employee, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        log.debug("REST request to get Employee : {}", id);

        Employee tmpEmployee = employeeMapper.selectByPrimaryKey(id);
        Optional<Employee> employee = Optional.ofNullable(tmpEmployee);

        return ResponseUtil.wrapOrNotFound(employee);
    }

    /**
     * {@code DELETE  /employees/:id} : delete the "id" employee.
     *
     * @param id the id of the employee to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        log.debug("REST request to delete Employee : {}", id);

        Employee employee = employeeMapper.selectByPrimaryKey(id);

        employeeMapper.deleteByPrimaryKey(id);

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
        allowedColumns.put("empName", "emp_name");
        allowedColumns.put("salary", "salary");

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
