/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Czar
 */
@Entity
@Table(name = "PARTICIPACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participacion.findAll", query = "SELECT p FROM Participacion p")
    , @NamedQuery(name = "Participacion.findByIdParticipacion", query = "SELECT p FROM Participacion p WHERE p.idParticipacion = :idParticipacion")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarParticipacion", 
    procedureName = "PK_PARTICIPACION.SELECCIONAR", 
    resultClasses = { Participacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_PARTICIPACION", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "seleccionarParticipacionPor", 
    procedureName = "PK_PARTICIPACION.SELECCIONAPOR", 
    resultClasses = { Participacion.class }, 
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
    name = "insertarParticipacion", 
    procedureName = "PK_PARTICIPACION.INSERTAR", 
    resultClasses = { Participacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_CENTRO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_PROGRAMA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarParticipacion", 
    procedureName = "PK_PARTICIPACION.ACTUALIZAR", 
    resultClasses = { Participacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_CENTRO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_PROGRAMA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_PARTICIPACION", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "borrarParticipacion", 
    procedureName = "PK_PARTICIPACION.BORRAR", 
    resultClasses = { Participacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_PARTICIPACION", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class Participacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARTICIPACION")
    private BigDecimal idParticipacion;
    @Column(name = "ID_CENTRO")
    private int idCentro;
    @Column(name = "ID_ESTADO")
    private int idEstado;
    @Column(name = "ID_PROGRAMA")
    private int idPrograma;
    
    private Postulacion postulacion;

    public Participacion() {
    }

    public Participacion(BigDecimal idParticipacion) {
        this.idParticipacion = idParticipacion;
    }

    public BigDecimal getIdParticipacion() {
        return idParticipacion;
    }

    public void setIdParticipacion(BigDecimal idParticipacion) {
        this.idParticipacion = idParticipacion;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Postulacion getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(Postulacion postulacion) {
        this.postulacion = postulacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParticipacion != null ? idParticipacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participacion)) {
            return false;
        }
        Participacion other = (Participacion) object;
        if ((this.idParticipacion == null && other.idParticipacion != null) || (this.idParticipacion != null && !this.idParticipacion.equals(other.idParticipacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Participacion[ idParticipacion=" + idParticipacion + " ]";
    }
    
}
