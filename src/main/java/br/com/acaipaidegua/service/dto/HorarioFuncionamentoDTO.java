package br.com.acaipaidegua.service.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the HorarioFuncionamento entity.
 */
public class HorarioFuncionamentoDTO implements Serializable {

    private Long id;

    private String diaInicio;

    private String diaFim;

    private String horarioInicio;

    private String horarioFim;

    private Boolean delivery;


    private Long estabelecimentoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(String diaInicio) {
        this.diaInicio = diaInicio;
    }
    public String getDiaFim() {
        return diaFim;
    }

    public void setDiaFim(String diaFim) {
        this.diaFim = diaFim;
    }
    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }
    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }
    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public Long getEstabelecimentoId() {
        return estabelecimentoId;
    }

    public void setEstabelecimentoId(Long estabelecimentoId) {
        this.estabelecimentoId = estabelecimentoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HorarioFuncionamentoDTO horarioFuncionamentoDTO = (HorarioFuncionamentoDTO) o;

        if ( ! Objects.equals(id, horarioFuncionamentoDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "HorarioFuncionamentoDTO{" +
            "id=" + id +
            ", diaInicio='" + diaInicio + "'" +
            ", diaFim='" + diaFim + "'" +
            ", horarioInicio='" + horarioInicio + "'" +
            ", horarioFim='" + horarioFim + "'" +
            ", delivery='" + delivery + "'" +
            '}';
    }
}
