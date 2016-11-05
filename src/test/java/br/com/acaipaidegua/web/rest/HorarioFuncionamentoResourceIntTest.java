package br.com.acaipaidegua.web.rest;

import br.com.acaipaidegua.AcaipdejhipsterApp;

import br.com.acaipaidegua.domain.HorarioFuncionamento;
import br.com.acaipaidegua.repository.HorarioFuncionamentoRepository;
import br.com.acaipaidegua.service.dto.HorarioFuncionamentoDTO;
import br.com.acaipaidegua.service.mapper.HorarioFuncionamentoMapper;

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
 * Test class for the HorarioFuncionamentoResource REST controller.
 *
 * @see HorarioFuncionamentoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AcaipdejhipsterApp.class)
public class HorarioFuncionamentoResourceIntTest {

    private static final String DEFAULT_DIA_INICIO = "AAAAA";
    private static final String UPDATED_DIA_INICIO = "BBBBB";

    private static final String DEFAULT_DIA_FIM = "AAAAA";
    private static final String UPDATED_DIA_FIM = "BBBBB";

    private static final String DEFAULT_HORARIO_INICIO = "AAAAA";
    private static final String UPDATED_HORARIO_INICIO = "BBBBB";

    private static final String DEFAULT_HORARIO_FIM = "AAAAA";
    private static final String UPDATED_HORARIO_FIM = "BBBBB";

    private static final Boolean DEFAULT_DELIVERY = false;
    private static final Boolean UPDATED_DELIVERY = true;

    @Inject
    private HorarioFuncionamentoRepository horarioFuncionamentoRepository;

    @Inject
    private HorarioFuncionamentoMapper horarioFuncionamentoMapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restHorarioFuncionamentoMockMvc;

