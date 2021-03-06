package br.com.acaipaidegua.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.acaipaidegua.domain.Produto;

import br.com.acaipaidegua.repository.ProdutoRepository;
import br.com.acaipaidegua.web.rest.util.HeaderUtil;
import br.com.acaipaidegua.web.rest.util.PaginationUtil;
import br.com.acaipaidegua.service.dto.ProdutoDTO;
import br.com.acaipaidegua.service.mapper.ProdutoMapper;
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
 * REST controller for managing Produto.
 */
@RestController
@RequestMapping("/api")
public class ProdutoResource {

    private final Logger log = LoggerFactory.getLogger(ProdutoResource.class);
        
    @Inject
    private ProdutoRepository produtoRepository;

    @Inject
    private ProdutoMapper produtoMapper;

    /**
     * POST  /produtos : Create a new produto.
     *
     * @param produtoDTO the produtoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new produtoDTO, or with status 400 (Bad Request) if the produto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/produtos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProdutoDTO> createProduto(@RequestBody ProdutoDTO produtoDTO) throws URISyntaxException {
        log.debug("REST request to save Produto : {}", produtoDTO);
        if (produtoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("produto", "idexists", "A new produto cannot already have an ID")).body(null);
        }
        Produto produto = produtoMapper.produtoDTOToProduto(produtoDTO);
        produto = produtoRepository.save(produto);
        ProdutoDTO result = produtoMapper.produtoToProdutoDTO(produto);
        return ResponseEntity.created(new URI("/api/produtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("produto", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /produtos : Updates an existing produto.
     *
     * @param produtoDTO the produtoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated produtoDTO,
     * or with status 400 (Bad Request) if the produtoDTO is not valid,
     * or with status 500 (Internal Server Error) if the produtoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/produtos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProdutoDTO> updateProduto(@RequestBody ProdutoDTO produtoDTO) throws URISyntaxException {
        log.debug("REST request to update Produto : {}", produtoDTO);
        if (produtoDTO.getId() == null) {
            return createProduto(produtoDTO);
        }
        Produto produto = produtoMapper.produtoDTOToProduto(produtoDTO);
        produto = produtoRepository.save(produto);
        ProdutoDTO result = produtoMapper.produtoToProdutoDTO(produto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("produto", produtoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /produtos : get all the produtos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of produtos in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/produtos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ProdutoDTO>> getAllProdutos(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Produtos");
        Page<Produto> page = produtoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/produtos");
        return new ResponseEntity<>(produtoMapper.produtosToProdutoDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /produtos/:id : get the "id" produto.
     *
     * @param id the id of the produtoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the produtoDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/produtos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProdutoDTO> getProduto(@PathVariable Long id) {
        log.debug("REST request to get Produto : {}", id);
        Produto produto = produtoRepository.findOne(id);
        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);
        return Optional.ofNullable(produtoDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /produtos/:id : delete the "id" produto.
     *
     * @param id the id of the produtoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/produtos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        log.debug("REST request to delete Produto : {}", id);
        produtoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("produto", id.toString())).build();
    }

}
