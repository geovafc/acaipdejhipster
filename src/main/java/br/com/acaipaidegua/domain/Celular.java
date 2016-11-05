package br.com.acaipaidegua.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Celular.
 */
@Entity
@Table(name = "celular")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Celular extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "operadora")
    private String operadora;

    @JoinColumn(name = "estabelecimento", referencedColumnName = "id")
    @ManyToOne
    private Estabelecimento estabelecimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public Celular numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getOperadora() {
        return operadora;
    }

    public Celular operadora(String operadora) {
        this.operadora = operadora;
        return this;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Celular estabelecimento(Estabelecimento estabelecimento) {
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
        Celular celular = (Celular) o;
        if (celular.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, celular.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Celular{"
                + "id=" + id
                + ", numero='" + numero + "'"
                + ", operadora='" + operadora + "'"
                + '}';
    }
}
