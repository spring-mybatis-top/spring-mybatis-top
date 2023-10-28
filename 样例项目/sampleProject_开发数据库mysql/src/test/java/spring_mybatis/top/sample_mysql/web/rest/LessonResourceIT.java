//本代码由 http://spring-mybatis.top 生成
//release# release_1.2.25 @ 2023年 10月 23日 星期一 11:56:43 CST
package spring_mybatis.top.sample_mysql.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import spring_mybatis.top.sample_mysql.IntegrationTest;
import spring_mybatis.top.sample_mysql.domain.Lesson;
import spring_mybatis.top.sample_mysql.domain.LessonExample;
import spring_mybatis.top.sample_mysql.domain.LessonMapper;

/**
 * Integration tests for the {@link LessonResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LessonResourceIT {

    private static final String DEFAULT_LESN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LESN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TEACHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEACHER_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/lessons";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private MockMvc restLessonMockMvc;

    private Lesson lesson;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lesson createEntity() {
        Lesson lesson = new Lesson();
        lesson.setLesnName(DEFAULT_LESN_NAME);
        lesson.setTeacherName(DEFAULT_TEACHER_NAME);
        return lesson;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lesson createUpdatedEntity() {
        Lesson lesson = new Lesson();
        lesson.setLesnName(UPDATED_LESN_NAME);
        lesson.setTeacherName(UPDATED_TEACHER_NAME);
        return lesson;
    }

    @BeforeEach
    public void initTest() {
        lesson = createEntity();
    }

    @Test
    @Transactional
    void createLesson() throws Exception {
        int databaseSizeBeforeCreate = lessonMapper.selectByExample(new LessonExample()).size();
        // Create the Lesson
        restLessonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lesson)))
            .andExpect(status().isCreated());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeCreate + 1);
        Lesson testLesson = lessonList.get(lessonList.size() - 1);
        assertThat(testLesson.getLesnName()).isEqualTo(DEFAULT_LESN_NAME);
        assertThat(testLesson.getTeacherName()).isEqualTo(DEFAULT_TEACHER_NAME);
    }

    @Test
    @Transactional
    void createLessonWithExistingId() throws Exception {
        // Create the Lesson with an existing ID
        lesson.setId(1L);

        int databaseSizeBeforeCreate = lessonMapper.selectByExample(new LessonExample()).size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLessonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lesson)))
            .andExpect(status().isBadRequest());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLesnNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = lessonMapper.selectByExample(new LessonExample()).size();
        // set the field null
        lesson.setLesnName(null);

        // Create the Lesson, which fails.

        restLessonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lesson)))
            .andExpect(status().isBadRequest());

        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTeacherNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = lessonMapper.selectByExample(new LessonExample()).size();
        // set the field null
        lesson.setTeacherName(null);

        // Create the Lesson, which fails.

        restLessonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lesson)))
            .andExpect(status().isBadRequest());

        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLessons() throws Exception {
        // Initialize the database
        lessonMapper.insert(lesson);

        // Get all the lessonList
        restLessonMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lesson.getId().intValue())))
            .andExpect(jsonPath("$.[*].lesnName").value(hasItem(DEFAULT_LESN_NAME)))
            .andExpect(jsonPath("$.[*].teacherName").value(hasItem(DEFAULT_TEACHER_NAME)));
    }

    @Test
    @Transactional
    void getLesson() throws Exception {
        // Initialize the database
        lessonMapper.insert(lesson);

        // Get the lesson
        restLessonMockMvc
            .perform(get(ENTITY_API_URL_ID, lesson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lesson.getId().intValue()))
            .andExpect(jsonPath("$.lesnName").value(DEFAULT_LESN_NAME))
            .andExpect(jsonPath("$.teacherName").value(DEFAULT_TEACHER_NAME));
    }

    @Test
    @Transactional
    void getNonExistingLesson() throws Exception {
        // Get the lesson
        restLessonMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewLesson() throws Exception {
        // Initialize the database
        lessonMapper.insert(lesson);

        int databaseSizeBeforeUpdate = lessonMapper.selectByExample(new LessonExample()).size();

        // Update the lesson
        Lesson updatedLesson = lessonMapper.selectByPrimaryKey(lesson.getId());
        updatedLesson.lesnName(UPDATED_LESN_NAME).teacherName(UPDATED_TEACHER_NAME);

        restLessonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLesson.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLesson))
            )
            .andExpect(status().isOk());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeUpdate);
        Lesson testLesson = lessonList.get(lessonList.size() - 1);
        assertThat(testLesson.getLesnName()).isEqualTo(UPDATED_LESN_NAME);
        assertThat(testLesson.getTeacherName()).isEqualTo(UPDATED_TEACHER_NAME);
    }

    @Test
    @Transactional
    void putNonExistingLesson() throws Exception {
        int databaseSizeBeforeUpdate = lessonMapper.selectByExample(new LessonExample()).size();
        lesson.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLessonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, lesson.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lesson))
            )
            .andExpect(status().isBadRequest());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLesson() throws Exception {
        int databaseSizeBeforeUpdate = lessonMapper.selectByExample(new LessonExample()).size();
        lesson.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLessonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lesson))
            )
            .andExpect(status().isBadRequest());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLesson() throws Exception {
        int databaseSizeBeforeUpdate = lessonMapper.selectByExample(new LessonExample()).size();
        lesson.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLessonMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lesson)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLessonWithPatch() throws Exception {
        // Initialize the database
        lessonMapper.insert(lesson);

        int databaseSizeBeforeUpdate = lessonMapper.selectByExample(new LessonExample()).size();

        // Update the lesson using partial update
        Lesson partialUpdatedLesson = new Lesson();
        partialUpdatedLesson.setId(lesson.getId());

        partialUpdatedLesson.lesnName(UPDATED_LESN_NAME);
        restLessonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLesson.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLesson))
            )
            .andExpect(status().isOk());
        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeUpdate);
        Lesson testLesson = lessonList.get(lessonList.size() - 1);
        assertThat(testLesson.getLesnName()).isEqualTo(UPDATED_LESN_NAME);
        assertThat(testLesson.getTeacherName()).isEqualTo(DEFAULT_TEACHER_NAME);
    }

    @Test
    @Transactional
    void fullUpdateLessonWithPatch() throws Exception {
        // Initialize the database
        lessonMapper.insert(lesson);

        int databaseSizeBeforeUpdate = lessonMapper.selectByExample(new LessonExample()).size();

        // Update the lesson using partial update
        Lesson partialUpdatedLesson = new Lesson();
        partialUpdatedLesson.setId(lesson.getId());

        partialUpdatedLesson.lesnName(UPDATED_LESN_NAME).teacherName(UPDATED_TEACHER_NAME);
        restLessonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLesson.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLesson))
            )
            .andExpect(status().isOk());
        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeUpdate);
        Lesson testLesson = lessonList.get(lessonList.size() - 1);
        assertThat(testLesson.getLesnName()).isEqualTo(UPDATED_LESN_NAME);
        assertThat(testLesson.getTeacherName()).isEqualTo(UPDATED_TEACHER_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingLesson() throws Exception {
        int databaseSizeBeforeUpdate = lessonMapper.selectByExample(new LessonExample()).size();
        lesson.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLessonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, lesson.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(lesson))
            )
            .andExpect(status().isBadRequest());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLesson() throws Exception {
        int databaseSizeBeforeUpdate = lessonMapper.selectByExample(new LessonExample()).size();
        lesson.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLessonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(lesson))
            )
            .andExpect(status().isBadRequest());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLesson() throws Exception {
        int databaseSizeBeforeUpdate = lessonMapper.selectByExample(new LessonExample()).size();
        lesson.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLessonMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(lesson)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLesson() throws Exception {
        // Initialize the database
        lessonMapper.insert(lesson);

        int databaseSizeBeforeDelete = lessonMapper.selectByExample(new LessonExample()).size();

        // Delete the lesson
        restLessonMockMvc
            .perform(delete(ENTITY_API_URL_ID, lesson.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Lesson> lessonList = lessonMapper.selectByExample(new LessonExample());
        assertThat(lessonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
