package br.com.acaipaidegua.service.mapper;

import br.com.acaipaidegua.domain.*;
import br.com.acaipaidegua.service.dto.EstabelecimentoDTONew;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Estabelecimento and its DTO EstabelecimentoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstabelecimentoMapperNew {

    EstabelecimentoDTONew estabelecimentoToEstabelecimentoDTO(Estabelecimento estabelecimento);

    List<EstabelecimentoDTONew> estabelecimentosToEstabelecimentoDTOs(List<Estabelecimento> estabelecimentos);


    Estabelecimento estabelecimentoDTOToEstabelecimento(EstabelecimentoDTONew estabelecimentoDTO);

    List<Estabelecimento> estabelecimentoDTOsToEstabelecimentos(List<EstabelecimentoDTONew> estabelecimentoDTOs);
}
