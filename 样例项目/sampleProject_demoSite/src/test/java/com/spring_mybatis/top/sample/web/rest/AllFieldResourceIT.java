//本代码由 http://spring-mybatis.top 生成
//release# release_1.2.25 @ 2023年 10月 23日 星期一 11:56:43 CST
package com.spring_mybatis.top.sample.web.rest;

import static com.spring_mybatis.top.sample.web.rest.TestUtil.sameInstant;
import static com.spring_mybatis.top.sample.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.spring_mybatis.top.sample.IntegrationTest;
import com.spring_mybatis.top.sample.domain.AllFieldExample;
import com.spring_mybatis.top.sample.domain.AllFieldMapper;
import com.spring_mybatis.top.sample.domain.AllFieldWithBLOBs;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link AllFieldResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AllFieldResourceIT {

    private static final String DEFAULT_NAME = "Kzzzisd";
    private static final String UPDATED_NAME = "Lcxd";

    private static final Integer DEFAULT_NAME_INTEGER = 10;
    private static final Integer UPDATED_NAME_INTEGER = 11;

    private static final Long DEFAULT_NAME_LONG = 10L;
    private static final Long UPDATED_NAME_LONG = 11L;

    private static final BigDecimal DEFAULT_NAME_BIG_DECIMAL = new BigDecimal(10);
    private static final BigDecimal UPDATED_NAME_BIG_DECIMAL = new BigDecimal(11);

    private static final Float DEFAULT_NAME_FLOAT = 10F;
    private static final Float UPDATED_NAME_FLOAT = 11F;

    private static final Double DEFAULT_NAME_DOUBLE = 10D;
    private static final Double UPDATED_NAME_DOUBLE = 11D;

    private static final Boolean DEFAULT_NAME_BOOLEAN = false;
    private static final Boolean UPDATED_NAME_BOOLEAN = true;

    private static final LocalDate DEFAULT_NAME_LOCAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NAME_LOCAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_NAME_ZONED_DATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NAME_ZONED_DATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Instant DEFAULT_NAME_INSTANT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NAME_INSTANT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final byte[] DEFAULT_NAME_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NAME_BLOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_NAME_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NAME_BLOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_NAME_ANY_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NAME_ANY_BLOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_NAME_ANY_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NAME_ANY_BLOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_NAME_IMAGE_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NAME_IMAGE_BLOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_NAME_IMAGE_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NAME_IMAGE_BLOB_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NAME_TEXT_BLOB = "AAAAAAAAAA";
    private static final String UPDATED_NAME_TEXT_BLOB = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/all-fields";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AllFieldMapper allFieldMapper;

    @Autowired
    private MockMvc restAllFieldMockMvc;

    private AllFieldWithBLOBs allFieldWithBLOBs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AllFieldWithBLOBs createEntity() {
        AllFieldWithBLOBs allFieldWithBLOBs = new AllFieldWithBLOBs();
        allFieldWithBLOBs.setName(DEFAULT_NAME);
        allFieldWithBLOBs.setNameInteger(DEFAULT_NAME_INTEGER);
        allFieldWithBLOBs.setNameLong(DEFAULT_NAME_LONG);
        allFieldWithBLOBs.setNameBigDecimal(DEFAULT_NAME_BIG_DECIMAL);
        allFieldWithBLOBs.setNameFloat(DEFAULT_NAME_FLOAT);
        allFieldWithBLOBs.setNameDouble(DEFAULT_NAME_DOUBLE);
        allFieldWithBLOBs.setNameBoolean(DEFAULT_NAME_BOOLEAN);
        allFieldWithBLOBs.setNameLocalDate(DEFAULT_NAME_LOCAL_DATE);
        allFieldWithBLOBs.setNameZonedDateTime(DEFAULT_NAME_ZONED_DATE_TIME);
        allFieldWithBLOBs.setNameInstant(DEFAULT_NAME_INSTANT);
        allFieldWithBLOBs.setNameBlob(DEFAULT_NAME_BLOB);
        allFieldWithBLOBs.setNameBlobContentType(DEFAULT_NAME_BLOB_CONTENT_TYPE);
        allFieldWithBLOBs.setNameAnyBlob(DEFAULT_NAME_ANY_BLOB);
        allFieldWithBLOBs.setNameAnyBlobContentType(DEFAULT_NAME_ANY_BLOB_CONTENT_TYPE);
        allFieldWithBLOBs.setNameImageBlob(DEFAULT_NAME_IMAGE_BLOB);
        allFieldWithBLOBs.setNameImageBlobContentType(DEFAULT_NAME_IMAGE_BLOB_CONTENT_TYPE);
        allFieldWithBLOBs.setNameTextBlob(DEFAULT_NAME_TEXT_BLOB);
        return allFieldWithBLOBs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AllFieldWithBLOBs createUpdatedEntity() {
        AllFieldWithBLOBs allFieldWithBLOBs = new AllFieldWithBLOBs();
        allFieldWithBLOBs.setName(UPDATED_NAME);
        allFieldWithBLOBs.setNameInteger(UPDATED_NAME_INTEGER);
        allFieldWithBLOBs.setNameLong(UPDATED_NAME_LONG);
        allFieldWithBLOBs.setNameBigDecimal(UPDATED_NAME_BIG_DECIMAL);
        allFieldWithBLOBs.setNameFloat(UPDATED_NAME_FLOAT);
        allFieldWithBLOBs.setNameDouble(UPDATED_NAME_DOUBLE);
        allFieldWithBLOBs.setNameBoolean(UPDATED_NAME_BOOLEAN);
        allFieldWithBLOBs.setNameLocalDate(UPDATED_NAME_LOCAL_DATE);
        allFieldWithBLOBs.setNameZonedDateTime(UPDATED_NAME_ZONED_DATE_TIME);
        allFieldWithBLOBs.setNameInstant(UPDATED_NAME_INSTANT);
        allFieldWithBLOBs.setNameBlob(UPDATED_NAME_BLOB);
        allFieldWithBLOBs.setNameBlobContentType(UPDATED_NAME_BLOB_CONTENT_TYPE);
        allFieldWithBLOBs.setNameAnyBlob(UPDATED_NAME_ANY_BLOB);
        allFieldWithBLOBs.setNameAnyBlobContentType(UPDATED_NAME_ANY_BLOB_CONTENT_TYPE);
        allFieldWithBLOBs.setNameImageBlob(UPDATED_NAME_IMAGE_BLOB);
        allFieldWithBLOBs.setNameImageBlobContentType(UPDATED_NAME_IMAGE_BLOB_CONTENT_TYPE);
        allFieldWithBLOBs.setNameTextBlob(UPDATED_NAME_TEXT_BLOB);
        return allFieldWithBLOBs;
    }

    @BeforeEach
    public void initTest() {
        allFieldWithBLOBs = createEntity();
    }

    @Test
    @Transactional
    void createAllField() throws Exception {
        int databaseSizeBeforeCreate = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        // Create the AllFieldWithBLOBs
        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isCreated());

        // Validate the AllFieldWithBLOBs in the database
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeCreate + 1);
        AllFieldWithBLOBs testAllField = allFieldList.get(allFieldList.size() - 1);
        assertThat(testAllField.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAllField.getNameInteger()).isEqualTo(DEFAULT_NAME_INTEGER);
        assertThat(testAllField.getNameLong()).isEqualTo(DEFAULT_NAME_LONG);
        assertThat(testAllField.getNameBigDecimal()).isEqualByComparingTo(DEFAULT_NAME_BIG_DECIMAL);
        assertThat(testAllField.getNameFloat()).isEqualTo(DEFAULT_NAME_FLOAT);
        assertThat(testAllField.getNameDouble()).isEqualTo(DEFAULT_NAME_DOUBLE);
        assertThat(testAllField.getNameBoolean()).isEqualTo(DEFAULT_NAME_BOOLEAN);
        assertThat(testAllField.getNameLocalDate()).isEqualTo(DEFAULT_NAME_LOCAL_DATE);
        assertThat(testAllField.getNameZonedDateTime()).isEqualTo(DEFAULT_NAME_ZONED_DATE_TIME);
        assertThat(testAllField.getNameInstant()).isEqualTo(DEFAULT_NAME_INSTANT);
        assertThat(testAllField.getNameBlob()).isEqualTo(DEFAULT_NAME_BLOB);
        assertThat(testAllField.getNameBlobContentType()).isEqualTo(DEFAULT_NAME_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameAnyBlob()).isEqualTo(DEFAULT_NAME_ANY_BLOB);
        assertThat(testAllField.getNameAnyBlobContentType()).isEqualTo(DEFAULT_NAME_ANY_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameImageBlob()).isEqualTo(DEFAULT_NAME_IMAGE_BLOB);
        assertThat(testAllField.getNameImageBlobContentType()).isEqualTo(DEFAULT_NAME_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameTextBlob()).isEqualTo(DEFAULT_NAME_TEXT_BLOB);
    }

    @Test
    @Transactional
    void createAllFieldWithExistingId() throws Exception {
        // Create the AllFieldWithBLOBs with an existing ID
        allFieldWithBLOBs.setId(1L);

        int databaseSizeBeforeCreate = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        // Validate the AllFieldWithBLOBs in the database
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        // set the field null
        allFieldWithBLOBs.setName(null);

        // Create the AllFieldWithBLOBs, which fails.

        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIntegerIsRequired() throws Exception {
        int databaseSizeBeforeTest = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        // set the field null
        allFieldWithBLOBs.setNameInteger(null);

        // Create the AllFieldWithBLOBs, which fails.

        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameLongIsRequired() throws Exception {
        int databaseSizeBeforeTest = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        // set the field null
        allFieldWithBLOBs.setNameLong(null);

        // Create the AllFieldWithBLOBs, which fails.

        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameBigDecimalIsRequired() throws Exception {
        int databaseSizeBeforeTest = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        // set the field null
        allFieldWithBLOBs.setNameBigDecimal(null);

        // Create the AllFieldWithBLOBs, which fails.

        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameFloatIsRequired() throws Exception {
        int databaseSizeBeforeTest = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        // set the field null
        allFieldWithBLOBs.setNameFloat(null);

        // Create the AllFieldWithBLOBs, which fails.

        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameDoubleIsRequired() throws Exception {
        int databaseSizeBeforeTest = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        // set the field null
        allFieldWithBLOBs.setNameDouble(null);

        // Create the AllFieldWithBLOBs, which fails.

        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameBooleanIsRequired() throws Exception {
        int databaseSizeBeforeTest = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        // set the field null
        allFieldWithBLOBs.setNameBoolean(null);

        // Create the AllFieldWithBLOBs, which fails.

        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameLocalDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        // set the field null
        allFieldWithBLOBs.setNameLocalDate(null);

        // Create the AllFieldWithBLOBs, which fails.

        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameZonedDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        // set the field null
        allFieldWithBLOBs.setNameZonedDateTime(null);

        // Create the AllFieldWithBLOBs, which fails.

        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameInstantIsRequired() throws Exception {
        int databaseSizeBeforeTest = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        // set the field null
        allFieldWithBLOBs.setNameInstant(null);

        // Create the AllFieldWithBLOBs, which fails.

        restAllFieldMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAllFields() throws Exception {
        // Initialize the database
        allFieldMapper.insert(allFieldWithBLOBs);

        // Get all the allFieldList
        restAllFieldMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(allFieldWithBLOBs.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].nameInteger").value(hasItem(DEFAULT_NAME_INTEGER)))
            .andExpect(jsonPath("$.[*].nameLong").value(hasItem(DEFAULT_NAME_LONG.intValue())))
            .andExpect(jsonPath("$.[*].nameBigDecimal").value(hasItem(sameNumber(DEFAULT_NAME_BIG_DECIMAL))))
            .andExpect(jsonPath("$.[*].nameFloat").value(hasItem(DEFAULT_NAME_FLOAT.doubleValue())))
            .andExpect(jsonPath("$.[*].nameDouble").value(hasItem(DEFAULT_NAME_DOUBLE.doubleValue())))
            .andExpect(jsonPath("$.[*].nameBoolean").value(hasItem(DEFAULT_NAME_BOOLEAN.booleanValue())))
            .andExpect(jsonPath("$.[*].nameLocalDate").value(hasItem(DEFAULT_NAME_LOCAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].nameZonedDateTime").value(hasItem(sameInstant(DEFAULT_NAME_ZONED_DATE_TIME))))
            .andExpect(jsonPath("$.[*].nameInstant").value(hasItem(DEFAULT_NAME_INSTANT.toString())))
            .andExpect(jsonPath("$.[*].nameBlobContentType").value(hasItem(DEFAULT_NAME_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].nameBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_NAME_BLOB))))
            .andExpect(jsonPath("$.[*].nameAnyBlobContentType").value(hasItem(DEFAULT_NAME_ANY_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].nameAnyBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_NAME_ANY_BLOB))))
            .andExpect(jsonPath("$.[*].nameImageBlobContentType").value(hasItem(DEFAULT_NAME_IMAGE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].nameImageBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_NAME_IMAGE_BLOB))))
            .andExpect(jsonPath("$.[*].nameTextBlob").value(hasItem(DEFAULT_NAME_TEXT_BLOB.toString())));
    }

    @Test
    @Transactional
    void getAllField() throws Exception {
        // Initialize the database
        allFieldMapper.insert(allFieldWithBLOBs);

        // Get the allFieldWithBLOBs
        restAllFieldMockMvc
            .perform(get(ENTITY_API_URL_ID, allFieldWithBLOBs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(allFieldWithBLOBs.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.nameInteger").value(DEFAULT_NAME_INTEGER))
            .andExpect(jsonPath("$.nameLong").value(DEFAULT_NAME_LONG.intValue()))
            .andExpect(jsonPath("$.nameBigDecimal").value(sameNumber(DEFAULT_NAME_BIG_DECIMAL)))
            .andExpect(jsonPath("$.nameFloat").value(DEFAULT_NAME_FLOAT.doubleValue()))
            .andExpect(jsonPath("$.nameDouble").value(DEFAULT_NAME_DOUBLE.doubleValue()))
            .andExpect(jsonPath("$.nameBoolean").value(DEFAULT_NAME_BOOLEAN.booleanValue()))
            .andExpect(jsonPath("$.nameLocalDate").value(DEFAULT_NAME_LOCAL_DATE.toString()))
            .andExpect(jsonPath("$.nameZonedDateTime").value(sameInstant(DEFAULT_NAME_ZONED_DATE_TIME)))
            .andExpect(jsonPath("$.nameInstant").value(DEFAULT_NAME_INSTANT.toString()))
            .andExpect(jsonPath("$.nameBlobContentType").value(DEFAULT_NAME_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.nameBlob").value(Base64Utils.encodeToString(DEFAULT_NAME_BLOB)))
            .andExpect(jsonPath("$.nameAnyBlobContentType").value(DEFAULT_NAME_ANY_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.nameAnyBlob").value(Base64Utils.encodeToString(DEFAULT_NAME_ANY_BLOB)))
            .andExpect(jsonPath("$.nameImageBlobContentType").value(DEFAULT_NAME_IMAGE_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.nameImageBlob").value(Base64Utils.encodeToString(DEFAULT_NAME_IMAGE_BLOB)))
            .andExpect(jsonPath("$.nameTextBlob").value(DEFAULT_NAME_TEXT_BLOB.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAllField() throws Exception {
        // Get the allFieldWithBLOBs
        restAllFieldMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAllField() throws Exception {
        // Initialize the database
        allFieldMapper.insert(allFieldWithBLOBs);

        int databaseSizeBeforeUpdate = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();

        // Update the allFieldWithBLOBs
        AllFieldWithBLOBs updatedAllField = allFieldMapper.selectByPrimaryKey(allFieldWithBLOBs.getId());
        updatedAllField
            .name(UPDATED_NAME)
            .nameInteger(UPDATED_NAME_INTEGER)
            .nameLong(UPDATED_NAME_LONG)
            .nameBigDecimal(UPDATED_NAME_BIG_DECIMAL)
            .nameFloat(UPDATED_NAME_FLOAT)
            .nameDouble(UPDATED_NAME_DOUBLE)
            .nameBoolean(UPDATED_NAME_BOOLEAN)
            .nameLocalDate(UPDATED_NAME_LOCAL_DATE)
            .nameZonedDateTime(UPDATED_NAME_ZONED_DATE_TIME)
            .nameInstant(UPDATED_NAME_INSTANT)
            .nameBlob(UPDATED_NAME_BLOB)
            .nameBlobContentType(UPDATED_NAME_BLOB_CONTENT_TYPE)
            .nameAnyBlob(UPDATED_NAME_ANY_BLOB)
            .nameAnyBlobContentType(UPDATED_NAME_ANY_BLOB_CONTENT_TYPE)
            .nameImageBlob(UPDATED_NAME_IMAGE_BLOB)
            .nameImageBlobContentType(UPDATED_NAME_IMAGE_BLOB_CONTENT_TYPE)
            .nameTextBlob(UPDATED_NAME_TEXT_BLOB);

        restAllFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAllField.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAllField))
            )
            .andExpect(status().isOk());

        // Validate the AllFieldWithBLOBs in the database
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeUpdate);
        AllFieldWithBLOBs testAllField = allFieldList.get(allFieldList.size() - 1);
        assertThat(testAllField.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAllField.getNameInteger()).isEqualTo(UPDATED_NAME_INTEGER);
        assertThat(testAllField.getNameLong()).isEqualTo(UPDATED_NAME_LONG);
        assertThat(testAllField.getNameBigDecimal()).isEqualByComparingTo(UPDATED_NAME_BIG_DECIMAL);
        assertThat(testAllField.getNameFloat()).isEqualTo(UPDATED_NAME_FLOAT);
        assertThat(testAllField.getNameDouble()).isEqualTo(UPDATED_NAME_DOUBLE);
        assertThat(testAllField.getNameBoolean()).isEqualTo(UPDATED_NAME_BOOLEAN);
        assertThat(testAllField.getNameLocalDate()).isEqualTo(UPDATED_NAME_LOCAL_DATE);
        assertThat(testAllField.getNameZonedDateTime()).isEqualTo(UPDATED_NAME_ZONED_DATE_TIME);
        assertThat(testAllField.getNameInstant()).isEqualTo(UPDATED_NAME_INSTANT);
        assertThat(testAllField.getNameBlob()).isEqualTo(UPDATED_NAME_BLOB);
        assertThat(testAllField.getNameBlobContentType()).isEqualTo(UPDATED_NAME_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameAnyBlob()).isEqualTo(UPDATED_NAME_ANY_BLOB);
        assertThat(testAllField.getNameAnyBlobContentType()).isEqualTo(UPDATED_NAME_ANY_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameImageBlob()).isEqualTo(UPDATED_NAME_IMAGE_BLOB);
        assertThat(testAllField.getNameImageBlobContentType()).isEqualTo(UPDATED_NAME_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameTextBlob()).isEqualTo(UPDATED_NAME_TEXT_BLOB);
    }

    @Test
    @Transactional
    void putNonExistingAllField() throws Exception {
        int databaseSizeBeforeUpdate = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        allFieldWithBLOBs.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAllFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, allFieldWithBLOBs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        // Validate the AllFieldWithBLOBs in the database
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAllField() throws Exception {
        int databaseSizeBeforeUpdate = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        allFieldWithBLOBs.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAllFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        // Validate the AllFieldWithBLOBs in the database
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAllField() throws Exception {
        int databaseSizeBeforeUpdate = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        allFieldWithBLOBs.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAllFieldMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AllFieldWithBLOBs in the database
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAllFieldWithPatch() throws Exception {
        // Initialize the database
        allFieldMapper.insert(allFieldWithBLOBs);

        int databaseSizeBeforeUpdate = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();

        // Update the allFieldWithBLOBs using partial update
        AllFieldWithBLOBs partialUpdatedAllField = new AllFieldWithBLOBs();
        partialUpdatedAllField.setId(allFieldWithBLOBs.getId());

        partialUpdatedAllField
            .name(UPDATED_NAME)
            .nameBigDecimal(UPDATED_NAME_BIG_DECIMAL)
            .nameDouble(UPDATED_NAME_DOUBLE)
            .nameBoolean(UPDATED_NAME_BOOLEAN)
            .nameZonedDateTime(UPDATED_NAME_ZONED_DATE_TIME)
            .nameBlob(UPDATED_NAME_BLOB)
            .nameBlobContentType(UPDATED_NAME_BLOB_CONTENT_TYPE)
            .nameImageBlob(UPDATED_NAME_IMAGE_BLOB)
            .nameImageBlobContentType(UPDATED_NAME_IMAGE_BLOB_CONTENT_TYPE);
        restAllFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAllField.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAllField))
            )
            .andExpect(status().isOk());
        // Validate the AllFieldWithBLOBs in the database
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeUpdate);
        AllFieldWithBLOBs testAllField = allFieldList.get(allFieldList.size() - 1);
        assertThat(testAllField.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAllField.getNameInteger()).isEqualTo(DEFAULT_NAME_INTEGER);
        assertThat(testAllField.getNameLong()).isEqualTo(DEFAULT_NAME_LONG);
        assertThat(testAllField.getNameBigDecimal()).isEqualByComparingTo(UPDATED_NAME_BIG_DECIMAL);
        assertThat(testAllField.getNameFloat()).isEqualTo(DEFAULT_NAME_FLOAT);
        assertThat(testAllField.getNameDouble()).isEqualTo(UPDATED_NAME_DOUBLE);
        assertThat(testAllField.getNameBoolean()).isEqualTo(UPDATED_NAME_BOOLEAN);
        assertThat(testAllField.getNameLocalDate()).isEqualTo(DEFAULT_NAME_LOCAL_DATE);
        assertThat(testAllField.getNameZonedDateTime()).isEqualTo(UPDATED_NAME_ZONED_DATE_TIME);
        assertThat(testAllField.getNameInstant()).isEqualTo(DEFAULT_NAME_INSTANT);
        assertThat(testAllField.getNameBlob()).isEqualTo(UPDATED_NAME_BLOB);
        assertThat(testAllField.getNameBlobContentType()).isEqualTo(UPDATED_NAME_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameAnyBlob()).isEqualTo(DEFAULT_NAME_ANY_BLOB);
        assertThat(testAllField.getNameAnyBlobContentType()).isEqualTo(DEFAULT_NAME_ANY_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameImageBlob()).isEqualTo(UPDATED_NAME_IMAGE_BLOB);
        assertThat(testAllField.getNameImageBlobContentType()).isEqualTo(UPDATED_NAME_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameTextBlob()).isEqualTo(DEFAULT_NAME_TEXT_BLOB);
    }

    @Test
    @Transactional
    void fullUpdateAllFieldWithPatch() throws Exception {
        // Initialize the database
        allFieldMapper.insert(allFieldWithBLOBs);

        int databaseSizeBeforeUpdate = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();

        // Update the allFieldWithBLOBs using partial update
        AllFieldWithBLOBs partialUpdatedAllField = new AllFieldWithBLOBs();
        partialUpdatedAllField.setId(allFieldWithBLOBs.getId());

        partialUpdatedAllField
            .name(UPDATED_NAME)
            .nameInteger(UPDATED_NAME_INTEGER)
            .nameLong(UPDATED_NAME_LONG)
            .nameBigDecimal(UPDATED_NAME_BIG_DECIMAL)
            .nameFloat(UPDATED_NAME_FLOAT)
            .nameDouble(UPDATED_NAME_DOUBLE)
            .nameBoolean(UPDATED_NAME_BOOLEAN)
            .nameLocalDate(UPDATED_NAME_LOCAL_DATE)
            .nameZonedDateTime(UPDATED_NAME_ZONED_DATE_TIME)
            .nameInstant(UPDATED_NAME_INSTANT)
            .nameBlob(UPDATED_NAME_BLOB)
            .nameBlobContentType(UPDATED_NAME_BLOB_CONTENT_TYPE)
            .nameAnyBlob(UPDATED_NAME_ANY_BLOB)
            .nameAnyBlobContentType(UPDATED_NAME_ANY_BLOB_CONTENT_TYPE)
            .nameImageBlob(UPDATED_NAME_IMAGE_BLOB)
            .nameImageBlobContentType(UPDATED_NAME_IMAGE_BLOB_CONTENT_TYPE)
            .nameTextBlob(UPDATED_NAME_TEXT_BLOB);
        restAllFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAllField.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAllField))
            )
            .andExpect(status().isOk());
        // Validate the AllFieldWithBLOBs in the database
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeUpdate);
        AllFieldWithBLOBs testAllField = allFieldList.get(allFieldList.size() - 1);
        assertThat(testAllField.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAllField.getNameInteger()).isEqualTo(UPDATED_NAME_INTEGER);
        assertThat(testAllField.getNameLong()).isEqualTo(UPDATED_NAME_LONG);
        assertThat(testAllField.getNameBigDecimal()).isEqualByComparingTo(UPDATED_NAME_BIG_DECIMAL);
        assertThat(testAllField.getNameFloat()).isEqualTo(UPDATED_NAME_FLOAT);
        assertThat(testAllField.getNameDouble()).isEqualTo(UPDATED_NAME_DOUBLE);
        assertThat(testAllField.getNameBoolean()).isEqualTo(UPDATED_NAME_BOOLEAN);
        assertThat(testAllField.getNameLocalDate()).isEqualTo(UPDATED_NAME_LOCAL_DATE);
        assertThat(testAllField.getNameZonedDateTime()).isEqualTo(UPDATED_NAME_ZONED_DATE_TIME);
        assertThat(testAllField.getNameInstant()).isEqualTo(UPDATED_NAME_INSTANT);
        assertThat(testAllField.getNameBlob()).isEqualTo(UPDATED_NAME_BLOB);
        assertThat(testAllField.getNameBlobContentType()).isEqualTo(UPDATED_NAME_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameAnyBlob()).isEqualTo(UPDATED_NAME_ANY_BLOB);
        assertThat(testAllField.getNameAnyBlobContentType()).isEqualTo(UPDATED_NAME_ANY_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameImageBlob()).isEqualTo(UPDATED_NAME_IMAGE_BLOB);
        assertThat(testAllField.getNameImageBlobContentType()).isEqualTo(UPDATED_NAME_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testAllField.getNameTextBlob()).isEqualTo(UPDATED_NAME_TEXT_BLOB);
    }

    @Test
    @Transactional
    void patchNonExistingAllField() throws Exception {
        int databaseSizeBeforeUpdate = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        allFieldWithBLOBs.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAllFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, allFieldWithBLOBs.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        // Validate the AllFieldWithBLOBs in the database
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAllField() throws Exception {
        int databaseSizeBeforeUpdate = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        allFieldWithBLOBs.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAllFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isBadRequest());

        // Validate the AllFieldWithBLOBs in the database
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAllField() throws Exception {
        int databaseSizeBeforeUpdate = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();
        allFieldWithBLOBs.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAllFieldMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(allFieldWithBLOBs))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AllFieldWithBLOBs in the database
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAllField() throws Exception {
        // Initialize the database
        allFieldMapper.insert(allFieldWithBLOBs);

        int databaseSizeBeforeDelete = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample()).size();

        // Delete the allFieldWithBLOBs
        restAllFieldMockMvc
            .perform(delete(ENTITY_API_URL_ID, allFieldWithBLOBs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AllFieldWithBLOBs> allFieldList = allFieldMapper.selectByExampleWithBLOBs(new AllFieldExample());
        assertThat(allFieldList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
