package com.infosys.lbs.web.rest;

import com.infosys.lbs.LbsLiveEnterpriseApp;
import com.infosys.lbs.domain.Geofence;
import com.infosys.lbs.repository.GeofenceRepository;
import com.infosys.lbs.service.GeofenceService;
import com.infosys.lbs.service.dto.GeofenceDTO;
import com.infosys.lbs.service.mapper.GeofenceMapper;
import com.infosys.lbs.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.infosys.lbs.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GeofenceResource} REST controller.
 */
@SpringBootTest(classes = LbsLiveEnterpriseApp.class)
public class GeofenceResourceIT {

    private static final Long DEFAULT_FENCE_ID = 1L;
    private static final Long UPDATED_FENCE_ID = 2L;

    private static final String DEFAULT_FENCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FENCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_TIME = 1L;
    private static final Long UPDATED_CREATED_TIME = 2L;

    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIED_TIME = 1L;
    private static final Long UPDATED_MODIFIED_TIME = 2L;

    private static final Long DEFAULT_TYPE = 1L;
    private static final Long UPDATED_TYPE = 2L;

    private static final String DEFAULT_FENCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FENCE_CODE = "BBBBBBBBBB";

    @Autowired
    private GeofenceRepository geofenceRepository;

    @Autowired
    private GeofenceMapper geofenceMapper;

    @Autowired
    private GeofenceService geofenceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restGeofenceMockMvc;

