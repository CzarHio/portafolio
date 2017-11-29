/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

/**
 *
 * @author Czar
 */
@Entity
@Table(name = "DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d")
    , @NamedQuery(name = "Documento.findByIdDocumento", query = "SELECT d FROM Documento d WHERE d.idDocumento = :idDocumento")
    , @NamedQuery(name = "Documento.findByTitulo", query = "SELECT d FROM Documento d WHERE d.titulo = :titulo")
    , @NamedQuery(name = "Documento.findByDescripcion", query = "SELECT d FROM Documento d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "Documento.findByRuta", query = "SELECT d FROM Documento d WHERE d.ruta = :ruta")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarDocumento", 
    procedureName = "PK_DOCUMENTO.SELECCIONAR", 
    resultClasses = { Documento.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_DOCUMENTO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "insertarDocumento", 
    procedureName = "PK_DOCUMENTO.INSERTAR", 
    resultClasses = { Documento.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_FAMILIA", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_RUTA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_TIPO_DOCUMENTO", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_TITULO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_DESCRIPCION", 
          type = String.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarDocumento", 
    procedureName = "PK_DOCUMENTO.ACTUALIZAR", 
    resultClasses = { Documento.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_ESTADO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_FAMILIA", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_RUTA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_TIPO_DOCUMENTO", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_TITULO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_DESCRIPCION", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_DOCUMENTO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "borrarDocumento", 
    procedureName = "PK_DOCUMENTO.BORRAR", 
    resultClasses = { Documento.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_DOCUMENTO", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DOCUMENTO")
    private BigDecimal idDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "RUTA")
    private String ruta;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne(optional = false)
    private EstadoDocumento idEstado;
    @JoinColumn(name = "ID_FAMILIA", referencedColumnName = "ID_FAMILIA")
    @ManyToOne(optional = false)
    private Familia idFamilia;
    @JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO")
    @ManyToOne(optional = false)
    private TipoDocumento idTipoDocumento;

    public Documento() {
    }

    public Documento(BigDecimal idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Documento(BigDecimal idDocumento, String titulo, String descripcion, String ruta) {
        this.idDocumento = idDocumento;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ruta = ruta;
    }

    public BigDecimal getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(BigDecimal idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public EstadoDocumento getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoDocumento idEstado) {
        this.idEstado = idEstado;
    }

    public Familia getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Familia idFamilia) {
        this.idFamilia = idFamilia;
    }

    public TipoDocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TipoDocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Documento[ idDocumento=" + idDocumento + " ]";
    }
    
}
