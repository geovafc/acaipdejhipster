package br.com.acaipaidegua.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.acaipaidegua.domain.HorarioFuncionamento;

import br.com.acaipaidegua.repository.HorarioFuncionamentoRepository;
import br.com.acaipaidegua.web.rest.util.HeaderUtil;
import br.com.acaipaidegua.web.rest.util.PaginationUtil;
import br.com.acaipaidegua.service.dto.HorarioFuncionamentoDTO;
import br.com.acaipaidegua.service.mapper.HorarioFuncionamentoMapper;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing HorarioFuncionamento.
 */
@RestController
@RequestMapping("/api")
public class HorarioFuncionamentoResource {

    private final Logger log = LoggerFactory.getLogger(HorarioFuncionamentoResource.class);
        
    @Inject
    private HorarioFuncionamentoRepository horarioFuncionamentoRepository;

    @Inject
    private HorarioFuncionamentoMapper horarioFuncionamentoMapper;

    /**
     * POST  /horario-funcionamentos : Create a new horarioFuncionamento.
     *
     * @param horarioFuncionamentoDTO the horarioFuncionamentoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new horarioFuncionamentoDTO, or with status 400 (Bad Request) if the horarioFuncionamento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/horario-funcionamentos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HorarioFuncionamentoDTO> createHorarioFuncionamento(@RequestBody HorarioFuncionamentoDTO horarioFuncionamentoDTO) throws URISyntaxException {
        log.debug("REST request to save HorarioFuncionamento : {}", horarioFuncionamentoDTO);
        if (horarioFuncionamentoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("horarioFuncionamento", "idexists", "A new horarioFuncionamento cannot already have an ID")).body(null);
        }
        HorarioFuncionamento horarioFuncionamento = horarioFuncionamentoMapper.horarioFuncionamentoDTOToHorarioFuncionamento(horarioFuncionamentoDTO);
        horarioFuncionamento = horarioFuncionamentoRepository.save(horarioFuncionamento);
        HorarioFuncionamentoDTO result = horarioFuncionamentoMapper.horarioFuncionamentoToHorarioFuncionamentoDTO(horarioFuncionamento);
        return ResponseEntity.created(new URI("/api/horario-funcionamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("horarioFuncionamento", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /horario-funcionamentos : Updates an existing horarioFuncionamento.
     *
     * @param horarioFuncionamentoDTO the horarioFuncionamentoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated horarioFuncionamentoDTO,
     * or with status 400 (Bad Request) if the horarioFuncionamentoDTO is not valid,
     * or with status 500 (Internal Server Error) if the horarioFuncionamentoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/horario-funcionamentos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HorarioFuncionamentoDTO> updateHorarioFuncionamento(@RequestBody HorarioFuncionamentoDTO horarioFuncionamentoDTO) throws URISyntaxException {
        log.debug("REST request to update HorarioFuncionamento : {}", horarioFuncionamentoDTO);
        if (horarioFuncionamentoDTO.getId() == null) {
            return createHorarioFuncionamento(horarioFuncionamentoDTO);
        }
        HorarioFuncionamento horarioFuncionamento = horarioFuncionamentoMapper.horarioFuncionamentoDTOToHorarioFuncionamento(horarioFuncionamentoDTO);
        horarioFuncionamento = horarioFuncionamentoRepository.save(horarioFuncionamento);
        HorarioFuncionamentoDTO result = horarioFuncionamentoMapper.horarioFuncionamentoToHorarioFuncionamentoDTO(horarioFuncionamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("horarioFuncionamento", horarioFuncionamentoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /horario-funcionamentos : get all the horarioFuncionamentos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of horarioFuncionamentos in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/horario-funcionamentos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<HorarioFuncionamentoDTO>> getAllHorarioFuncionamentos(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of HorarioFuncionamentos");
        Page<HorarioFuncionamento> page = horarioFuncionamentoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/horario-funcionamentos");
        return new ResponseEntity<>(horarioFuncionamentoMapper.horarioFuncionamentosToHorarioFuncionamentoDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /horario-funcionamentos/:id : get the "id" horarioFuncionamento.
     *
     * @param id the id of the horarioFuncionamentoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the horarioFuncionamentoDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/horario-funcionamentos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HorarioFuncionamentoDTO> getHorarioFuncionamento(@PathVariable Long id) {
        log.debug("REST request to get HorarioFuncionamento : {}", id);
        HorarioFuncionamento horarioFuncionamento = horarioFuncionamentoRepository.findOne(id);
        HorarioFuncionamentoDTO horarioFuncionamentoDTO = horarioFuncionamentoMapper.horarioFuncionamentoToHorarioFuncionamentoDTO(horarioFuncionamento);
        return Optional.ofNullable(horarioFuncionamentoDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /horario-funcionamentos/:id : delete the "id" horarioFuncionamento.
     *
     * @param id the id of the horarioFuncionamentoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/horario-funcionamentos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteHorarioFuncionamento(@PathVariable Long id) {
        log.debug("REST request to delete HorarioFuncionamento : {}", id);
        horarioFuncionamentoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("horarioFuncionamento", id.toString())).build();
    }

}
