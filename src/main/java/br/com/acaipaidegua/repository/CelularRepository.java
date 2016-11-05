package br.com.acaipaidegua.repository;

import br.com.acaipaidegua.domain.Celular;
import br.com.acaipaidegua.domain.Estabelecimento;
import java.util.Set;

import org.springframework.data.jpa.repository.*;

import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Celular entity.
 */
@SuppressWarnings("unused")
public interface CelularRepository extends JpaRepository<Celular, Long> {

    @Query("SELECT c FROM Celular c WHERE c.estabelecimento =:estabelecimento")
    public Set<Celular> obterCelularesEstabelecimento(@Param("estabelecimento") Estabelecimento estabelecimento);

}
