//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample_mysql.web.rest;

import com.github.pagehelper.PageHelper;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
import spring_mybatis.top.sample_mysql.domain.Info;
import spring_mybatis.top.sample_mysql.domain.InfoExample;
import spring_mybatis.top.sample_mysql.domain.InfoMapper;
import spring_mybatis.top.sample_mysql.domain.Person;
import spring_mybatis.top.sample_mysql.domain.PersonExample;
import spring_mybatis.top.sample_mysql.domain.PersonMapper;
import spring_mybatis.top.sample_mysql.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link spring_mybatis.top.sample_mysql.domain.Info}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InfoResource {

    private final Logger log = LoggerFactory.getLogger(InfoResource.class);
    private static final String ENTITY_NAME = "info";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    InfoMapper infoMapper;

    @Autowired
    PersonMapper personMapper;

    /**
     * {@code POST  /infos} : Create a new info.
     *
     * @param info the info to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new info, or with status {@code 400 (Bad Request)} if the info has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/infos")
    public ResponseEntity<Info> createInfo(@Valid @RequestBody Info info) throws URISyntaxException {
        log.debug("REST request to save Info : {}", info);
        if (info.getId() != null) {
            throw new BadRequestAlertException("A new info cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Info result = info;
        infoMapper.insert(info);
        return ResponseEntity
            .created(new URI("/api/infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /infos/:id} : Updates an existing info.
     *
     * @param id the id of the info to save.
     * @param info the info to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated info,
     * or with status {@code 400 (Bad Request)} if the info is not valid,
     * or with status {@code 500 (Internal Server Error)} if the info couldnot be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/infos/{id}")
    public ResponseEntity<Info> updateInfo(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Info info)
        throws URISyntaxException {
        log.debug("REST request to update Info : {}, {}", id, info);
        if (info.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, info.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (infoMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Info result = info;
        infoMapper.updateByPrimaryKey(info);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, info.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /infos/:id} : Partial updates given fields of an existing info, field will ignore if it is null
     *
     * @param id the id of the info to save.
     * @param info the info to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated info,
     * or with status {@code 400 (Bad Request)} if the info is not valid,
     * or with status {@code 404 (Not Found)} if the info is not found,
     * or with status {@code 500 (Internal Server Error)} if the info couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/infos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Info> partialUpdateInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Info info
    ) throws URISyntaxException {
        log.debug("REST request to partial update Info partially : {}, {}", id, info);
        if (info.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, info.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (infoMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Info> result = Optional.of(info);
        infoMapper.updateByPrimaryKeySelective(info);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, info.getId().toString())
        );
    }

    /**
     * {@code GET  /infos} : get all the infos.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of infos in body.
     */
    @GetMapping("/infos")
    public ResponseEntity<List<Info>> getAllInfos(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String filter
    ) {
        if ("myperson-is-null".equals(filter)) {
            log.debug("REST request to get all Info where myperson is null");
            return new ResponseEntity<>(
                infoMapper
                    .selectByExample(new InfoExample())
                    .stream()
                    .filter(info ->
                        personMapper
                            .selectByExample(new PersonExample())
                            .stream()
                            .noneMatch(myperson -> info.getId().equals(myperson.getMyinfoId()))
                    )
                    .collect(Collectors.toList()),
                HttpStatus.OK
            );
        }
        log.debug("REST request to get a page of Infos");
        InfoExample exampleWithOrderBy = new InfoExample();
        exampleWithOrderBy.setOrderByClause(validateAndSanitizeOrderBy(pageable.getSort()));

        long totalrows = infoMapper.countByExample(exampleWithOrderBy);
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize(), false);
        List<Info> resultList = infoMapper.selectByExample(exampleWithOrderBy);

        Page<Info> page = new PageImpl<>(resultList, pageable, totalrows);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /infos/:id} : get the "id" info.
     *
     * @param id the id of the info to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the info, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/infos/{id}")
    public ResponseEntity<Info> getInfo(@PathVariable Long id) {
        log.debug("REST request to get Info : {}", id);

        Info tmpInfo = infoMapper.selectByPrimaryKey(id);
        Optional<Info> info = Optional.ofNullable(tmpInfo);

        return ResponseUtil.wrapOrNotFound(info);
    }

    /**
     * {@code DELETE  /infos/:id} : delete the "id" info.
     *
     * @param id the id of the info to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/infos/{id}")
    public ResponseEntity<Void> deleteInfo(@PathVariable Long id) {
        log.debug("REST request to delete Info : {}", id);

        Info info = infoMapper.selectByPrimaryKey(id);

        infoMapper.deleteByPrimaryKey(id);

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
        allowedColumns.put("comments", "comments");
        allowedColumns.put("image", "image");

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
