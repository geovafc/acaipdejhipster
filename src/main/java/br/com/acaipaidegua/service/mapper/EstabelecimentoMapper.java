package br.com.acaipaidegua.service.mapper;

import br.com.acaipaidegua.domain.*;
import br.com.acaipaidegua.service.dto.EstabelecimentoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Estabelecimento and its DTO EstabelecimentoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstabelecimentoMapper {

    EstabelecimentoDTO estabelecimentoToEstabelecimentoDTO(Estabelecimento estabelecimento);

    List<EstabelecimentoDTO> estabelecimentosToEstabelecimentoDTOs(List<Estabelecimento> estabelecimentos);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "produtos", ignore = true)
    @Mapping(target = "celulars", ignore = true)
    @Mapping(target = "horariofuncionamentos")
    Estabelecimento estabelecimentoDTOToEstabelecimento(EstabelecimentoDTO estabelecimentoDTO);

    List<Estabelecimento> estabelecimentoDTOsToEstabelecimentos(List<EstabelecimentoDTO> estabelecimentoDTOs);
}
