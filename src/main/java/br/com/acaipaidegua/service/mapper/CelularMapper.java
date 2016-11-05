package br.com.acaipaidegua.service.mapper;

import br.com.acaipaidegua.domain.*;
import br.com.acaipaidegua.service.dto.CelularDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Celular and its DTO CelularDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CelularMapper {

    @Mapping(source = "estabelecimento.id", target = "estabelecimentoId")
    CelularDTO celularToCelularDTO(Celular celular);

    List<CelularDTO> celularsToCelularDTOs(List<Celular> celulars);

    @Mapping(source = "estabelecimentoId", target = "estabelecimento")
    Celular celularDTOToCelular(CelularDTO celularDTO);

    List<Celular> celularDTOsToCelulars(List<CelularDTO> celularDTOs);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)

    default Estabelecimento estabelecimentoFromId(Long id) {
        if (id == null) {
            return null;
        }
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(id);
        return estabelecimento;
    }
}
