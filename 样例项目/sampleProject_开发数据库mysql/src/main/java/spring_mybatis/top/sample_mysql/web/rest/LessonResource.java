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
import spring_mybatis.top.sample_mysql.domain.Lesson;
import spring_mybatis.top.sample_mysql.domain.LessonExample;
import spring_mybatis.top.sample_mysql.domain.LessonMapper;
import spring_mybatis.top.sample_mysql.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link spring_mybatis.top.sample_mysql.domain.Lesson}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LessonResource {

    private final Logger log = LoggerFactory.getLogger(LessonResource.class);
    private static final String ENTITY_NAME = "lesson";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    LessonMapper lessonMapper;

    /**
     * {@code POST  /lessons} : Create a new lesson.
     *
     * @param lesson the lesson to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lesson, or with status {@code 400 (Bad Request)} if the lesson has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lessons")
    public ResponseEntity<Lesson> createLesson(@Valid @RequestBody Lesson lesson) throws URISyntaxException {
        log.debug("REST request to save Lesson : {}", lesson);
        if (lesson.getId() != null) {
            throw new BadRequestAlertException("A new lesson cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Lesson result = lesson;
        lessonMapper.insert(lesson);
        return ResponseEntity
            .created(new URI("/api/lessons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lessons/:id} : Updates an existing lesson.
     *
     * @param id the id of the lesson to save.
     * @param lesson the lesson to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lesson,
     * or with status {@code 400 (Bad Request)} if the lesson is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lesson couldnot be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lessons/{id}")
    public ResponseEntity<Lesson> updateLesson(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Lesson lesson
    ) throws URISyntaxException {
        log.debug("REST request to update Lesson : {}, {}", id, lesson);
        if (lesson.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lesson.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (lessonMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Lesson result = lesson;
        lessonMapper.updateByPrimaryKey(lesson);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lesson.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /lessons/:id} : Partial updates given fields of an existing lesson, field will ignore if it is null
     *
     * @param id the id of the lesson to save.
     * @param lesson the lesson to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lesson,
     * or with status {@code 400 (Bad Request)} if the lesson is not valid,
     * or with status {@code 404 (Not Found)} if the lesson is not found,
     * or with status {@code 500 (Internal Server Error)} if the lesson couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/lessons/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Lesson> partialUpdateLesson(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Lesson lesson
    ) throws URISyntaxException {
        log.debug("REST request to partial update Lesson partially : {}, {}", id, lesson);
        if (lesson.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lesson.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (lessonMapper.selectByPrimaryKey(id) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Lesson> result = Optional.of(lesson);
        lessonMapper.updateByPrimaryKeySelective(lesson);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lesson.getId().toString())
        );
    }

    /**
     * {@code GET  /lessons} : get all the lessons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lessons in body.
     */
    @GetMapping("/lessons")
    public ResponseEntity<List<Lesson>> getAllLessons(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String filter
    ) {
        if ("all".equals(filter)) {
            log.debug("REST request to get all Lesson ");

            return new ResponseEntity<>(lessonMapper.selectByExample(new LessonExample()), HttpStatus.OK);
        }
        log.debug("REST request to get a page of Lessons");
        LessonExample exampleWithOrderBy = new LessonExample();
        exampleWithOrderBy.setOrderByClause(validateAndSanitizeOrderBy(pageable.getSort()));

        long totalrows = lessonMapper.countByExample(exampleWithOrderBy);
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize(), false);
        List<Lesson> resultList = lessonMapper.selectByExample(exampleWithOrderBy);

        Page<Lesson> page = new PageImpl<>(resultList, pageable, totalrows);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lessons/:id} : get the "id" lesson.
     *
     * @param id the id of the lesson to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lesson, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lessons/{id}")
    public ResponseEntity<Lesson> getLesson(@PathVariable Long id) {
        log.debug("REST request to get Lesson : {}", id);

        Lesson tmpLesson = lessonMapper.selectByPrimaryKey(id);
        Optional<Lesson> lesson = Optional.ofNullable(tmpLesson);

        return ResponseUtil.wrapOrNotFound(lesson);
    }

    /**
     * {@code DELETE  /lessons/:id} : delete the "id" lesson.
     *
     * @param id the id of the lesson to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        log.debug("REST request to delete Lesson : {}", id);

        Lesson lesson = lessonMapper.selectByPrimaryKey(id);

        lessonMapper.deleteByPrimaryKey(id);

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
        allowedColumns.put("lesnName", "lesn_name");
        allowedColumns.put("teacherName", "teacher_name");

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
