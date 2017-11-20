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
@Table(name = "PAIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")
    , @NamedQuery(name = "Pais.findByIdPais", query = "SELECT p FROM Pais p WHERE p.idPais = :idPais")
    , @NamedQuery(name = "Pais.findByNombrePais", query = "SELECT p FROM Pais p WHERE p.nombrePais = :nombrePais")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarPais", 
    procedureName = "PK_PAIS.SELECCIONAR", 
    resultClasses = { Pais.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_PAIS", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "seleccionarPaisPor", 
    procedureName = "PK_PAIS.SELECCIONAPOR", 
    resultClasses = { Pais.class }, 
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
    name = "insertarPais", 
    procedureName = "PK_PAIS.INSERTAR", 
    resultClasses = { Pais.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_NOMBRE_PAIS", 
          type = String.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarPais", 
    procedureName = "PK_PAIS.ACTUALIZAR", 
    resultClasses = { Pais.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_NOMBRE_PAIS", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_PAIS", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "borrarPais", 
    procedureName = "PK_PAIS.BORRAR", 
    resultClasses = { Pais.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_PAIS", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PAIS")
    private BigDecimal idPais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "NOMBRE_PAIS")
    private String nombrePais;
    
    private Collection<Programa> programaCollection;
    
    private Collection<Region> regionCollection;

    public Pais() {
    }

    public Pais(BigDecimal idPais) {
        this.idPais = idPais;
    }

    public Pais(BigDecimal idPais, String nombrePais) {
        this.idPais = idPais;
        this.nombrePais = nombrePais;
    }

    public BigDecimal getIdPais() {
        return idPais;
    }

    public void setIdPais(BigDecimal idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @XmlTransient
    public Collection<Programa> getProgramaCollection() {
        return programaCollection;
    }

    public void setProgramaCollection(Collection<Programa> programaCollection) {
        this.programaCollection = programaCollection;
    }

    @XmlTransient
    public Collection<Region> getRegionCollection() {
        return regionCollection;
    }

    public void setRegionCollection(Collection<Region> regionCollection) {
        this.regionCollection = regionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.idPais == null && other.idPais != null) || (this.idPais != null && !this.idPais.equals(other.idPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pais[ idPais=" + idPais + " ]";
    }
    
}
