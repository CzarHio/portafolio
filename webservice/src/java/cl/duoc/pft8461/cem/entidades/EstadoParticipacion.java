/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Czar
 */
@Entity
@Table(name = "ESTADO_PARTICIPACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoParticipacion.findAll", query = "SELECT e FROM EstadoParticipacion e")
    , @NamedQuery(name = "EstadoParticipacion.findByIdEstado", query = "SELECT e FROM EstadoParticipacion e WHERE e.idEstado = :idEstado")
    , @NamedQuery(name = "EstadoParticipacion.findByEstado", query = "SELECT e FROM EstadoParticipacion e WHERE e.estado = :estado")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarEstadoParticipacion", 
    procedureName = "PK_ESTADO_PARTICIPACION.SELECCIONAR", 
    resultClasses = { EstadoParticipacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
     @NamedStoredProcedureQuery(
    name = "seleccionarEstadoParticipacionPor", 
    procedureName = "PK_ESTADO_PARTICIPACION.SELECCIONAPOR", 
    resultClasses = { EstadoParticipacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "ve_campo", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "ve_valor", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "insertarEstadoParticipacion", 
    procedureName = "PK_ESTADO_PARTICIPACION.INSERTAR", 
    resultClasses = { EstadoParticipacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ESTADO", 
          type = String.class, 
          mode = ParameterMode.IN)
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarEstadoParticipacion", 
    procedureName = "PK_ESTADO_PARTICIPACION.ACTUALIZAR", 
    resultClasses = { EstadoParticipacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ESTADO", 
          type = String.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "borrarEstadoParticipacion", 
    procedureName = "PK_ESTADO_PARTICIPACION.BORRAR", 
    resultClasses = { EstadoParticipacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class EstadoParticipacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTADO")
    private BigDecimal idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ESTADO")
    private String estado;

    private Collection<Participacion> participacionCollection;

    public EstadoParticipacion() {
    }

    public EstadoParticipacion(BigDecimal idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoParticipacion(BigDecimal idEstado, String estado) {
        this.idEstado = idEstado;
        this.estado = estado;
    }

    public BigDecimal getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(BigDecimal idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Participacion> getParticipacionCollection() {
        return participacionCollection;
    }

    public void setParticipacionCollection(Collection<Participacion> participacionCollection) {
        this.participacionCollection = participacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoParticipacion)) {
            return false;
        }
        EstadoParticipacion other = (EstadoParticipacion) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadoParticipacion[ idEstado=" + idEstado + " ]";
    }
    
}
