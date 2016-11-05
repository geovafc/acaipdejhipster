/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acaipaidegua.domain;

/**
 *
 * @author Jairo
 */
public enum StatusEstabelecimento {
//Em análise = o estabelecimento ainda não decidiu se irá ser parceiro;
    SEMPARCERIA("Sem Parceria"),
    EMANALISE("Em análise"),
    PENDENTE("Pendente"),
    RECEBIDO("Recebido");

    private String descricao;

    StatusEstabelecimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