    private Geofence geofence;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GeofenceResource geofenceResource = new GeofenceResource(geofenceService);
        this.restGeofenceMockMvc = MockMvcBuilders.standaloneSetup(geofenceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Geofence createEntity(EntityManager em) {
        Geofence geofence = new Geofence()
            .fenceId(DEFAULT_FENCE_ID)
            .fenceName(DEFAULT_FENCE_NAME)
            .createdBy(DEFAULT_CREATED_BY)
            .createdTime(DEFAULT_CREATED_TIME)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedTime(DEFAULT_MODIFIED_TIME)
            .type(DEFAULT_TYPE)
            .fenceCode(DEFAULT_FENCE_CODE);
        return geofence;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Geofence createUpdatedEntity(EntityManager em) {
        Geofence geofence = new Geofence()
            .fenceId(UPDATED_FENCE_ID)
            .fenceName(UPDATED_FENCE_NAME)
            .createdBy(UPDATED_CREATED_BY)
            .createdTime(UPDATED_CREATED_TIME)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedTime(UPDATED_MODIFIED_TIME)
            .type(UPDATED_TYPE)
            .fenceCode(UPDATED_FENCE_CODE);
        return geofence;
    }

    @BeforeEach
    public void initTest() {
        geofence = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeofence() throws Exception {
        int databaseSizeBeforeCreate = geofenceRepository.findAll().size();

        // Create the Geofence
        GeofenceDTO geofenceDTO = geofenceMapper.toDto(geofence);
        restGeofenceMockMvc.perform(post("/api/geofences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geofenceDTO)))
            .andExpect(status().isCreated());

        // Validate the Geofence in the database
        List<Geofence> geofenceList = geofenceRepository.findAll();
        assertThat(geofenceList).hasSize(databaseSizeBeforeCreate + 1);
        Geofence testGeofence = geofenceList.get(geofenceList.size() - 1);
        assertThat(testGeofence.getFenceId()).isEqualTo(DEFAULT_FENCE_ID);
        assertThat(testGeofence.getFenceName()).isEqualTo(DEFAULT_FENCE_NAME);
        assertThat(testGeofence.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGeofence.getCreatedTime()).isEqualTo(DEFAULT_CREATED_TIME);
        assertThat(testGeofence.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testGeofence.getModifiedTime()).isEqualTo(DEFAULT_MODIFIED_TIME);
        assertThat(testGeofence.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testGeofence.getFenceCode()).isEqualTo(DEFAULT_FENCE_CODE);
    }

    @Test
    @Transactional
    public void createGeofenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geofenceRepository.findAll().size();

        // Create the Geofence with an existing ID
        geofence.setId(1L);
        GeofenceDTO geofenceDTO = geofenceMapper.toDto(geofence);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeofenceMockMvc.perform(post("/api/geofences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geofenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Geofence in the database
        List<Geofence> geofenceList = geofenceRepository.findAll();
        assertThat(geofenceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeofences() throws Exception {
        // Initialize the database
        geofenceRepository.saveAndFlush(geofence);

        // Get all the geofenceList
        restGeofenceMockMvc.perform(get("/api/geofences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geofence.getId().intValue())))
            .andExpect(jsonPath("$.[*].fenceId").value(hasItem(DEFAULT_FENCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].fenceName").value(hasItem(DEFAULT_FENCE_NAME)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(DEFAULT_CREATED_TIME.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedTime").value(hasItem(DEFAULT_MODIFIED_TIME.intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].fenceCode").value(hasItem(DEFAULT_FENCE_CODE)));
    }
    
    @Test
    @Transactional
    public void getGeofence() throws Exception {
        // Initialize the database
        geofenceRepository.saveAndFlush(geofence);

        // Get the geofence
        restGeofenceMockMvc.perform(get("/api/geofences/{id}", geofence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(geofence.getId().intValue()))
            .andExpect(jsonPath("$.fenceId").value(DEFAULT_FENCE_ID.intValue()))
            .andExpect(jsonPath("$.fenceName").value(DEFAULT_FENCE_NAME))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdTime").value(DEFAULT_CREATED_TIME.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedTime").value(DEFAULT_MODIFIED_TIME.intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.intValue()))
            .andExpect(jsonPath("$.fenceCode").value(DEFAULT_FENCE_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingGeofence() throws Exception {
        // Get the geofence
        restGeofenceMockMvc.perform(get("/api/geofences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeofence() throws Exception {
        // Initialize the database
        geofenceRepository.saveAndFlush(geofence);

        int databaseSizeBeforeUpdate = geofenceRepository.findAll().size();

        // Update the geofence
        Geofence updatedGeofence = geofenceRepository.findById(geofence.getId()).get();
        // Disconnect from session so that the updates on updatedGeofence are not directly saved in db
        em.detach(updatedGeofence);
        updatedGeofence
            .fenceId(UPDATED_FENCE_ID)
            .fenceName(UPDATED_FENCE_NAME)
            .createdBy(UPDATED_CREATED_BY)
            .createdTime(UPDATED_CREATED_TIME)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedTime(UPDATED_MODIFIED_TIME)
            .type(UPDATED_TYPE)
            .fenceCode(UPDATED_FENCE_CODE);
        GeofenceDTO geofenceDTO = geofenceMapper.toDto(updatedGeofence);

        restGeofenceMockMvc.perform(put("/api/geofences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geofenceDTO)))
            .andExpect(status().isOk());

        // Validate the Geofence in the database
        List<Geofence> geofenceList = geofenceRepository.findAll();
        assertThat(geofenceList).hasSize(databaseSizeBeforeUpdate);
        Geofence testGeofence = geofenceList.get(geofenceList.size() - 1);
        assertThat(testGeofence.getFenceId()).isEqualTo(UPDATED_FENCE_ID);
        assertThat(testGeofence.getFenceName()).isEqualTo(UPDATED_FENCE_NAME);
        assertThat(testGeofence.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGeofence.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
        assertThat(testGeofence.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testGeofence.getModifiedTime()).isEqualTo(UPDATED_MODIFIED_TIME);
        assertThat(testGeofence.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testGeofence.getFenceCode()).isEqualTo(UPDATED_FENCE_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeofence() throws Exception {
        int databaseSizeBeforeUpdate = geofenceRepository.findAll().size();

        // Create the Geofence
        GeofenceDTO geofenceDTO = geofenceMapper.toDto(geofence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeofenceMockMvc.perform(put("/api/geofences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geofenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Geofence in the database
        List<Geofence> geofenceList = geofenceRepository.findAll();
        assertThat(geofenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeofence() throws Exception {
        // Initialize the database
        geofenceRepository.saveAndFlush(geofence);

        int databaseSizeBeforeDelete = geofenceRepository.findAll().size();

        // Delete the geofence
        restGeofenceMockMvc.perform(delete("/api/geofences/{id}", geofence.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Geofence> geofenceList = geofenceRepository.findAll();
        assertThat(geofenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
