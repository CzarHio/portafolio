/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Czar
 */
@Entity
@Table(name = "POSTULACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Postulacion.findAll", query = "SELECT p FROM Postulacion p")
    , @NamedQuery(name = "Postulacion.findByIdPostulacion", query = "SELECT p FROM Postulacion p WHERE p.idPostulacion = :idPostulacion")
    , @NamedQuery(name = "Postulacion.findByFechaCreacion", query = "SELECT p FROM Postulacion p WHERE p.fechaCreacion = :fechaCreacion")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarPostulacion", 
    procedureName = "PK_POSTULACION.SELECCIONAR", 
    resultClasses = { Postulacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_POSTULACION", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "seleccionarPostulacionPor", 
    procedureName = "PK_POSTULACION.SELECCIONAPOR", 
    resultClasses = { Postulacion.class }, 
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
    name = "insertarPostulacion", 
    procedureName = "PK_POSTULACION.INSERTAR", 
    resultClasses = { Postulacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_FAMILIA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_PARTICIPACION", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarPostulacion", 
    procedureName = "PK_POSTULACION.ACTUALIZAR", 
    resultClasses = { Postulacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_FAMILIA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_POSTULACION", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_PARTICIPACION", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "borrarPostulacion", 
    procedureName = "PK_POSTULACION.BORRAR", 
    resultClasses = { Postulacion.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_POSTULACION", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class Postulacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_POSTULACION")
    private BigDecimal idPostulacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "ID_ESTADO")
    private int idEstado;
    @Column(name = "ID_FAMILIA")
    private int idFamilia;
    @Column(name = "ID_PARTICIPACION")
    private int idParticipacion;
    @Column(name = "ID_USUARIO")
    private int idUsuario;

    private Collection<Nota> notaCollection;

    public Postulacion() {
    }

    public Postulacion(BigDecimal idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Postulacion(BigDecimal idPostulacion, Date fechaCreacion) {
        this.idPostulacion = idPostulacion;
        this.fechaCreacion = fechaCreacion;
    }

    public BigDecimal getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(BigDecimal idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    public int getIdParticipacion() {
        return idParticipacion;
    }

    public void setIdParticipacion(int idParticipacion) {
        this.idParticipacion = idParticipacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<Nota> getNotaCollection() {
        return notaCollection;
    }

    public void setNotaCollection(Collection<Nota> notaCollection) {
        this.notaCollection = notaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPostulacion != null ? idPostulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postulacion)) {
            return false;
        }
        Postulacion other = (Postulacion) object;
        if ((this.idPostulacion == null && other.idPostulacion != null) || (this.idPostulacion != null && !this.idPostulacion.equals(other.idPostulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Postulacion[ idPostulacion=" + idPostulacion + " ]";
    }
    
}
