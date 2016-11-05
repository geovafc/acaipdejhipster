package br.com.acaipaidegua.repository;

import br.com.acaipaidegua.domain.Estabelecimento;
import br.com.acaipaidegua.domain.Produto;
import java.util.Set;

import org.springframework.data.jpa.repository.*;

import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Produto entity.
 */
@SuppressWarnings("unused")
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    @Query("SELECT p FROM Produto p WHERE p.estabelecimento =:estabelecimento")
    public Set<Produto> obterProdutosEstabelecimento(@Param("estabelecimento") Estabelecimento estabelecimento);
    
}
