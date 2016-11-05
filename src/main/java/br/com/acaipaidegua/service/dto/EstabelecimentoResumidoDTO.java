/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acaipaidegua.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author geovane
 */
public class EstabelecimentoResumidoDTO implements Serializable {

    private Long id;

    private String nome;

    private String logradouro;
    
    private String bairro;

    private String latitude;

    private String longitude;

    private String urlImagem;

    private Boolean delivery;

  

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


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

   

 
    
    

    @Override
    public int hashCode() {
        return Objects.hashCode(id);

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstabelecimentoResumidoDTO other = (EstabelecimentoResumidoDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.logradouro, other.logradouro)) {
            return false;
        }

        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.urlImagem, other.urlImagem)) {
            return false;
        }
        if (!Objects.equals(this.delivery, other.delivery)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstabelecimentoResumidoDTO{" + "id=" + id + ", nome=" + nome + ", logradouro=" + logradouro +", latitude=" + latitude + ", longitude=" + longitude + ", urlImagem=" + urlImagem + ", delivery=" + delivery + '}';
    }

    
}
