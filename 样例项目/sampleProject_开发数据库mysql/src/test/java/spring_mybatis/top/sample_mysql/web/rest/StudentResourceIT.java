//本代码由 http://spring-mybatis.top 生成
//release# release_1.2.25 @ 2023年 10月 23日 星期一 11:56:43 CST
package spring_mybatis.top.sample_mysql.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import spring_mybatis.top.sample_mysql.IntegrationTest;
import spring_mybatis.top.sample_mysql.domain.Student;
import spring_mybatis.top.sample_mysql.domain.StudentExample;
import spring_mybatis.top.sample_mysql.domain.StudentMapper;
import spring_mybatis.top.sample_mysql.domain.enumeration.Language;

/**
 * Integration tests for the {@link StudentResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class StudentResourceIT {

    private static final String DEFAULT_STU_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STU_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_MONEY = 1L;
    private static final Long UPDATED_MONEY = 2L;

    private static final Language DEFAULT_LANGUAGE = Language.FRENCH;
    private static final Language UPDATED_LANGUAGE = Language.ENGLISH;

    private static final String ENTITY_API_URL = "/api/students";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private MockMvc restStudentMockMvc;

    private Student student;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createEntity() {
        Student student = new Student();
        student.setStuName(DEFAULT_STU_NAME);
        student.setMoney(DEFAULT_MONEY);
        student.setLanguage(DEFAULT_LANGUAGE);
        return student;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createUpdatedEntity() {
        Student student = new Student();
        student.setStuName(UPDATED_STU_NAME);
        student.setMoney(UPDATED_MONEY);
        student.setLanguage(UPDATED_LANGUAGE);
        return student;
    }

    @BeforeEach
    public void initTest() {
        student = createEntity();
    }

    @Test
    @Transactional
    void createStudent() throws Exception {
        int databaseSizeBeforeCreate = studentMapper.selectByExample(new StudentExample()).size();
        // Create the Student
        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isCreated());

        // Validate the Student in the database
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStuName()).isEqualTo(DEFAULT_STU_NAME);
        assertThat(testStudent.getMoney()).isEqualTo(DEFAULT_MONEY);
        assertThat(testStudent.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
    }

    @Test
    @Transactional
    void createStudentWithExistingId() throws Exception {
        // Create the Student with an existing ID
        student.setId(1L);

        int databaseSizeBeforeCreate = studentMapper.selectByExample(new StudentExample()).size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkStuNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentMapper.selectByExample(new StudentExample()).size();
        // set the field null
        student.setStuName(null);

        // Create the Student, which fails.

        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllStudents() throws Exception {
        // Initialize the database
        studentMapper.insert(student);

        // Get all the studentList
        restStudentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId().intValue())))
            .andExpect(jsonPath("$.[*].stuName").value(hasItem(DEFAULT_STU_NAME)))
            .andExpect(jsonPath("$.[*].money").value(hasItem(DEFAULT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())));
    }

    @Test
    @Transactional
    void getStudent() throws Exception {
        // Initialize the database
        studentMapper.insert(student);

        // Get the student
        restStudentMockMvc
            .perform(get(ENTITY_API_URL_ID, student.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(student.getId().intValue()))
            .andExpect(jsonPath("$.stuName").value(DEFAULT_STU_NAME))
            .andExpect(jsonPath("$.money").value(DEFAULT_MONEY.intValue()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingStudent() throws Exception {
        // Get the student
        restStudentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewStudent() throws Exception {
        // Initialize the database
        studentMapper.insert(student);

        int databaseSizeBeforeUpdate = studentMapper.selectByExample(new StudentExample()).size();

        // Update the student
        Student updatedStudent = studentMapper.selectByPrimaryKey(student.getId());
        updatedStudent.stuName(UPDATED_STU_NAME).money(UPDATED_MONEY).language(UPDATED_LANGUAGE);

        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedStudent.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedStudent))
            )
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStuName()).isEqualTo(UPDATED_STU_NAME);
        assertThat(testStudent.getMoney()).isEqualTo(UPDATED_MONEY);
        assertThat(testStudent.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    void putNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentMapper.selectByExample(new StudentExample()).size();
        student.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, student.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentMapper.selectByExample(new StudentExample()).size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentMapper.selectByExample(new StudentExample()).size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Student in the database
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStudentWithPatch() throws Exception {
        // Initialize the database
        studentMapper.insert(student);

        int databaseSizeBeforeUpdate = studentMapper.selectByExample(new StudentExample()).size();

        // Update the student using partial update
        Student partialUpdatedStudent = new Student();
        partialUpdatedStudent.setId(student.getId());

        partialUpdatedStudent.stuName(UPDATED_STU_NAME);
        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStudent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStudent))
            )
            .andExpect(status().isOk());
        // Validate the Student in the database
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStuName()).isEqualTo(UPDATED_STU_NAME);
        assertThat(testStudent.getMoney()).isEqualTo(DEFAULT_MONEY);
        assertThat(testStudent.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
    }

    @Test
    @Transactional
    void fullUpdateStudentWithPatch() throws Exception {
        // Initialize the database
        studentMapper.insert(student);

        int databaseSizeBeforeUpdate = studentMapper.selectByExample(new StudentExample()).size();

        // Update the student using partial update
        Student partialUpdatedStudent = new Student();
        partialUpdatedStudent.setId(student.getId());

        partialUpdatedStudent.stuName(UPDATED_STU_NAME).money(UPDATED_MONEY).language(UPDATED_LANGUAGE);
        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStudent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStudent))
            )
            .andExpect(status().isOk());
        // Validate the Student in the database
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStuName()).isEqualTo(UPDATED_STU_NAME);
        assertThat(testStudent.getMoney()).isEqualTo(UPDATED_MONEY);
        assertThat(testStudent.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    void patchNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentMapper.selectByExample(new StudentExample()).size();
        student.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, student.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentMapper.selectByExample(new StudentExample()).size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(student))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentMapper.selectByExample(new StudentExample()).size();
        student.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Student in the database
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStudent() throws Exception {
        // Initialize the database
        studentMapper.insert(student);

        int databaseSizeBeforeDelete = studentMapper.selectByExample(new StudentExample()).size();

        // Delete the student
        restStudentMockMvc
            .perform(delete(ENTITY_API_URL_ID, student.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        assertThat(studentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
