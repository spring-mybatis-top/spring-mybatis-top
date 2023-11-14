//本代码由 http://spring-mybatis.top 生成
//release# RELEASE_NAME
package com.spring_mybatis.top.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.spring_mybatis.top.sample.IntegrationTest;
import com.spring_mybatis.top.sample.domain.Info;
import com.spring_mybatis.top.sample.domain.InfoExample;
import com.spring_mybatis.top.sample.domain.InfoMapper;
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
 * Integration tests for the {@link InfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InfoResourceIT {

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InfoMapper infoMapper;

    @Autowired
    private MockMvc restInfoMockMvc;

    private Info info;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Info createEntity() {
        Info info = new Info();
        info.setComments(DEFAULT_COMMENTS);
        info.setImage(DEFAULT_IMAGE);
        return info;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Info createUpdatedEntity() {
        Info info = new Info();
        info.setComments(UPDATED_COMMENTS);
        info.setImage(UPDATED_IMAGE);
        return info;
    }

    @BeforeEach
    public void initTest() {
        info = createEntity();
    }

    @Test
    @Transactional
    void createInfo() throws Exception {
        int databaseSizeBeforeCreate = infoMapper.selectByExample(new InfoExample()).size();
        // Create the Info
        restInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(info)))
            .andExpect(status().isCreated());

        // Validate the Info in the database
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeCreate + 1);
        Info testInfo = infoList.get(infoList.size() - 1);
        assertThat(testInfo.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testInfo.getImage()).isEqualTo(DEFAULT_IMAGE);
    }

    @Test
    @Transactional
    void createInfoWithExistingId() throws Exception {
        // Create the Info with an existing ID
        info.setId(1L);

        int databaseSizeBeforeCreate = infoMapper.selectByExample(new InfoExample()).size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(info)))
            .andExpect(status().isBadRequest());

        // Validate the Info in the database
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = infoMapper.selectByExample(new InfoExample()).size();
        // set the field null
        info.setComments(null);

        // Create the Info, which fails.

        restInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(info)))
            .andExpect(status().isBadRequest());

        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkImageIsRequired() throws Exception {
        int databaseSizeBeforeTest = infoMapper.selectByExample(new InfoExample()).size();
        // set the field null
        info.setImage(null);

        // Create the Info, which fails.

        restInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(info)))
            .andExpect(status().isBadRequest());

        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllInfos() throws Exception {
        // Initialize the database
        infoMapper.insert(info);

        // Get all the infoList
        restInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(info.getId().intValue())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)));
    }

    @Test
    @Transactional
    void getInfo() throws Exception {
        // Initialize the database
        infoMapper.insert(info);

        // Get the info
        restInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, info.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(info.getId().intValue()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE));
    }

    @Test
    @Transactional
    void getNonExistingInfo() throws Exception {
        // Get the info
        restInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInfo() throws Exception {
        // Initialize the database
        infoMapper.insert(info);

        int databaseSizeBeforeUpdate = infoMapper.selectByExample(new InfoExample()).size();

        // Update the info
        Info updatedInfo = infoMapper.selectByPrimaryKey(info.getId());
        updatedInfo.comments(UPDATED_COMMENTS).image(UPDATED_IMAGE);

        restInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedInfo))
            )
            .andExpect(status().isOk());

        // Validate the Info in the database
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
        Info testInfo = infoList.get(infoList.size() - 1);
        assertThat(testInfo.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testInfo.getImage()).isEqualTo(UPDATED_IMAGE);
    }

    @Test
    @Transactional
    void putNonExistingInfo() throws Exception {
        int databaseSizeBeforeUpdate = infoMapper.selectByExample(new InfoExample()).size();
        info.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, info.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(info))
            )
            .andExpect(status().isBadRequest());

        // Validate the Info in the database
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInfo() throws Exception {
        int databaseSizeBeforeUpdate = infoMapper.selectByExample(new InfoExample()).size();
        info.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(info))
            )
            .andExpect(status().isBadRequest());

        // Validate the Info in the database
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInfo() throws Exception {
        int databaseSizeBeforeUpdate = infoMapper.selectByExample(new InfoExample()).size();
        info.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(info)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Info in the database
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInfoWithPatch() throws Exception {
        // Initialize the database
        infoMapper.insert(info);

        int databaseSizeBeforeUpdate = infoMapper.selectByExample(new InfoExample()).size();

        // Update the info using partial update
        Info partialUpdatedInfo = new Info();
        partialUpdatedInfo.setId(info.getId());

        partialUpdatedInfo.comments(UPDATED_COMMENTS);

        restInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInfo))
            )
            .andExpect(status().isOk());

        // Validate the Info in the database
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
        Info testInfo = infoList.get(infoList.size() - 1);
        assertThat(testInfo.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testInfo.getImage()).isEqualTo(DEFAULT_IMAGE);
    }

    @Test
    @Transactional
    void fullUpdateInfoWithPatch() throws Exception {
        // Initialize the database
        infoMapper.insert(info);

        int databaseSizeBeforeUpdate = infoMapper.selectByExample(new InfoExample()).size();

        // Update the info using partial update
        Info partialUpdatedInfo = new Info();
        partialUpdatedInfo.setId(info.getId());

        partialUpdatedInfo.comments(UPDATED_COMMENTS).image(UPDATED_IMAGE);

        restInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInfo))
            )
            .andExpect(status().isOk());

        // Validate the Info in the database
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
        Info testInfo = infoList.get(infoList.size() - 1);
        assertThat(testInfo.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testInfo.getImage()).isEqualTo(UPDATED_IMAGE);
    }

    @Test
    @Transactional
    void patchNonExistingInfo() throws Exception {
        int databaseSizeBeforeUpdate = infoMapper.selectByExample(new InfoExample()).size();
        info.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, info.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(info))
            )
            .andExpect(status().isBadRequest());

        // Validate the Info in the database
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInfo() throws Exception {
        int databaseSizeBeforeUpdate = infoMapper.selectByExample(new InfoExample()).size();
        info.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(info))
            )
            .andExpect(status().isBadRequest());

        // Validate the Info in the database
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInfo() throws Exception {
        int databaseSizeBeforeUpdate = infoMapper.selectByExample(new InfoExample()).size();
        info.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(info)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Info in the database
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInfo() throws Exception {
        // Initialize the database
        infoMapper.insert(info);

        int databaseSizeBeforeDelete = infoMapper.selectByExample(new InfoExample()).size();

        // Delete the info
        restInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, info.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Info> infoList = infoMapper.selectByExample(new InfoExample());
        assertThat(infoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