    private HorarioFuncionamento horarioFuncionamento;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HorarioFuncionamentoResource horarioFuncionamentoResource = new HorarioFuncionamentoResource();
        ReflectionTestUtils.setField(horarioFuncionamentoResource, "horarioFuncionamentoRepository", horarioFuncionamentoRepository);
        ReflectionTestUtils.setField(horarioFuncionamentoResource, "horarioFuncionamentoMapper", horarioFuncionamentoMapper);
        this.restHorarioFuncionamentoMockMvc = MockMvcBuilders.standaloneSetup(horarioFuncionamentoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HorarioFuncionamento createEntity(EntityManager em) {
        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento()
                .diaInicio(DEFAULT_DIA_INICIO)
                .diaFim(DEFAULT_DIA_FIM)
                .horarioInicio(DEFAULT_HORARIO_INICIO)
                .horarioFim(DEFAULT_HORARIO_FIM)
                .delivery(DEFAULT_DELIVERY);
        return horarioFuncionamento;
    }

    @Before
    public void initTest() {
        horarioFuncionamento = createEntity(em);
    }

    @Test
    @Transactional
    public void createHorarioFuncionamento() throws Exception {
        int databaseSizeBeforeCreate = horarioFuncionamentoRepository.findAll().size();

        // Create the HorarioFuncionamento
        HorarioFuncionamentoDTO horarioFuncionamentoDTO = horarioFuncionamentoMapper.horarioFuncionamentoToHorarioFuncionamentoDTO(horarioFuncionamento);

        restHorarioFuncionamentoMockMvc.perform(post("/api/horario-funcionamentos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(horarioFuncionamentoDTO)))
                .andExpect(status().isCreated());

        // Validate the HorarioFuncionamento in the database
        List<HorarioFuncionamento> horarioFuncionamentos = horarioFuncionamentoRepository.findAll();
        assertThat(horarioFuncionamentos).hasSize(databaseSizeBeforeCreate + 1);
        HorarioFuncionamento testHorarioFuncionamento = horarioFuncionamentos.get(horarioFuncionamentos.size() - 1);
        assertThat(testHorarioFuncionamento.getDiaInicio()).isEqualTo(DEFAULT_DIA_INICIO);
        assertThat(testHorarioFuncionamento.getDiaFim()).isEqualTo(DEFAULT_DIA_FIM);
        assertThat(testHorarioFuncionamento.getHorarioInicio()).isEqualTo(DEFAULT_HORARIO_INICIO);
        assertThat(testHorarioFuncionamento.getHorarioFim()).isEqualTo(DEFAULT_HORARIO_FIM);
        assertThat(testHorarioFuncionamento.isDelivery()).isEqualTo(DEFAULT_DELIVERY);
    }

    @Test
    @Transactional
    public void getAllHorarioFuncionamentos() throws Exception {
        // Initialize the database
        horarioFuncionamentoRepository.saveAndFlush(horarioFuncionamento);

        // Get all the horarioFuncionamentos
        restHorarioFuncionamentoMockMvc.perform(get("/api/horario-funcionamentos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(horarioFuncionamento.getId().intValue())))
                .andExpect(jsonPath("$.[*].diaInicio").value(hasItem(DEFAULT_DIA_INICIO.toString())))
                .andExpect(jsonPath("$.[*].diaFim").value(hasItem(DEFAULT_DIA_FIM.toString())))
                .andExpect(jsonPath("$.[*].horarioInicio").value(hasItem(DEFAULT_HORARIO_INICIO.toString())))
                .andExpect(jsonPath("$.[*].horarioFim").value(hasItem(DEFAULT_HORARIO_FIM.toString())))
                .andExpect(jsonPath("$.[*].delivery").value(hasItem(DEFAULT_DELIVERY.booleanValue())));
    }

    @Test
    @Transactional
    public void getHorarioFuncionamento() throws Exception {
        // Initialize the database
        horarioFuncionamentoRepository.saveAndFlush(horarioFuncionamento);

        // Get the horarioFuncionamento
        restHorarioFuncionamentoMockMvc.perform(get("/api/horario-funcionamentos/{id}", horarioFuncionamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(horarioFuncionamento.getId().intValue()))
            .andExpect(jsonPath("$.diaInicio").value(DEFAULT_DIA_INICIO.toString()))
            .andExpect(jsonPath("$.diaFim").value(DEFAULT_DIA_FIM.toString()))
            .andExpect(jsonPath("$.horarioInicio").value(DEFAULT_HORARIO_INICIO.toString()))
            .andExpect(jsonPath("$.horarioFim").value(DEFAULT_HORARIO_FIM.toString()))
            .andExpect(jsonPath("$.delivery").value(DEFAULT_DELIVERY.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingHorarioFuncionamento() throws Exception {
        // Get the horarioFuncionamento
        restHorarioFuncionamentoMockMvc.perform(get("/api/horario-funcionamentos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHorarioFuncionamento() throws Exception {
        // Initialize the database
        horarioFuncionamentoRepository.saveAndFlush(horarioFuncionamento);
        int databaseSizeBeforeUpdate = horarioFuncionamentoRepository.findAll().size();

        // Update the horarioFuncionamento
        HorarioFuncionamento updatedHorarioFuncionamento = horarioFuncionamentoRepository.findOne(horarioFuncionamento.getId());
        updatedHorarioFuncionamento
                .diaInicio(UPDATED_DIA_INICIO)
                .diaFim(UPDATED_DIA_FIM)
                .horarioInicio(UPDATED_HORARIO_INICIO)
                .horarioFim(UPDATED_HORARIO_FIM)
                .delivery(UPDATED_DELIVERY);
        HorarioFuncionamentoDTO horarioFuncionamentoDTO = horarioFuncionamentoMapper.horarioFuncionamentoToHorarioFuncionamentoDTO(updatedHorarioFuncionamento);

        restHorarioFuncionamentoMockMvc.perform(put("/api/horario-funcionamentos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(horarioFuncionamentoDTO)))
                .andExpect(status().isOk());

        // Validate the HorarioFuncionamento in the database
        List<HorarioFuncionamento> horarioFuncionamentos = horarioFuncionamentoRepository.findAll();
        assertThat(horarioFuncionamentos).hasSize(databaseSizeBeforeUpdate);
        HorarioFuncionamento testHorarioFuncionamento = horarioFuncionamentos.get(horarioFuncionamentos.size() - 1);
        assertThat(testHorarioFuncionamento.getDiaInicio()).isEqualTo(UPDATED_DIA_INICIO);
        assertThat(testHorarioFuncionamento.getDiaFim()).isEqualTo(UPDATED_DIA_FIM);
        assertThat(testHorarioFuncionamento.getHorarioInicio()).isEqualTo(UPDATED_HORARIO_INICIO);
        assertThat(testHorarioFuncionamento.getHorarioFim()).isEqualTo(UPDATED_HORARIO_FIM);
        assertThat(testHorarioFuncionamento.isDelivery()).isEqualTo(UPDATED_DELIVERY);
    }

    @Test
    @Transactional
    public void deleteHorarioFuncionamento() throws Exception {
        // Initialize the database
        horarioFuncionamentoRepository.saveAndFlush(horarioFuncionamento);
        int databaseSizeBeforeDelete = horarioFuncionamentoRepository.findAll().size();

        // Get the horarioFuncionamento
        restHorarioFuncionamentoMockMvc.perform(delete("/api/horario-funcionamentos/{id}", horarioFuncionamento.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<HorarioFuncionamento> horarioFuncionamentos = horarioFuncionamentoRepository.findAll();
        assertThat(horarioFuncionamentos).hasSize(databaseSizeBeforeDelete - 1);
    }
}
