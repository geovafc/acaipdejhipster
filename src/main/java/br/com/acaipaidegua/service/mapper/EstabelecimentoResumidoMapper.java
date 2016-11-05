/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acaipaidegua.service.mapper;

import br.com.acaipaidegua.domain.Estabelecimento;
import br.com.acaipaidegua.service.dto.EstabelecimentoResumidoDTO;
import java.util.List;
import org.mapstruct.Mapper;

/**
 *
 * @author geovane
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstabelecimentoResumidoMapper {

    EstabelecimentoResumidoDTO estabelecimentoToEstabelecimentoResumidoDTO(Estabelecimento estabelecimento);

    List<EstabelecimentoResumidoDTO> estabelecimentosToEstabelecimentoDTOs(List<Estabelecimento> estabelecimentos);

    //@Mapping(source = "bairro", target = "bairro")
    Estabelecimento estabelecimentoDTOToEstabelecimento(EstabelecimentoResumidoDTO estabelecimentoResumidoDTO);

    List<Estabelecimento> estabelecimentoDTOsToEstabelecimentos(List<EstabelecimentoResumidoDTO> estabelecimentoDTOs);

}
