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
@Table(name = "TIPO_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t")
    , @NamedQuery(name = "TipoDocumento.findByIdTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.idTipoDocumento = :idTipoDocumento")
    , @NamedQuery(name = "TipoDocumento.findByTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.tipoDocumento = :tipoDocumento")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarTipoDocumento", 
    procedureName = "PK_TIPO_DOCUMENTO.SELECCIONAR", 
    resultClasses = { TipoDocumento.class }, 
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
    name = "seleccionarTipoDocumentoPor", 
    procedureName = "PK_TIPO_DOCUMENTO.SELECCIONAPOR", 
    resultClasses = { TipoDocumento.class }, 
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
    name = "insertarTipoDocumento", 
    procedureName = "PK_TIPO_DOCUMENTO.INSERTAR", 
    resultClasses = { TipoDocumento.class }, 
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
    name = "actualizarTipoDocumento", 
    procedureName = "PK_TIPO_DOCUMENTO.ACTUALIZAR", 
    resultClasses = { TipoDocumento.class }, 
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
    name = "borrarTipoDocumento", 
    procedureName = "PK_TIPO_DOCUMENTO.BORRAR", 
    resultClasses = { TipoDocumento.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_DOCUMENTO")
    private BigDecimal idTipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

    private Collection<Documento> documentoCollection;

    public TipoDocumento() {
    }

    public TipoDocumento(BigDecimal idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public TipoDocumento(BigDecimal idTipoDocumento, String tipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
        this.tipoDocumento = tipoDocumento;
    }

    public BigDecimal getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(BigDecimal idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @XmlTransient
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoDocumento[ idTipoDocumento=" + idTipoDocumento + " ]";
    }
    
}
