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
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Czar
 */
@Entity
@Table(name = "PROGRAMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p")
    , @NamedQuery(name = "Programa.findByIdPrograma", query = "SELECT p FROM Programa p WHERE p.idPrograma = :idPrograma")
    , @NamedQuery(name = "Programa.findByNombrePrograma", query = "SELECT p FROM Programa p WHERE p.nombrePrograma = :nombrePrograma")
    , @NamedQuery(name = "Programa.findByFechaCreacion", query = "SELECT p FROM Programa p WHERE p.fechaCreacion = :fechaCreacion")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarPrograma", 
    procedureName = "PK_PROGRAMA.SELECCIONAR", 
    resultClasses = { Programa.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_PROGRAMA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "seleccionarProgramaPor", 
    procedureName = "PK_PROGRAMA.SELECCIONAPOR", 
    resultClasses = { Programa.class }, 
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
    name = "insertarPrograma", 
    procedureName = "PK_PROGRAMA.INSERTAR", 
    resultClasses = { Programa.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_NOMBRE_PROGRAMA", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_PAIS", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarPrograma", 
    procedureName = "PK_PROGRAMA.ACTUALIZAR", 
    resultClasses = { Programa.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_PROGRAMA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_NOMBRE_PROGRAMA", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_PAIS", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "borrarPrograma", 
    procedureName = "PK_PROGRAMA.BORRAR", 
    resultClasses = { Programa.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_PROGRAMA", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROGRAMA")
    private BigDecimal idPrograma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOMBRE_PROGRAMA")
    private String nombrePrograma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
  
    private Collection<Curso> cursoCollection;

    private Collection<Participacion> participacionCollection;
    @Column(name = "ID_ESTADO")
    private int idEstado;
    @Column(name = "ID_PAIS")
    private int idPais;

    public Programa() {
    }

    public Programa(BigDecimal idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Programa(BigDecimal idPrograma, String nombrePrograma, Date fechaCreacion) {
        this.idPrograma = idPrograma;
        this.nombrePrograma = nombrePrograma;
        this.fechaCreacion = fechaCreacion;
    }

    public BigDecimal getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(BigDecimal idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @XmlTransient
    public Collection<Curso> getCursoCollection() {
        return cursoCollection;
    }

    public void setCursoCollection(Collection<Curso> cursoCollection) {
        this.cursoCollection = cursoCollection;
    }

    @XmlTransient
    public Collection<Participacion> getParticipacionCollection() {
        return participacionCollection;
    }

    public void setParticipacionCollection(Collection<Participacion> participacionCollection) {
        this.participacionCollection = participacionCollection;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrograma != null ? idPrograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.idPrograma == null && other.idPrograma != null) || (this.idPrograma != null && !this.idPrograma.equals(other.idPrograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Programa[ idPrograma=" + idPrograma + " ]";
    }
    
}
