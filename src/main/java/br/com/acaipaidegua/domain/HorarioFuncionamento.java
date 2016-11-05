package br.com.acaipaidegua.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A HorarioFuncionamento.
 */
@Entity
@Table(name = "horario_funcionamento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HorarioFuncionamento extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dia_inicio")
    private String diaInicio;

    @Column(name = "dia_fim")
    private String diaFim;

    @Column(name = "horario_inicio")
    private String horarioInicio;

    @Column(name = "horario_fim")
    private String horarioFim;

    @Column(name = "delivery")
    private Boolean delivery;

    @JoinColumn(name = "estabelecimento", referencedColumnName = "id")
    @ManyToOne
    private Estabelecimento estabelecimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiaInicio() {
        return diaInicio;
    }

    public HorarioFuncionamento diaInicio(String diaInicio) {
        this.diaInicio = diaInicio;
        return this;
    }

    public void setDiaInicio(String diaInicio) {
        this.diaInicio = diaInicio;
    }

    public String getDiaFim() {
        return diaFim;
    }

    public HorarioFuncionamento diaFim(String diaFim) {
        this.diaFim = diaFim;
        return this;
    }

    public void setDiaFim(String diaFim) {
        this.diaFim = diaFim;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public HorarioFuncionamento horarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
        return this;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public HorarioFuncionamento horarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
        return this;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public Boolean isDelivery() {
        return delivery;
    }

    public HorarioFuncionamento delivery(Boolean delivery) {
        this.delivery = delivery;
        return this;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public HorarioFuncionamento estabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
        return this;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HorarioFuncionamento horarioFuncionamento = (HorarioFuncionamento) o;
        if (horarioFuncionamento.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, horarioFuncionamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "HorarioFuncionamento{"
                + "id=" + id
                + ", diaInicio='" + diaInicio + "'"
                + ", diaFim='" + diaFim + "'"
                + ", horarioInicio='" + horarioInicio + "'"
                + ", horarioFim='" + horarioFim + "'"
                + ", delivery='" + delivery + "'"
                + '}';
    }
}
