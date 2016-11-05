package br.com.acaipaidegua.service.mapper;

import br.com.acaipaidegua.domain.*;
import br.com.acaipaidegua.service.dto.ProdutoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Produto and its DTO ProdutoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProdutoMapper {

    @Mapping(source = "estabelecimento.id", target = "estabelecimentoId")
    ProdutoDTO produtoToProdutoDTO(Produto produto);

    List<ProdutoDTO> produtosToProdutoDTOs(List<Produto> produtos);

    @Mapping(source = "estabelecimentoId", target = "estabelecimento")
    Produto produtoDTOToProduto(ProdutoDTO produtoDTO);

    List<Produto> produtoDTOsToProdutos(List<ProdutoDTO> produtoDTOs);

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
