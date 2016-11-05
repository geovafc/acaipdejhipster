package br.com.acaipaidegua.web.rest;

import br.com.acaipaidegua.AcaipdejhipsterApp;

import br.com.acaipaidegua.domain.Celular;
import br.com.acaipaidegua.repository.CelularRepository;
import br.com.acaipaidegua.service.dto.CelularDTO;
import br.com.acaipaidegua.service.mapper.CelularMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CelularResource REST controller.
 *
 * @see CelularResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AcaipdejhipsterApp.class)
public class CelularResourceIntTest {

    private static final String DEFAULT_NUMERO = "AAAAA";
    private static final String UPDATED_NUMERO = "BBBBB";

    private static final String DEFAULT_OPERADORA = "AAAAA";
    private static final String UPDATED_OPERADORA = "BBBBB";

    @Inject
    private CelularRepository celularRepository;

    @Inject
    private CelularMapper celularMapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restCelularMockMvc;

    private Celular celular;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CelularResource celularResource = new CelularResource();
        ReflectionTestUtils.setField(celularResource, "celularRepository", celularRepository);
        ReflectionTestUtils.setField(celularResource, "celularMapper", celularMapper);
        this.restCelularMockMvc = MockMvcBuilders.standaloneSetup(celularResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Celular createEntity(EntityManager em) {
        Celular celular = new Celular()
                .numero(DEFAULT_NUMERO)
                .operadora(DEFAULT_OPERADORA);
        return celular;
    }

    @Before
    public void initTest() {
        celular = createEntity(em);
    }

    @Test
    @Transactional
    public void createCelular() throws Exception {
        int databaseSizeBeforeCreate = celularRepository.findAll().size();

        // Create the Celular
        CelularDTO celularDTO = celularMapper.celularToCelularDTO(celular);

        restCelularMockMvc.perform(post("/api/celulars")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(celularDTO)))
                .andExpect(status().isCreated());

        // Validate the Celular in the database
        List<Celular> celulars = celularRepository.findAll();
        assertThat(celulars).hasSize(databaseSizeBeforeCreate + 1);
        Celular testCelular = celulars.get(celulars.size() - 1);
        assertThat(testCelular.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testCelular.getOperadora()).isEqualTo(DEFAULT_OPERADORA);
    }

    @Test
    @Transactional
    public void getAllCelulars() throws Exception {
        // Initialize the database
        celularRepository.saveAndFlush(celular);

        // Get all the celulars
        restCelularMockMvc.perform(get("/api/celulars?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(celular.getId().intValue())))
                .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
                .andExpect(jsonPath("$.[*].operadora").value(hasItem(DEFAULT_OPERADORA.toString())));
    }

    @Test
    @Transactional
    public void getCelular() throws Exception {
        // Initialize the database
        celularRepository.saveAndFlush(celular);

        // Get the celular
        restCelularMockMvc.perform(get("/api/celulars/{id}", celular.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(celular.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.operadora").value(DEFAULT_OPERADORA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCelular() throws Exception {
        // Get the celular
        restCelularMockMvc.perform(get("/api/celulars/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCelular() throws Exception {
        // Initialize the database
        celularRepository.saveAndFlush(celular);
        int databaseSizeBeforeUpdate = celularRepository.findAll().size();

        // Update the celular
        Celular updatedCelular = celularRepository.findOne(celular.getId());
        updatedCelular
                .numero(UPDATED_NUMERO)
                .operadora(UPDATED_OPERADORA);
        CelularDTO celularDTO = celularMapper.celularToCelularDTO(updatedCelular);

        restCelularMockMvc.perform(put("/api/celulars")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(celularDTO)))
                .andExpect(status().isOk());

        // Validate the Celular in the database
        List<Celular> celulars = celularRepository.findAll();
        assertThat(celulars).hasSize(databaseSizeBeforeUpdate);
        Celular testCelular = celulars.get(celulars.size() - 1);
        assertThat(testCelular.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testCelular.getOperadora()).isEqualTo(UPDATED_OPERADORA);
    }

    @Test
    @Transactional
    public void deleteCelular() throws Exception {
        // Initialize the database
        celularRepository.saveAndFlush(celular);
        int databaseSizeBeforeDelete = celularRepository.findAll().size();

        // Get the celular
        restCelularMockMvc.perform(delete("/api/celulars/{id}", celular.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Celular> celulars = celularRepository.findAll();
        assertThat(celulars).hasSize(databaseSizeBeforeDelete - 1);
    }
}
