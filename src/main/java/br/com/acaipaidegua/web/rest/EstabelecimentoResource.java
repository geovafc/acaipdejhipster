package br.com.acaipaidegua.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.acaipaidegua.domain.Estabelecimento;
import br.com.acaipaidegua.repository.EstabelecimentoRepository;
import br.com.acaipaidegua.service.EstabelecimentoService;
import br.com.acaipaidegua.service.converter.EstabelecimentoConverter;
import br.com.acaipaidegua.web.rest.util.HeaderUtil;
import br.com.acaipaidegua.web.rest.util.PaginationUtil;
import br.com.acaipaidegua.service.dto.EstabelecimentoDTO;
import br.com.acaipaidegua.service.dto.EstabelecimentoDTONew;
import br.com.acaipaidegua.service.dto.EstabelecimentoResumidoDTO;
import br.com.acaipaidegua.service.mapper.EstabelecimentoMapper;
import br.com.acaipaidegua.service.mapper.EstabelecimentoResumidoMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Estabelecimento.
 */
@RestController
@RequestMapping("/api")
public class EstabelecimentoResource {

    private final Logger log = LoggerFactory.getLogger(EstabelecimentoResource.class);


    @Inject
    private EstabelecimentoRepository estabelecimentoRepository;
    @Inject
    private EstabelecimentoService estabelecimentoService;
    @Inject
    private EstabelecimentoMapper estabelecimentoMapper;

    @Inject
    private EstabelecimentoResumidoMapper estabelecimentoResumidoMapper;
    
    Gson gson = new Gson();  

    /**
     * POST /estabelecimentos : Create a new estabelecimento.
     *
     * @param estabelecimentoDTO the estabelecimentoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new estabelecimentoDTO, or with status 400 (Bad Request) if the
     * estabelecimento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/estabelecimentos",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EstabelecimentoDTO> createEstabelecimento(@RequestBody EstabelecimentoDTO estabelecimentoDTO) throws URISyntaxException {
        log.debug("REST request to save Estabelecimento : {}", estabelecimentoDTO);
        if (estabelecimentoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("estabelecimento", "idexists", "A new estabelecimento cannot already have an ID")).body(null);
        }
        Estabelecimento estabelecimento = estabelecimentoMapper.estabelecimentoDTOToEstabelecimento(estabelecimentoDTO);
        estabelecimento = estabelecimentoRepository.save(estabelecimento);
        EstabelecimentoDTO result = estabelecimentoMapper.estabelecimentoToEstabelecimentoDTO(estabelecimento);
        return ResponseEntity.created(new URI("/api/estabelecimentos/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("estabelecimento", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /estabelecimentos : Updates an existing estabelecimento.
     *
     * @param estabelecimentoDTO the estabelecimentoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * estabelecimentoDTO, or with status 400 (Bad Request) if the
     * estabelecimentoDTO is not valid, or with status 500 (Internal Server
     * Error) if the estabelecimentoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/estabelecimentos",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EstabelecimentoDTO> updateEstabelecimento(@RequestBody EstabelecimentoDTO estabelecimentoDTO) throws URISyntaxException {
        log.debug("REST request to update Estabelecimento : {}", estabelecimentoDTO);
        if (estabelecimentoDTO.getId() == null) {
            return createEstabelecimento(estabelecimentoDTO);
        }
        Estabelecimento estabelecimento = estabelecimentoMapper.estabelecimentoDTOToEstabelecimento(estabelecimentoDTO);
        estabelecimento = estabelecimentoRepository.save(estabelecimento);
        EstabelecimentoDTO result = estabelecimentoMapper.estabelecimentoToEstabelecimentoDTO(estabelecimento);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("estabelecimento", estabelecimentoDTO.getId().toString()))
                .body(result);
    }

    /**
     * GET /estabelecimentos : get all the estabelecimentos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of
     * estabelecimentos in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @RequestMapping(value = "/estabelecimentos",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<EstabelecimentoDTO>> getAllEstabelecimentos(Pageable pageable)
            throws URISyntaxException {
        log.debug("REST request to get a page of Estabelecimentos");
        Page<Estabelecimento> page = estabelecimentoRepository.findAll(pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/estabelecimentos");
        return new ResponseEntity<>(estabelecimentoMapper.estabelecimentosToEstabelecimentoDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/estabelecimentosResumido",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<EstabelecimentoResumidoDTO>> getAllEstabelecimentosResumido(Pageable pageable)
            throws URISyntaxException {
        log.debug("REST request to get a page of Estabelecimentos");
        Page<Estabelecimento> page = estabelecimentoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/estabelecimentosResumido");
        return new ResponseEntity<>(estabelecimentoResumidoMapper.estabelecimentosToEstabelecimentoDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET /estabelecimentos/:id : get the "id" estabelecimento.
     *
     * @param id the id of the estabelecimentoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     * estabelecimentoDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/estabelecimentos/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EstabelecimentoDTONew> getEstabelecimento(@PathVariable Long id) {
        log.debug("REST request to get Estabelecimento : {}", id);
        Estabelecimento estabelecimento = estabelecimentoService.obterEstabelecimentoPorId(id);
        log.debug(" Estabelecimento : {}", estabelecimento.getCidade());
        EstabelecimentoDTONew estabelecimentoDTO = EstabelecimentoConverter.toEstabelecimentoDTONew(estabelecimento);
        return Optional.ofNullable(estabelecimentoDTO)
                .map(result -> new ResponseEntity<>(
                                result,
                                HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
//        @RequestMapping(value = "/estabelecimentos/{id}",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
//    public ResponseEntity<EstabelecimentoDTO> getEstabelecimento(@PathVariable Long id) {
//        log.debug("REST request to get Estabelecimento : {}", id);
//        Estabelecimento estabelecimento = estabelecimentoRepository.findOne(id);
//        List<HorarioFuncionamento> horarios = horarioRepository.obterHorariosFuncionamentoEstabelecimento(estabelecimento);
//        EstabelecimentoDTONew estabelecimentoDTO = estabelecimentoMapper.estabelecimentoToEstabelecimentoDTO(estabelecimento);
//        List<HorarioFuncionamentoDTO> horariosDTO = horarioMapper.horarioFuncionamentosToHorarioFuncionamentoDTOs(horarios);
//        estabelecimentoDTO.setHorarios(horariosDTO);
//        return Optional.ofNullable(estabelecimentoDTO)
//                .map(result -> new ResponseEntity<>(
//                                result,
//                                HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    /**
     * DELETE /estabelecimentos/:id : delete the "id" estabelecimento.
     *
     * @param id the id of the estabelecimentoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/estabelecimentos/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEstabelecimento(@PathVariable Long id) {
        log.debug("REST request to delete Estabelecimento : {}", id);
        estabelecimentoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("estabelecimento", id.toString())).build();
    }

}
