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
@Table(name = "ESTADO_PROGRAMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoPrograma.findAll", query = "SELECT e FROM EstadoPrograma e")
    , @NamedQuery(name = "EstadoPrograma.findByIdEstado", query = "SELECT e FROM EstadoPrograma e WHERE e.idEstado = :idEstado")
    , @NamedQuery(name = "EstadoPrograma.findByEstado", query = "SELECT e FROM EstadoPrograma e WHERE e.estado = :estado")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarEstadoPrograma", 
    procedureName = "PK_ESTADO_PROGRAMA.SELECCIONAR", 
    resultClasses = { EstadoPrograma.class }, 
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
    name = "seleccionarEstadoProgramaPor", 
    procedureName = "PK_ESTADO_PROGRAMA.SELECCIONAPOR", 
    resultClasses = { EstadoPrograma.class }, 
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
    name = "insertarEstadoPrograma", 
    procedureName = "PK_ESTADO_PROGRAMA.INSERTAR", 
    resultClasses = { EstadoPrograma.class }, 
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
    name = "actualizarEstadoPrograma", 
    procedureName = "PK_ESTADO_PROGRAMA.ACTUALIZAR", 
    resultClasses = { EstadoPrograma.class }, 
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
    name = "borrarEstadoPrograma", 
    procedureName = "PK_ESTADO_PROGRAMA.BORRAR", 
    resultClasses = { EstadoPrograma.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class EstadoPrograma implements Serializable {

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
    
    private Collection<Programa> programaCollection;

    public EstadoPrograma() {
    }

    public EstadoPrograma(BigDecimal idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoPrograma(BigDecimal idEstado, String estado) {
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
    public Collection<Programa> getProgramaCollection() {
        return programaCollection;
    }

    public void setProgramaCollection(Collection<Programa> programaCollection) {
        this.programaCollection = programaCollection;
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
        if (!(object instanceof EstadoPrograma)) {
            return false;
        }
        EstadoPrograma other = (EstadoPrograma) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadoPrograma[ idEstado=" + idEstado + " ]";
    }
    
}
