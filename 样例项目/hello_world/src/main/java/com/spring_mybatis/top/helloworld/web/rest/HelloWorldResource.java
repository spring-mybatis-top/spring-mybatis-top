//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.helloworld.web.rest;

import com.github.pagehelper.PageHelper;
import com.spring_mybatis.top.helloworld.domain.HelloWorld;
import com.spring_mybatis.top.helloworld.domain.HelloWorldExample;
import com.spring_mybatis.top.helloworld.domain.HelloWorldMapper;
import com.spring_mybatis.top.helloworld.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.spring_mybatis.top.helloworld.domain.HelloWorld}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class HelloWorldResource {

    private final Logger log = LoggerFactory.getLogger(HelloWorldResource.class);

    private static final String ENTITY_NAME = "helloWorld";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    HelloWorldMapper helloWorldMapper;

    /**
     * {@code POST  /hello-worlds} : Create a new helloWorld.
     *
     * @param helloWorld the helloWorld to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new helloWorld, or with status {@code 400 (Bad Request)} if the helloWorld has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hello-worlds")
    public ResponseEntity<HelloWorld> createHelloWorld(@RequestBody HelloWorld helloWorld) throws URISyntaxException {
        log.debug("REST request to save HelloWorld : {}", helloWorld);
        if (helloWorld.getId() != null) {
            throw new BadRequestAlertException("A new helloWorld cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HelloWorld result = helloWorld;
        helloWorldMapper.insert(helloWorld);

        return ResponseEntity
            .created(new URI("/api/hello-worlds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hello-worlds/:id} : Updates an existing helloWorld.
     *
     * @param id the id of the helloWorld to save.
     * @param helloWorld the helloWorld to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated helloWorld,
     * or with status {@code 400 (Bad Request)} if the helloWorld is not valid,
     * or with status {@code 500 (Internal Server Error)} if the helloWorld couldnot be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hello-worlds/{id}")
    public ResponseEntity<HelloWorld> updateHelloWorld(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HelloWorld helloWorld
    ) throws URISyntaxException {
        log.debug("REST request to update HelloWorld : {}, {}", id, helloWorld);
        if (helloWorld.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, helloWorld.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (helloWorldMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HelloWorld result = helloWorld;
        helloWorldMapper.updateByPrimaryKey(helloWorld);

        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, helloWorld.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /hello-worlds/:id} : Partial updates given fields of an existing helloWorld, field will ignore if it is null
     *
     * @param id the id of the helloWorld to save.
     * @param helloWorld the helloWorld to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated helloWorld,
     * or with status {@code 400 (Bad Request)} if the helloWorld is not valid,
     * or with status {@code 404 (Not Found)} if the helloWorld is not found,
     * or with status {@code 500 (Internal Server Error)} if the helloWorld couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/hello-worlds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HelloWorld> partialUpdateHelloWorld(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HelloWorld helloWorld
    ) throws URISyntaxException {
        log.debug("REST request to partial update HelloWorld partially : {}, {}", id, helloWorld);
        if (helloWorld.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, helloWorld.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (helloWorldMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HelloWorld> result = Optional.of(helloWorld);
        helloWorldMapper.updateByPrimaryKeySelective(helloWorld);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, helloWorld.getId().toString())
        );
    }

    /**
     * {@code GET  /hello-worlds} : get all the helloWorlds.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of helloWorlds in body.
     */
    @GetMapping("/hello-worlds")
    public ResponseEntity<List<HelloWorld>> getAllHelloWorlds(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of HelloWorlds");
        HelloWorldExample exampleWithOrderBy = new HelloWorldExample();
        exampleWithOrderBy.setOrderByClause(validateAndSanitizeOrderBy(pageable.getSort()));

        long totalrows = helloWorldMapper.countByExample(exampleWithOrderBy);
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize(), false);
        List<HelloWorld> resultList = helloWorldMapper.selectByExample(exampleWithOrderBy);

        Page<HelloWorld> page = new PageImpl<>(resultList, pageable, totalrows);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hello-worlds/:id} : get the "id" helloWorld.
     *
     * @param id the id of the helloWorld to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the helloWorld, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hello-worlds/{id}")
    public ResponseEntity<HelloWorld> getHelloWorld(@PathVariable Long id) {
        log.debug("REST request to get HelloWorld : {}", id);

        HelloWorld tmpHelloWorld = helloWorldMapper.selectByPrimaryKey(id);
        Optional<HelloWorld> helloWorld = Optional.ofNullable(tmpHelloWorld);

        return ResponseUtil.wrapOrNotFound(helloWorld);
    }

    /**
     * {@code DELETE  /hello-worlds/:id} : delete the "id" helloWorld.
     *
     * @param id the id of the helloWorld to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hello-worlds/{id}")
    public ResponseEntity<Void> deleteHelloWorld(@PathVariable Long id) {
        log.debug("REST request to delete HelloWorld : {}", id);

        HelloWorld helloWorld = helloWorldMapper.selectByPrimaryKey(id);

        helloWorldMapper.deleteByPrimaryKey(id);

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
        allowedColumns.put("name", "name");
        allowedColumns.put("age", "age");

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
