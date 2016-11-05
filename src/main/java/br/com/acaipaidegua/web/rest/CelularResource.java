package br.com.acaipaidegua.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.acaipaidegua.domain.Celular;

import br.com.acaipaidegua.repository.CelularRepository;
import br.com.acaipaidegua.web.rest.util.HeaderUtil;
import br.com.acaipaidegua.web.rest.util.PaginationUtil;
import br.com.acaipaidegua.service.dto.CelularDTO;
import br.com.acaipaidegua.service.mapper.CelularMapper;
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
 * REST controller for managing Celular.
 */
@RestController
@RequestMapping("/api")
public class CelularResource {

    private final Logger log = LoggerFactory.getLogger(CelularResource.class);
        
    @Inject
    private CelularRepository celularRepository;

    @Inject
    private CelularMapper celularMapper;

    /**
     * POST  /celulars : Create a new celular.
     *
     * @param celularDTO the celularDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new celularDTO, or with status 400 (Bad Request) if the celular has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/celulars",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CelularDTO> createCelular(@RequestBody CelularDTO celularDTO) throws URISyntaxException {
        log.debug("REST request to save Celular : {}", celularDTO);
        if (celularDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("celular", "idexists", "A new celular cannot already have an ID")).body(null);
        }
        Celular celular = celularMapper.celularDTOToCelular(celularDTO);
        celular = celularRepository.save(celular);
        CelularDTO result = celularMapper.celularToCelularDTO(celular);
        return ResponseEntity.created(new URI("/api/celulars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("celular", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /celulars : Updates an existing celular.
     *
     * @param celularDTO the celularDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated celularDTO,
     * or with status 400 (Bad Request) if the celularDTO is not valid,
     * or with status 500 (Internal Server Error) if the celularDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/celulars",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CelularDTO> updateCelular(@RequestBody CelularDTO celularDTO) throws URISyntaxException {
        log.debug("REST request to update Celular : {}", celularDTO);
        if (celularDTO.getId() == null) {
            return createCelular(celularDTO);
        }
        Celular celular = celularMapper.celularDTOToCelular(celularDTO);
        celular = celularRepository.save(celular);
        CelularDTO result = celularMapper.celularToCelularDTO(celular);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("celular", celularDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /celulars : get all the celulars.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of celulars in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/celulars",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CelularDTO>> getAllCelulars(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Celulars");
        Page<Celular> page = celularRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/celulars");
        return new ResponseEntity<>(celularMapper.celularsToCelularDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /celulars/:id : get the "id" celular.
     *
     * @param id the id of the celularDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the celularDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/celulars/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CelularDTO> getCelular(@PathVariable Long id) {
        log.debug("REST request to get Celular : {}", id);
        Celular celular = celularRepository.findOne(id);
        CelularDTO celularDTO = celularMapper.celularToCelularDTO(celular);
        return Optional.ofNullable(celularDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /celulars/:id : delete the "id" celular.
     *
     * @param id the id of the celularDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/celulars/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCelular(@PathVariable Long id) {
        log.debug("REST request to delete Celular : {}", id);
        celularRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("celular", id.toString())).build();
    }

}
