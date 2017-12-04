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
@Table(name = "PERFIL_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilUsuario.findAll", query = "SELECT p FROM PerfilUsuario p")
    , @NamedQuery(name = "PerfilUsuario.findByIdPerfilUsuario", query = "SELECT p FROM PerfilUsuario p WHERE p.idPerfilUsuario = :idPerfilUsuario")
    , @NamedQuery(name = "PerfilUsuario.findByNombrePerfil", query = "SELECT p FROM PerfilUsuario p WHERE p.nombrePerfil = :nombrePerfil")})
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarPerfilUsuario", 
    procedureName = "PK_PERFIL_USUARIO.SELECCIONAR", 
    resultClasses = { PerfilUsuario.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_PERFIL_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "seleccionarPerfilUsuarioPor", 
    procedureName = "PK_PERFIL_USUARIO.SELECCIONAPOR", 
    resultClasses = { PerfilUsuario.class }, 
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
    name = "insertarPerfilUsuario", 
    procedureName = "PK_PERFIL_USUARIO.INSERTAR", 
    resultClasses = { PerfilUsuario.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_PERFIL_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_NOMBRE_PERFIL", 
          type = String.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarPerfilUsuario", 
    procedureName = "PK_PERFIL_USUARIO.ACTUALIZAR", 
    resultClasses = { PerfilUsuario.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_PERFIL_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_NOMBRE_PERFIL", 
          type = String.class, 
          mode = ParameterMode.IN),     
         }),
    @NamedStoredProcedureQuery(
    name = "borrarPerfilUsuario", 
    procedureName = "PK_PERFIL_USUARIO.BORRAR", 
    resultClasses = { PerfilUsuario.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_PERFIL_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class PerfilUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERFIL_USUARIO")
    private BigDecimal idPerfilUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE_PERFIL")
    private String nombrePerfil;

    private Collection<Usuario> usuarioCollection; 
    private Collection<Menu> menuCollection;

    public PerfilUsuario() {
    }

    public PerfilUsuario(BigDecimal idPerfilUsuario) {
        this.idPerfilUsuario = idPerfilUsuario;
    }

    public PerfilUsuario(BigDecimal idPerfilUsuario, String nombrePerfil) {
        this.idPerfilUsuario = idPerfilUsuario;
        this.nombrePerfil = nombrePerfil;
    }

    public BigDecimal getIdPerfilUsuario() {
        return idPerfilUsuario;
    }

    public void setIdPerfilUsuario(BigDecimal idPerfilUsuario) {
        this.idPerfilUsuario = idPerfilUsuario;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfilUsuario != null ? idPerfilUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilUsuario)) {
            return false;
        }
        PerfilUsuario other = (PerfilUsuario) object;
        if ((this.idPerfilUsuario == null && other.idPerfilUsuario != null) || (this.idPerfilUsuario != null && !this.idPerfilUsuario.equals(other.idPerfilUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PerfilUsuario[ idPerfilUsuario=" + idPerfilUsuario + " ]";
    }
    
}
