package br.com.acaipaidegua.service;

import br.com.acaipaidegua.domain.Estabelecimento;
import br.com.acaipaidegua.repository.CelularRepository;
import br.com.acaipaidegua.repository.EstabelecimentoRepository;
import br.com.acaipaidegua.repository.HorarioFuncionamentoRepository;
import br.com.acaipaidegua.repository.ProdutoRepository;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstabelecimentoService {

    @Inject
    private EstabelecimentoRepository estabelecimentoRepository;
    @Inject
    private HorarioFuncionamentoRepository horarioRepository;
    @Inject
    private ProdutoRepository produtoRepository;
    @Inject
    private CelularRepository celularRepository;


    private final Logger log = LoggerFactory.getLogger(EstabelecimentoService.class);

    public Estabelecimento obterEstabelecimentoPorId(Long id) {
        try {
            
            log.debug("aqui1");
           Estabelecimento estabelecimento = estabelecimentoRepository.getOne(id);
            log.debug("aqui2");
            estabelecimento.setHorariofuncionamentos(horarioRepository.obterHorariosFuncionamentoEstabelecimento(estabelecimento));
            log.debug("aqui3");
            estabelecimento.setProdutos(produtoRepository.obterProdutosEstabelecimento(estabelecimento));
            log.debug("aqui4");
            estabelecimento.setCelulars(celularRepository.obterCelularesEstabelecimento(estabelecimento));
            log.debug("aqui5");

            return estabelecimento;
        } catch (Exception e) {
            log.debug("error service" + e.getMessage());
            return null;
        }

    }

}
