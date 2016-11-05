package br.com.acaipaidegua.repository;

import br.com.acaipaidegua.domain.Estabelecimento;
import org.springframework.data.jpa.repository.*;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data JPA repository for the Estabelecimento entity.
 */
@SuppressWarnings("unused")
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento,Long> {

            @Transactional(readOnly = true)
            @Query("SELECT e FROM Estabelecimento e where e.id =:id")
    public Estabelecimento obterEstabelecimentoId(@Param("id") Long id );
    
    

}
