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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
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
@Table(name = "CENTRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Centro.findAll", query = "SELECT c FROM Centro c")
    , @NamedQuery(name = "Centro.findByIdCentro", query = "SELECT c FROM Centro c WHERE c.idCentro = :idCentro")
    , @NamedQuery(name = "Centro.findByNombreCentro", query = "SELECT c FROM Centro c WHERE c.nombreCentro = :nombreCentro")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarCentro", 
    procedureName = "PK_CENTRO.SELECCIONAR", 
    resultClasses = { Centro.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_CENTRO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "seleccionarCentroPor", 
    procedureName = "PK_CENTRO.SELECCIONAPOR", 
    resultClasses = { Centro.class }, 
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
    name = "insertarCentro", 
    procedureName = "PK_CENTRO.INSERTAR", 
    resultClasses = { Centro.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_NOMBRE_CENTRO", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_CIUDAD", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarCentro", 
    procedureName = "PK_CENTRO.ACTUALIZAR", 
    resultClasses = { Centro.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_CENTRO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_NOMBRE_CENTRO", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_CIUDAD", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "borrarCentro", 
    procedureName = "PK_CENTRO.BORRAR", 
    resultClasses = { Centro.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_CENTRO", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class Centro implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CENTRO")
    private BigDecimal idCentro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOMBRE_CENTRO")
    private String nombreCentro;
    @Column(name = "ID_CIUDAD")
    private int idCiudad;
    @Column(name = "ID_USUARIO")
    private int idUsuario;
  
    private Collection<Participacion> participacionCollection;

    private Collection<Familia> familiaCollection;

    public Centro() {
    }

    public Centro(BigDecimal idCentro) {
        this.idCentro = idCentro;
    }

    public Centro(BigDecimal idCentro, String nombreCentro) {
        this.idCentro = idCentro;
        this.nombreCentro = nombreCentro;
    }

    public BigDecimal getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(BigDecimal idCentro) {
        this.idCentro = idCentro;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<Participacion> getParticipacionCollection() {
        return participacionCollection;
    }

    public void setParticipacionCollection(Collection<Participacion> participacionCollection) {
        this.participacionCollection = participacionCollection;
    }

    @XmlTransient
    public Collection<Familia> getFamiliaCollection() {
        return familiaCollection;
    }

    public void setFamiliaCollection(Collection<Familia> familiaCollection) {
        this.familiaCollection = familiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCentro != null ? idCentro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Centro)) {
            return false;
        }
        Centro other = (Centro) object;
        if ((this.idCentro == null && other.idCentro != null) || (this.idCentro != null && !this.idCentro.equals(other.idCentro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Centro[ idCentro=" + idCentro + " ]";
    }
    
}
