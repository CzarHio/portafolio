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
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Czar
 */
@Entity
@Table(name = "FAMILIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Familia.findAll", query = "SELECT f FROM Familia f")
    , @NamedQuery(name = "Familia.findByIdFamilia", query = "SELECT f FROM Familia f WHERE f.idFamilia = :idFamilia")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarFamilia", 
    procedureName = "PK_FAMILIA.SELECCIONAR", 
    resultClasses = { Familia.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_FAMILIA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "seleccionarFamiliaPor", 
    procedureName = "PK_FAMILIA.SELECCIONAPOR", 
    resultClasses = { Familia.class }, 
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
    name = "insertarFamilia", 
    procedureName = "PK_FAMILIA.INSERTAR", 
    resultClasses = { Familia.class }, 
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
          name = "p_ID_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarFamilia", 
    procedureName = "PK_FAMILIA.ACTUALIZAR", 
    resultClasses = { Familia.class }, 
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
          name = "p_ID_FAMILIA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "borrarFamilia", 
    procedureName = "PK_FAMILIA.BORRAR", 
    resultClasses = { Familia.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_FAMILIA", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class Familia implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FAMILIA")
    private BigDecimal idFamilia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFamilia")
    private Collection<Documento> documentoCollection;
    
    private Postulacion postulacion;
    
    @Column(name = "ID_CENTRO")
    private int idCentro;
    @Column(name = "ID_ESTADO")
    private int idEstado;
    @Column(name = "ID_USUARIO")
    private int idUsuario;

    public Familia() {
    }

    public Familia(BigDecimal idFamilia) {
        this.idFamilia = idFamilia;
    }

    public BigDecimal getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(BigDecimal idFamilia) {
        this.idFamilia = idFamilia;
    }

    @XmlTransient
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    public Postulacion getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(Postulacion postulacion) {
        this.postulacion = postulacion;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamilia != null ? idFamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Familia)) {
            return false;
        }
        Familia other = (Familia) object;
        if ((this.idFamilia == null && other.idFamilia != null) || (this.idFamilia != null && !this.idFamilia.equals(other.idFamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Familia[ idFamilia=" + idFamilia + " ]";
    }
    
}
