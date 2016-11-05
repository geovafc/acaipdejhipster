package br.com.acaipaidegua.repository;

import br.com.acaipaidegua.domain.Estabelecimento;
import br.com.acaipaidegua.domain.HorarioFuncionamento;

import org.springframework.data.jpa.repository.*;

import java.util.Set;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the HorarioFuncionamento entity.
 */
@SuppressWarnings("unused")
public interface HorarioFuncionamentoRepository extends JpaRepository<HorarioFuncionamento,Long> {
        @Query("SELECT f FROM HorarioFuncionamento f WHERE f.estabelecimento =:estabelecimento")
    public Set<HorarioFuncionamento> obterHorariosFuncionamentoEstabelecimento(@Param("estabelecimento") Estabelecimento estabelecimento);
    
}
