/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acaipaidegua.service.converter;

import br.com.acaipaidegua.domain.Celular;
import br.com.acaipaidegua.domain.Estabelecimento;
import br.com.acaipaidegua.domain.Produto;
import br.com.acaipaidegua.domain.StatusEstabelecimento;
import br.com.acaipaidegua.service.dto.EstabelecimentoDTONew;
import br.com.acaipaidegua.util.RemoveAcentosUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author geovane
 */
public class EstabelecimentoConverter {

    private static final Logger log = LoggerFactory.getLogger(EstabelecimentoConverter.class);

    private static final EstabelecimentoConverter uniqueInstance = new EstabelecimentoConverter();

//    @Inject
//    private static  HorarioFuncionamentoRepository horarioRepository;

    private static String intervalosFuncionamento;
    private static String intervalosDelivery;
    private static boolean delivery;

    private EstabelecimentoConverter() {

    }

    public static EstabelecimentoConverter getInstance() {
        return uniqueInstance;
    }

    public static EstabelecimentoDTONew toEstabelecimentoDTONew(Estabelecimento estabelecimento) {
        if (estabelecimento != null) {

            EstabelecimentoDTONew resposta = new EstabelecimentoDTONew();

            intervalosFuncionamento = "";
            intervalosDelivery = "";

            resposta.setId(estabelecimento.getId());
            resposta.setLatitude(estabelecimento.getLatitude());
            resposta.setLongitude(estabelecimento.getLongitude());
            resposta.setNome(estabelecimento.getNome());
            resposta.setLogradouro(estabelecimento.getLogradouro());
            resposta.setBairro(estabelecimento.getBairro());
            resposta.setDescricao(estabelecimento.getDescricao());
            resposta.setuRLimagem(estabelecimento.getuRLimagem());
            resposta.setNumero(estabelecimento.getNumero());
            if (estabelecimento.getStatus().isEmpty()) {
                resposta.setStatus(StatusEstabelecimento.SEMPARCERIA);
            }

            if (estabelecimento.getDataAtualizacaoPreco() != null) {
                resposta.setDataUltimaAtualizacaoPreco(estabelecimento.getDataAtualizacaoPreco().toInstant().toEpochMilli());
            }

            transformarIntervaloToString(estabelecimento);
            if (delivery == true) {
                resposta.setDelivery(true);
            }
            resposta.setIntervaloFuncionamento(intervalosFuncionamento);
            resposta.setIntervaloDelivery(intervalosDelivery);

            if (!estabelecimento.getProdutos().isEmpty()) {
                estabelecimento.getProdutos().stream().forEach((Produto produto) -> {
                    switch (RemoveAcentosUtil.removerAcentos(produto.getNome()).toLowerCase()) {
                        case "acai popular":
                            resposta.setPrecoPopular(produto.getPreco());
                            break;
                        case "acai medio":
                            resposta.setPrecoMedio(produto.getPreco());
                            break;
                        case "acai grosso":
                            resposta.setPrecoGrosso(produto.getPreco());
                            break;
                    }

                });
            }

            resposta.setTelefoneResidencial(estabelecimento.getTelefone());
            if (!estabelecimento.getCelulars().isEmpty()) {
                estabelecimento.getCelulars().stream().forEach((Celular celular) -> {
                    switch (celular.getOperadora()) {
                        case "TIM":
                            resposta.setTelefoneTim(celular.getNumero());
                            break;
                        case "CLARO":
                            resposta.setTelefoneClaro(celular.getNumero());
                            break;
                        case "VIVO":
                            resposta.setTelefoneVivo(celular.getNumero());
                            break;
                        case "OI":
                            resposta.setTelefoneOi(celular.getNumero());
                            break;
                    }
                });
            }
            return resposta;

        } else {
            return null;
        }
    }


    public static void transformarIntervaloToString(Estabelecimento estabelecimento) {   

        try{
            
        if (!estabelecimento.getHorariofuncionamentos().isEmpty()) {
                                log.debug("encontrou ");

            estabelecimento.getHorariofuncionamentos().stream().forEach((horario) -> {
                if (horario.isDelivery() == false) {
                    if (horario.getDiaInicio().equals(horario.getDiaFim()) || horario.getDiaFim().equals("")) {
                        intervalosFuncionamento += horario.getDiaInicio() + ": " + horario.getHorarioInicio() + " - " + horario.getHorarioFim() + ";";

                    } else {
                        intervalosFuncionamento += horario.getDiaInicio() + " - " + horario.getDiaFim() + ": " + horario.getHorarioInicio() + " - " + horario.getHorarioFim() + ";";

                    }

                } else {

                    if (horario.getDiaInicio().equals(horario.getDiaFim()) || horario.getDiaFim().equals("")) {

                        intervalosDelivery += horario.getDiaInicio() + ": " + horario.getHorarioInicio() + " - " + horario.getHorarioFim() + ";";
                    } else {
                        intervalosDelivery += horario.getDiaInicio() + " - " + horario.getDiaFim() + ": " + horario.getHorarioInicio() + " - " + horario.getHorarioFim() + ";";
                    }

                    delivery = true;

                }
            });
        } else {
                                log.debug("Horários não encontrados ");

        }
        } catch (Exception e){
                    log.debug("Erro ", e.getCause());
                    log.debug("Erro ", e.getLocalizedMessage());
                    log.debug("Erro ", e.getMessage());

        }
    }
}
