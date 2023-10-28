//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.sample.web.rest;

import com.github.pagehelper.PageHelper;
import com.spring_mybatis.top.sample.domain.AllFieldExample;
import com.spring_mybatis.top.sample.domain.AllFieldMapper;
import com.spring_mybatis.top.sample.domain.AllFieldWithBLOBs;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.spring_mybatis.top.sample.domain.AllFieldWithBLOBs}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AllFieldResource {

    private final Logger log = LoggerFactory.getLogger(AllFieldResource.class);
    private static final String ENTITY_NAME = "allField";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    AllFieldMapper allFieldMapper;

    /**
     * {@code POST  /all-fields} : Create a new allFieldWithBLOBs.
     *
     * @param allFieldWithBLOBs the allFieldWithBLOBs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new allFieldWithBLOBs, or with status {@code 400 (Bad Request)} if the allFieldWithBLOBs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/all-fields")
    public ResponseEntity<AllFieldWithBLOBs> createAllField(@Valid @RequestBody AllFieldWithBLOBs allFieldWithBLOBs)
        throws URISyntaxException {
        log.debug("REST request to save AllFieldWithBLOBs : {}", allFieldWithBLOBs);
        if (allFieldWithBLOBs.getId() != null) {
            throw new BadRequestAlertException("A new allFieldWithBLOBs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AllFieldWithBLOBs result = allFieldWithBLOBs;
        allFieldMapper.insert(allFieldWithBLOBs);
        return ResponseEntity
            .created(new URI("/api/all-fields/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /all-fields/:id} : Updates an existing allFieldWithBLOBs.
     *
     * @param id the id of the allFieldWithBLOBs to save.
     * @param allFieldWithBLOBs the allFieldWithBLOBs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated allFieldWithBLOBs,
     * or with status {@code 400 (Bad Request)} if the allFieldWithBLOBs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the allFieldWithBLOBs couldnot be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/all-fields/{id}")
    public ResponseEntity<AllFieldWithBLOBs> updateAllField(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AllFieldWithBLOBs allFieldWithBLOBs
    ) throws URISyntaxException {
        log.debug("REST request to update AllFieldWithBLOBs : {}, {}", id, allFieldWithBLOBs);
        if (allFieldWithBLOBs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, allFieldWithBLOBs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (allFieldMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AllFieldWithBLOBs result = allFieldWithBLOBs;
        allFieldMapper.updateByPrimaryKeyWithBLOBs(allFieldWithBLOBs);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, allFieldWithBLOBs.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /all-fields/:id} : Partial updates given fields of an existing allFieldWithBLOBs, field will ignore if it is null
     *
     * @param id the id of the allFieldWithBLOBs to save.
     * @param allFieldWithBLOBs the allFieldWithBLOBs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated allFieldWithBLOBs,
     * or with status {@code 400 (Bad Request)} if the allFieldWithBLOBs is not valid,
     * or with status {@code 404 (Not Found)} if the allFieldWithBLOBs is not found,
     * or with status {@code 500 (Internal Server Error)} if the allFieldWithBLOBs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/all-fields/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AllFieldWithBLOBs> partialUpdateAllField(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AllFieldWithBLOBs allFieldWithBLOBs
    ) throws URISyntaxException {
        log.debug("REST request to partial update AllFieldWithBLOBs partially : {}, {}", id, allFieldWithBLOBs);
        if (allFieldWithBLOBs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, allFieldWithBLOBs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (allFieldMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AllFieldWithBLOBs> result = Optional.of(allFieldWithBLOBs);
        allFieldMapper.updateByPrimaryKeySelective(allFieldWithBLOBs);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, allFieldWithBLOBs.getId().toString())
        );
    }

    /**
     * {@code GET  /all-fields} : get all the allFields.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of allFields in body.
     */
    @GetMapping("/all-fields")
    public List<AllFieldWithBLOBs> getAllAllFields() {
        log.debug("REST request to get all AllFields");
        List<AllFieldWithBLOBs> result = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());

        return result;
        //return allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
    }

    /**
     * {@code GET  /all-fields/:id} : get the "id" allFieldWithBLOBs.
     *
     * @param id the id of the allFieldWithBLOBs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the allFieldWithBLOBs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/all-fields/{id}")
    public ResponseEntity<AllFieldWithBLOBs> getAllField(@PathVariable Long id) {
        log.debug("REST request to get AllFieldWithBLOBs : {}", id);

        AllFieldWithBLOBs tmpAllField = allFieldMapper.selectByPrimaryKey(id);
        Optional<AllFieldWithBLOBs> allFieldWithBLOBs = Optional.ofNullable(tmpAllField);

        return ResponseUtil.wrapOrNotFound(allFieldWithBLOBs);
    }

    /**
     * {@code DELETE  /all-fields/:id} : delete the "id" allFieldWithBLOBs.
     *
     * @param id the id of the allFieldWithBLOBs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/all-fields/{id}")
    public ResponseEntity<Void> deleteAllField(@PathVariable Long id) {
        log.debug("REST request to delete AllFieldWithBLOBs : {}", id);

        AllFieldWithBLOBs allFieldWithBLOBs = allFieldMapper.selectByPrimaryKey(id);

        allFieldMapper.deleteByPrimaryKey(id);

        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
