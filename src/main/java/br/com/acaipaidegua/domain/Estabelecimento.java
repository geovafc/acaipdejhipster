package br.com.acaipaidegua.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Estabelecimento.
 */
@Entity
@Table(name = "estabelecimento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Estabelecimento extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "responsavel")
    private String responsavel;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cep")
    private String cep;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private String numero;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "uf")
    private String uf;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "u_r_limagem")
    private String uRLimagem;

    @Column(name = "status")
    private String status;

    @Column(name = "data_atualizacao_preco")
    private ZonedDateTime dataAtualizacaoPreco;

    @OneToMany(mappedBy = "estabelecimento")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Produto> produtos = new HashSet<>();

    @OneToMany(mappedBy = "estabelecimento")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Celular> celulars = new HashSet<>();

    @OneToMany(mappedBy = "estabelecimento")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HorarioFuncionamento> horariofuncionamentos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Estabelecimento nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public Estabelecimento responsavel(String responsavel) {
        this.responsavel = responsavel;
        return this;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getEmail() {
        return email;
    }

    public Estabelecimento email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Estabelecimento telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public Estabelecimento cep(String cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Estabelecimento logradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public Estabelecimento numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public Estabelecimento bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public Estabelecimento cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public Estabelecimento uf(String uf) {
        this.uf = uf;
        return this;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDescricao() {
        return descricao;
    }

    public Estabelecimento descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Estabelecimento latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Estabelecimento longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getuRLimagem() {
        return uRLimagem;
    }

    public Estabelecimento uRLimagem(String uRLimagem) {
        this.uRLimagem = uRLimagem;
        return this;
    }

    public void setuRLimagem(String uRLimagem) {
        this.uRLimagem = uRLimagem;
    }

    public String getStatus() {
        return status;
    }

    public Estabelecimento status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getDataAtualizacaoPreco() {
        return dataAtualizacaoPreco;
    }

    public Estabelecimento dataAtualizacaoPreco(ZonedDateTime dataAtualizacaoPreco) {
        this.dataAtualizacaoPreco = dataAtualizacaoPreco;
        return this;
    }

    public void setDataAtualizacaoPreco(ZonedDateTime dataAtualizacaoPreco) {
        this.dataAtualizacaoPreco = dataAtualizacaoPreco;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public Estabelecimento produtos(Set<Produto> produtos) {
        this.produtos = produtos;
        return this;
    }

    public Estabelecimento addProduto(Produto produto) {
        produtos.add(produto);
        produto.setEstabelecimento(this);
        return this;
    }

    public Estabelecimento removeProduto(Produto produto) {
        produtos.remove(produto);
        produto.setEstabelecimento(null);
        return this;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public Set<Celular> getCelulars() {
        return celulars;
    }

    public Estabelecimento celulars(Set<Celular> celulars) {
        this.celulars = celulars;
        return this;
    }

    public Estabelecimento addCelular(Celular celular) {
        celulars.add(celular);
        celular.setEstabelecimento(this);
        return this;
    }

    public Estabelecimento removeCelular(Celular celular) {
        celulars.remove(celular);
        celular.setEstabelecimento(null);
        return this;
    }

    public void setCelulars(Set<Celular> celulars) {
        this.celulars = celulars;
    }

    public Set<HorarioFuncionamento> getHorariofuncionamentos() {
        return horariofuncionamentos;
    }

    public Estabelecimento horariofuncionamentos(Set<HorarioFuncionamento> horarioFuncionamentos) {
        this.horariofuncionamentos = horarioFuncionamentos;
        return this;
    }

    public Estabelecimento addHorariofuncionamento(HorarioFuncionamento horarioFuncionamento) {
        horariofuncionamentos.add(horarioFuncionamento);
        horarioFuncionamento.setEstabelecimento(this);
        return this;
    }

    public Estabelecimento removeHorariofuncionamento(HorarioFuncionamento horarioFuncionamento) {
        horariofuncionamentos.remove(horarioFuncionamento);
        horarioFuncionamento.setEstabelecimento(null);
        return this;
    }

    public void setHorariofuncionamentos(Set<HorarioFuncionamento> horarioFuncionamentos) {
        this.horariofuncionamentos = horarioFuncionamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Estabelecimento estabelecimento = (Estabelecimento) o;
        if(estabelecimento.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, estabelecimento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Estabelecimento{" +
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
