package br.com.acaipaidegua.web.rest;

import br.com.acaipaidegua.AcaipdejhipsterApp;

import br.com.acaipaidegua.domain.Estabelecimento;
import br.com.acaipaidegua.repository.EstabelecimentoRepository;
import br.com.acaipaidegua.service.dto.EstabelecimentoDTO;
import br.com.acaipaidegua.service.mapper.EstabelecimentoMapper;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EstabelecimentoResource REST controller.
 *
 * @see EstabelecimentoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AcaipdejhipsterApp.class)
public class EstabelecimentoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAA";
    private static final String UPDATED_NOME = "BBBBB";

    private static final String DEFAULT_RESPONSAVEL = "AAAAA";
    private static final String UPDATED_RESPONSAVEL = "BBBBB";

    private static final String DEFAULT_EMAIL = "AAAAA";
    private static final String UPDATED_EMAIL = "BBBBB";

    private static final String DEFAULT_TELEFONE = "AAAAA";
    private static final String UPDATED_TELEFONE = "BBBBB";

    private static final String DEFAULT_CEP = "AAAAA";
    private static final String UPDATED_CEP = "BBBBB";

    private static final String DEFAULT_LOGRADOURO = "AAAAA";
    private static final String UPDATED_LOGRADOURO = "BBBBB";

    private static final String DEFAULT_NUMERO = "AAAAA";
    private static final String UPDATED_NUMERO = "BBBBB";

    private static final String DEFAULT_BAIRRO = "AAAAA";
    private static final String UPDATED_BAIRRO = "BBBBB";

    private static final String DEFAULT_CIDADE = "AAAAA";
    private static final String UPDATED_CIDADE = "BBBBB";

    private static final String DEFAULT_UF = "AAAAA";
    private static final String UPDATED_UF = "BBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBB";

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final String DEFAULT_U_R_LIMAGEM = "AAAAA";
    private static final String UPDATED_U_R_LIMAGEM = "BBBBB";

    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";

    private static final ZonedDateTime DEFAULT_DATA_ATUALIZACAO_PRECO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_DATA_ATUALIZACAO_PRECO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_DATA_ATUALIZACAO_PRECO_STR = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(DEFAULT_DATA_ATUALIZACAO_PRECO);

    @Inject
    private EstabelecimentoRepository estabelecimentoRepository;

    @Inject
    private EstabelecimentoMapper estabelecimentoMapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restEstabelecimentoMockMvc;

    private Estabelecimento estabelecimento;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EstabelecimentoResource estabelecimentoResource = new EstabelecimentoResource();
        ReflectionTestUtils.setField(estabelecimentoResource, "estabelecimentoRepository", estabelecimentoRepository);
        ReflectionTestUtils.setField(estabelecimentoResource, "estabelecimentoMapper", estabelecimentoMapper);
        this.restEstabelecimentoMockMvc = MockMvcBuilders.standaloneSetup(estabelecimentoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Estabelecimento createEntity(EntityManager em) {
        Estabelecimento estabelecimento = new Estabelecimento()
                .nome(DEFAULT_NOME)
                .responsavel(DEFAULT_RESPONSAVEL)
                .email(DEFAULT_EMAIL)
                .telefone(DEFAULT_TELEFONE)
                .cep(DEFAULT_CEP)
                .logradouro(DEFAULT_LOGRADOURO)
                .numero(DEFAULT_NUMERO)
                .bairro(DEFAULT_BAIRRO)
                .cidade(DEFAULT_CIDADE)
                .uf(DEFAULT_UF)
                .descricao(DEFAULT_DESCRICAO)
                .latitude(DEFAULT_LATITUDE)
                .longitude(DEFAULT_LONGITUDE)
                .uRLimagem(DEFAULT_U_R_LIMAGEM)
                .status(DEFAULT_STATUS)
                .dataAtualizacaoPreco(DEFAULT_DATA_ATUALIZACAO_PRECO);
        return estabelecimento;
    }

    @Before
    public void initTest() {
        estabelecimento = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstabelecimento() throws Exception {
        int databaseSizeBeforeCreate = estabelecimentoRepository.findAll().size();

        // Create the Estabelecimento
        EstabelecimentoDTO estabelecimentoDTO = estabelecimentoMapper.estabelecimentoToEstabelecimentoDTO(estabelecimento);

        restEstabelecimentoMockMvc.perform(post("/api/estabelecimentos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(estabelecimentoDTO)))
                .andExpect(status().isCreated());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll();
        assertThat(estabelecimentos).hasSize(databaseSizeBeforeCreate + 1);
        Estabelecimento testEstabelecimento = estabelecimentos.get(estabelecimentos.size() - 1);
        assertThat(testEstabelecimento.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testEstabelecimento.getResponsavel()).isEqualTo(DEFAULT_RESPONSAVEL);
        assertThat(testEstabelecimento.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEstabelecimento.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testEstabelecimento.getCep()).isEqualTo(DEFAULT_CEP);
        assertThat(testEstabelecimento.getLogradouro()).isEqualTo(DEFAULT_LOGRADOURO);
        assertThat(testEstabelecimento.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testEstabelecimento.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testEstabelecimento.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testEstabelecimento.getUf()).isEqualTo(DEFAULT_UF);
        assertThat(testEstabelecimento.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testEstabelecimento.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testEstabelecimento.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testEstabelecimento.getuRLimagem()).isEqualTo(DEFAULT_U_R_LIMAGEM);
        assertThat(testEstabelecimento.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEstabelecimento.getDataAtualizacaoPreco()).isEqualTo(DEFAULT_DATA_ATUALIZACAO_PRECO);
    }

    @Test
    @Transactional
    public void getAllEstabelecimentos() throws Exception {
        // Initialize the database
        estabelecimentoRepository.saveAndFlush(estabelecimento);

        // Get all the estabelecimentos
        restEstabelecimentoMockMvc.perform(get("/api/estabelecimentos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(estabelecimento.getId().intValue())))
                .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
                .andExpect(jsonPath("$.[*].responsavel").value(hasItem(DEFAULT_RESPONSAVEL.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE.toString())))
                .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP.toString())))
                .andExpect(jsonPath("$.[*].logradouro").value(hasItem(DEFAULT_LOGRADOURO.toString())))
                .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
                .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO.toString())))
                .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE.toString())))
                .andExpect(jsonPath("$.[*].uf").value(hasItem(DEFAULT_UF.toString())))
                .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
                .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
                .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
                .andExpect(jsonPath("$.[*].uRLimagem").value(hasItem(DEFAULT_U_R_LIMAGEM.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
                .andExpect(jsonPath("$.[*].dataAtualizacaoPreco").value(hasItem(DEFAULT_DATA_ATUALIZACAO_PRECO_STR)));
    }

    @Test
    @Transactional
    public void getEstabelecimento() throws Exception {
        // Initialize the database
        estabelecimentoRepository.saveAndFlush(estabelecimento);

        // Get the estabelecimento
        restEstabelecimentoMockMvc.perform(get("/api/estabelecimentos/{id}", estabelecimento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estabelecimento.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.responsavel").value(DEFAULT_RESPONSAVEL.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE.toString()))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP.toString()))
            .andExpect(jsonPath("$.logradouro").value(DEFAULT_LOGRADOURO.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO.toString()))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE.toString()))
            .andExpect(jsonPath("$.uf").value(DEFAULT_UF.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.uRLimagem").value(DEFAULT_U_R_LIMAGEM.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.dataAtualizacaoPreco").value(DEFAULT_DATA_ATUALIZACAO_PRECO_STR));
    }

    @Test
    @Transactional
    public void getNonExistingEstabelecimento() throws Exception {
        // Get the estabelecimento
        restEstabelecimentoMockMvc.perform(get("/api/estabelecimentos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstabelecimento() throws Exception {
        // Initialize the database
        estabelecimentoRepository.saveAndFlush(estabelecimento);
        int databaseSizeBeforeUpdate = estabelecimentoRepository.findAll().size();

        // Update the estabelecimento
        Estabelecimento updatedEstabelecimento = estabelecimentoRepository.findOne(estabelecimento.getId());
        updatedEstabelecimento
                .nome(UPDATED_NOME)
                .responsavel(UPDATED_RESPONSAVEL)
                .email(UPDATED_EMAIL)
                .telefone(UPDATED_TELEFONE)
                .cep(UPDATED_CEP)
                .logradouro(UPDATED_LOGRADOURO)
                .numero(UPDATED_NUMERO)
                .bairro(UPDATED_BAIRRO)
                .cidade(UPDATED_CIDADE)
                .uf(UPDATED_UF)
                .descricao(UPDATED_DESCRICAO)
                .latitude(UPDATED_LATITUDE)
                .longitude(UPDATED_LONGITUDE)
                .uRLimagem(UPDATED_U_R_LIMAGEM)
                .status(UPDATED_STATUS)
                .dataAtualizacaoPreco(UPDATED_DATA_ATUALIZACAO_PRECO);
        EstabelecimentoDTO estabelecimentoDTO = estabelecimentoMapper.estabelecimentoToEstabelecimentoDTO(updatedEstabelecimento);

        restEstabelecimentoMockMvc.perform(put("/api/estabelecimentos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(estabelecimentoDTO)))
                .andExpect(status().isOk());

        // Validate the Estabelecimento in the database
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll();
        assertThat(estabelecimentos).hasSize(databaseSizeBeforeUpdate);
        Estabelecimento testEstabelecimento = estabelecimentos.get(estabelecimentos.size() - 1);
        assertThat(testEstabelecimento.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testEstabelecimento.getResponsavel()).isEqualTo(UPDATED_RESPONSAVEL);
        assertThat(testEstabelecimento.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEstabelecimento.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testEstabelecimento.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testEstabelecimento.getLogradouro()).isEqualTo(UPDATED_LOGRADOURO);
        assertThat(testEstabelecimento.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testEstabelecimento.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testEstabelecimento.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testEstabelecimento.getUf()).isEqualTo(UPDATED_UF);
        assertThat(testEstabelecimento.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testEstabelecimento.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testEstabelecimento.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testEstabelecimento.getuRLimagem()).isEqualTo(UPDATED_U_R_LIMAGEM);
        assertThat(testEstabelecimento.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEstabelecimento.getDataAtualizacaoPreco()).isEqualTo(UPDATED_DATA_ATUALIZACAO_PRECO);
    }

    @Test
    @Transactional
    public void deleteEstabelecimento() throws Exception {
        // Initialize the database
        estabelecimentoRepository.saveAndFlush(estabelecimento);
        int databaseSizeBeforeDelete = estabelecimentoRepository.findAll().size();

        // Get the estabelecimento
        restEstabelecimentoMockMvc.perform(delete("/api/estabelecimentos/{id}", estabelecimento.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll();
        assertThat(estabelecimentos).hasSize(databaseSizeBeforeDelete - 1);
    }
}
