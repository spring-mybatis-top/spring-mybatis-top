//本代码由 http://spring-mybatis.top 生成
//release# release_1.2.25 @ 2023年 10月 23日 星期一 11:56:43 CST
package com.spring_mybatis.top.helloworld.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.spring_mybatis.top.helloworld.IntegrationTest;
import com.spring_mybatis.top.helloworld.domain.HelloWorld;
import com.spring_mybatis.top.helloworld.domain.HelloWorldExample;
import com.spring_mybatis.top.helloworld.domain.HelloWorldMapper;
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

/**
 * Integration tests for the {@link HelloWorldResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HelloWorldResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final String ENTITY_API_URL = "/api/hello-worlds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HelloWorldMapper helloWorldMapper;

    @Autowired
    private MockMvc restHelloWorldMockMvc;

    private HelloWorld helloWorld;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HelloWorld createEntity() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setName(DEFAULT_NAME);
        helloWorld.setAge(DEFAULT_AGE);
        return helloWorld;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HelloWorld createUpdatedEntity() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setName(UPDATED_NAME);
        helloWorld.setAge(UPDATED_AGE);
        return helloWorld;
    }

    @BeforeEach
    public void initTest() {
        helloWorld = createEntity();
    }

    @Test
    @Transactional
    void createHelloWorld() throws Exception {
        int databaseSizeBeforeCreate = helloWorldMapper.selectByExample(new HelloWorldExample()).size();
        // Create the HelloWorld
        restHelloWorldMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(helloWorld)))
            .andExpect(status().isCreated());

        // Validate the HelloWorld in the database
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeCreate + 1);
        HelloWorld testHelloWorld = helloWorldList.get(helloWorldList.size() - 1);
        assertThat(testHelloWorld.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHelloWorld.getAge()).isEqualTo(DEFAULT_AGE);
    }

    @Test
    @Transactional
    void createHelloWorldWithExistingId() throws Exception {
        // Create the HelloWorld with an existing ID
        helloWorld.setId(1L);

        int databaseSizeBeforeCreate = helloWorldMapper.selectByExample(new HelloWorldExample()).size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHelloWorldMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(helloWorld)))
            .andExpect(status().isBadRequest());

        // Validate the HelloWorld in the database
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHelloWorlds() throws Exception {
        // Initialize the database
        helloWorldMapper.insert(helloWorld);

        // Get all the helloWorldList
        restHelloWorldMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(helloWorld.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)));
    }

    @Test
    @Transactional
    void getHelloWorld() throws Exception {
        // Initialize the database
        helloWorldMapper.insert(helloWorld);

        // Get the helloWorld
        restHelloWorldMockMvc
            .perform(get(ENTITY_API_URL_ID, helloWorld.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(helloWorld.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE));
    }

    @Test
    @Transactional
    void getNonExistingHelloWorld() throws Exception {
        // Get the helloWorld
        restHelloWorldMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewHelloWorld() throws Exception {
        // Initialize the database
        helloWorldMapper.insert(helloWorld);

        int databaseSizeBeforeUpdate = helloWorldMapper.selectByExample(new HelloWorldExample()).size();

        // Update the helloWorld
        HelloWorld updatedHelloWorld = helloWorldMapper.selectByPrimaryKey(helloWorld.getId());
        updatedHelloWorld.name(UPDATED_NAME).age(UPDATED_AGE);

        restHelloWorldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedHelloWorld.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedHelloWorld))
            )
            .andExpect(status().isOk());

        // Validate the HelloWorld in the database
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeUpdate);
        HelloWorld testHelloWorld = helloWorldList.get(helloWorldList.size() - 1);
        assertThat(testHelloWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHelloWorld.getAge()).isEqualTo(UPDATED_AGE);
    }

    @Test
    @Transactional
    void putNonExistingHelloWorld() throws Exception {
        int databaseSizeBeforeUpdate = helloWorldMapper.selectByExample(new HelloWorldExample()).size();
        helloWorld.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHelloWorldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, helloWorld.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(helloWorld))
            )
            .andExpect(status().isBadRequest());

        // Validate the HelloWorld in the database
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHelloWorld() throws Exception {
        int databaseSizeBeforeUpdate = helloWorldMapper.selectByExample(new HelloWorldExample()).size();
        helloWorld.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHelloWorldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(helloWorld))
            )
            .andExpect(status().isBadRequest());

        // Validate the HelloWorld in the database
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHelloWorld() throws Exception {
        int databaseSizeBeforeUpdate = helloWorldMapper.selectByExample(new HelloWorldExample()).size();
        helloWorld.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHelloWorldMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(helloWorld)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HelloWorld in the database
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHelloWorldWithPatch() throws Exception {
        // Initialize the database
        helloWorldMapper.insert(helloWorld);

        int databaseSizeBeforeUpdate = helloWorldMapper.selectByExample(new HelloWorldExample()).size();

        // Update the helloWorld using partial update
        HelloWorld partialUpdatedHelloWorld = new HelloWorld();
        partialUpdatedHelloWorld.setId(helloWorld.getId());

        partialUpdatedHelloWorld.name(UPDATED_NAME);
        restHelloWorldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHelloWorld.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHelloWorld))
            )
            .andExpect(status().isOk());
        // Validate the HelloWorld in the database
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeUpdate);
        HelloWorld testHelloWorld = helloWorldList.get(helloWorldList.size() - 1);
        assertThat(testHelloWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHelloWorld.getAge()).isEqualTo(DEFAULT_AGE);
    }

    @Test
    @Transactional
    void fullUpdateHelloWorldWithPatch() throws Exception {
        // Initialize the database
        helloWorldMapper.insert(helloWorld);

        int databaseSizeBeforeUpdate = helloWorldMapper.selectByExample(new HelloWorldExample()).size();

        // Update the helloWorld using partial update
        HelloWorld partialUpdatedHelloWorld = new HelloWorld();
        partialUpdatedHelloWorld.setId(helloWorld.getId());

        partialUpdatedHelloWorld.name(UPDATED_NAME).age(UPDATED_AGE);
        restHelloWorldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHelloWorld.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHelloWorld))
            )
            .andExpect(status().isOk());
        // Validate the HelloWorld in the database
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeUpdate);
        HelloWorld testHelloWorld = helloWorldList.get(helloWorldList.size() - 1);
        assertThat(testHelloWorld.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHelloWorld.getAge()).isEqualTo(UPDATED_AGE);
    }

    @Test
    @Transactional
    void patchNonExistingHelloWorld() throws Exception {
        int databaseSizeBeforeUpdate = helloWorldMapper.selectByExample(new HelloWorldExample()).size();
        helloWorld.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHelloWorldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, helloWorld.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(helloWorld))
            )
            .andExpect(status().isBadRequest());

        // Validate the HelloWorld in the database
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHelloWorld() throws Exception {
        int databaseSizeBeforeUpdate = helloWorldMapper.selectByExample(new HelloWorldExample()).size();
        helloWorld.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHelloWorldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(helloWorld))
            )
            .andExpect(status().isBadRequest());

        // Validate the HelloWorld in the database
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHelloWorld() throws Exception {
        int databaseSizeBeforeUpdate = helloWorldMapper.selectByExample(new HelloWorldExample()).size();
        helloWorld.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHelloWorldMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(helloWorld))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HelloWorld in the database
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHelloWorld() throws Exception {
        // Initialize the database
        helloWorldMapper.insert(helloWorld);

        int databaseSizeBeforeDelete = helloWorldMapper.selectByExample(new HelloWorldExample()).size();

        // Delete the helloWorld
        restHelloWorldMockMvc
            .perform(delete(ENTITY_API_URL_ID, helloWorld.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HelloWorld> helloWorldList = helloWorldMapper.selectByExample(new HelloWorldExample());
        assertThat(helloWorldList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
