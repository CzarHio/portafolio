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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Czar
 */
@Entity
@Table(name = "REGION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r")
    , @NamedQuery(name = "Region.findByIdRegion", query = "SELECT r FROM Region r WHERE r.idRegion = :idRegion")
    , @NamedQuery(name = "Region.findByNombreRegion", query = "SELECT r FROM Region r WHERE r.nombreRegion = :nombreRegion")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarRegion", 
    procedureName = "PK_REGION.SELECCIONAR", 
    resultClasses = { Region.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_REGION", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "seleccionarRegionPor", 
    procedureName = "PK_REGION.SELECCIONAPOR", 
    resultClasses = { Region.class }, 
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
    name = "insertarRegion", 
    procedureName = "PK_REGION.INSERTAR", 
    resultClasses = {Region.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_NOMBRE_REGION", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_PAIS", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarRegion", 
    procedureName = "PK_REGION.ACTUALIZAR", 
    resultClasses = { Region.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_NOMBRE_REGION", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_REGION", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_PAIS", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "borrarRegion", 
    procedureName = "PK_REGION.BORRAR", 
    resultClasses = { Region.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_REGION", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REGION")
    private BigDecimal idRegion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "NOMBRE_REGION")
    private String nombreRegion;
    @Column(name = "ID_PAIS")
    private int idPais;

    private Collection<Ciudad> ciudadCollection;

    public Region() {
    }

    public Region(BigDecimal idRegion) {
        this.idRegion = idRegion;
    }

    public Region(BigDecimal idRegion, String nombreRegion) {
        this.idRegion = idRegion;
        this.nombreRegion = nombreRegion;
    }

    public BigDecimal getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(BigDecimal idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    @XmlTransient
    public Collection<Ciudad> getCiudadCollection() {
        return ciudadCollection;
    }

    public void setCiudadCollection(Collection<Ciudad> ciudadCollection) {
        this.ciudadCollection = ciudadCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegion != null ? idRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.idRegion == null && other.idRegion != null) || (this.idRegion != null && !this.idRegion.equals(other.idRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Region[ idRegion=" + idRegion + " ]";
    }
    
}
