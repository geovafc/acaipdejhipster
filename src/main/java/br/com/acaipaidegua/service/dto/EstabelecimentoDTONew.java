package br.com.acaipaidegua.service.dto;

import br.com.acaipaidegua.domain.StatusEstabelecimento;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * A DTO for the Estabelecimento entity.
 */
public class EstabelecimentoDTONew implements Serializable {

    private Long id;
    private String nome;
    private String logradouro;
    private String numero;
    private String bairro;
    private double latitude;
    private double longitude;
    private String telefoneTim;
    private String telefoneVivo;
    private String telefoneOi;
    private String telefoneClaro;
    private String telefoneResidencial;
    private String uRLimagem;
    private String descricao;
    private BigDecimal precoPopular;
    private BigDecimal precoMedio;
    private BigDecimal precoGrosso;
    private boolean delivery;
    private Long dataUltimaAtualizacaoPreco;
    private String intervaloFuncionamento;
    private String intervaloDelivery;
    private StatusEstabelecimento Status;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTelefoneTim() {
        return telefoneTim;
    }

    public void setTelefoneTim(String telefoneTim) {
        this.telefoneTim = telefoneTim;
    }

    public String getTelefoneVivo() {
        return telefoneVivo;
    }

    public void setTelefoneVivo(String telefoneVivo) {
        this.telefoneVivo = telefoneVivo;
    }

    public String getTelefoneOi() {
        return telefoneOi;
    }

    public void setTelefoneOi(String telefoneOi) {
        this.telefoneOi = telefoneOi;
    }

    public String getTelefoneClaro() {
        return telefoneClaro;
    }

    public void setTelefoneClaro(String telefoneClaro) {
        this.telefoneClaro = telefoneClaro;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getuRLimagem() {
        return uRLimagem;
    }

    public void setuRLimagem(String uRLimagem) {
        this.uRLimagem = uRLimagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoPopular() {
        return precoPopular;
    }

    public void setPrecoPopular(BigDecimal precoPopular) {
        this.precoPopular = precoPopular;
    }

    public BigDecimal getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(BigDecimal precoMedio) {
        this.precoMedio = precoMedio;
    }

    public BigDecimal getPrecoGrosso() {
        return precoGrosso;
    }

    public void setPrecoGrosso(BigDecimal precoGrosso) {
        this.precoGrosso = precoGrosso;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public Long getDataUltimaAtualizacaoPreco() {
        return dataUltimaAtualizacaoPreco;
    }

    public void setDataUltimaAtualizacaoPreco(Long dataUltimaAtualizacaoPreco) {
        this.dataUltimaAtualizacaoPreco = dataUltimaAtualizacaoPreco;
    }

    public String getIntervaloFuncionamento() {
        return intervaloFuncionamento;
    }

    public void setIntervaloFuncionamento(String intervaloFuncionamento) {
        this.intervaloFuncionamento = intervaloFuncionamento;
    }

    public String getIntervaloDelivery() {
        return intervaloDelivery;
    }

    public void setIntervaloDelivery(String intervaloDelivery) {
        this.intervaloDelivery = intervaloDelivery;
    }

    public StatusEstabelecimento getStatus() {
        return Status;
    }

    public void setStatus(StatusEstabelecimento Status) {
        this.Status = Status;
    }

}
