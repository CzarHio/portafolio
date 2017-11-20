/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "NOTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nota.findAll", query = "SELECT n FROM Nota n")
    , @NamedQuery(name = "Nota.findByIdNota", query = "SELECT n FROM Nota n WHERE n.idNota = :idNota")
    , @NamedQuery(name = "Nota.findByNota", query = "SELECT n FROM Nota n WHERE n.nota = :nota")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarNota", 
    procedureName = "PK_NOTA.SELECCIONAR", 
    resultClasses = { Nota.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_NOTA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "seleccionarNotaPor", 
    procedureName = "PK_NOTA.SELECCIONAPOR", 
    resultClasses = { Nota.class }, 
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
    name = "insertarNota", 
    procedureName = "PK_NOTA.INSERTAR", 
    resultClasses = { Nota.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_POSTULACION", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_CURSO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_NOTA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarNota", 
    procedureName = "PK_NOTA.ACTUALIZAR", 
    resultClasses = { Nota.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_POSTULACION", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_CURSO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_NOTA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_NOTA", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "borrarNota", 
    procedureName = "PK_NOTA.BORRAR", 
    resultClasses = { Nota.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_NOTA", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class Nota implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_NOTA")
    private BigDecimal idNota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTA")
    private BigInteger nota;
    @Column(name = "ID_CURSO")
    private int idCurso;
    @Column(name = "ID_POSTULACION")
    private int idPostulacion;

    public Nota() {
    }

    public Nota(BigDecimal idNota) {
        this.idNota = idNota;
    }

    public Nota(BigDecimal idNota, BigInteger nota) {
        this.idNota = idNota;
        this.nota = nota;
    }

    public BigDecimal getIdNota() {
        return idNota;
    }

    public void setIdNota(BigDecimal idNota) {
        this.idNota = idNota;
    }

    public BigInteger getNota() {
        return nota;
    }

    public void setNota(BigInteger nota) {
        this.nota = nota;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(int idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNota != null ? idNota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nota)) {
            return false;
        }
        Nota other = (Nota) object;
        if ((this.idNota == null && other.idNota != null) || (this.idNota != null && !this.idNota.equals(other.idNota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Nota[ idNota=" + idNota + " ]";
    }
    
}
