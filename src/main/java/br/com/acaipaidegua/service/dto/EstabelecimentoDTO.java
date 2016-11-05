package br.com.acaipaidegua.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * A DTO for the Estabelecimento entity.
 */
public class EstabelecimentoDTO implements Serializable {

    private Long id;

    private String nome;

    private String responsavel;

    private String email;

    private String telefone;

    private String cep;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String uf;

    private String descricao;

    private Double latitude;

    private Double longitude;

    private String uRLimagem;

    private String status;

    private ZonedDateTime dataAtualizacaoPreco;
    
   private List<HorarioFuncionamentoDTO> horarios;


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
    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public String getuRLimagem() {
        return uRLimagem;
    }

    public void setuRLimagem(String uRLimagem) {
        this.uRLimagem = uRLimagem;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public ZonedDateTime getDataAtualizacaoPreco() {
        return dataAtualizacaoPreco;
    }

    public void setDataAtualizacaoPreco(ZonedDateTime dataAtualizacaoPreco) {
        this.dataAtualizacaoPreco = dataAtualizacaoPreco;
    }   

    public List<HorarioFuncionamentoDTO> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioFuncionamentoDTO> horarios) {
        this.horarios = horarios;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EstabelecimentoDTO estabelecimentoDTO = (EstabelecimentoDTO) o;

        if ( ! Objects.equals(id, estabelecimentoDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EstabelecimentoDTO{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", responsavel='" + responsavel + "'" +
            ", email='" + email + "'" +
            ", telefone='" + telefone + "'" +
            ", cep='" + cep + "'" +
            ", logradouro='" + logradouro + "'" +
            ", numero='" + numero + "'" +
            ", bairro='" + bairro + "'" +
            ", cidade='" + cidade + "'" +
            ", uf='" + uf + "'" +
            ", descricao='" + descricao + "'" +
            ", latitude='" + latitude + "'" +
            ", longitude='" + longitude + "'" +
            ", uRLimagem='" + uRLimagem + "'" +
            ", status='" + status + "'" +
            ", dataAtualizacaoPreco='" + dataAtualizacaoPreco + "'" +
            '}';
    }
}
