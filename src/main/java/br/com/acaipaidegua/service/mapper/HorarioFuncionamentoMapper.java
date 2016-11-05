package br.com.acaipaidegua.service.mapper;

import br.com.acaipaidegua.domain.*;
import br.com.acaipaidegua.service.dto.HorarioFuncionamentoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity HorarioFuncionamento and its DTO
 * HorarioFuncionamentoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HorarioFuncionamentoMapper {

    @Mapping(source = "estabelecimento.id", target = "estabelecimentoId")
    HorarioFuncionamentoDTO horarioFuncionamentoToHorarioFuncionamentoDTO(HorarioFuncionamento horarioFuncionamento);

    List<HorarioFuncionamentoDTO> horarioFuncionamentosToHorarioFuncionamentoDTOs(List<HorarioFuncionamento> horarioFuncionamentos);

    @Mapping(source = "estabelecimentoId", target = "estabelecimento")
    HorarioFuncionamento horarioFuncionamentoDTOToHorarioFuncionamento(HorarioFuncionamentoDTO horarioFuncionamentoDTO);

    List<HorarioFuncionamento> horarioFuncionamentoDTOsToHorarioFuncionamentos(List<HorarioFuncionamentoDTO> horarioFuncionamentoDTOs);

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
