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
@Table(name = "MENU")
@XmlRootElement
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "seleccionarMenu", 
    procedureName = "PK_MENU.SELECCIONAR", 
    resultClasses = { Menu.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_MENU", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
            name="vs_cursor",
            type = Integer.class,
            mode = ParameterMode.REF_CURSOR) }),
    @NamedStoredProcedureQuery(
    name = "seleccionarMenuPor", 
    procedureName = "PK_MENU.SELECCIONAPOR", 
    resultClasses = { Menu.class }, 
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
    name = "insertarMenu", 
    procedureName = "PK_MENU.INSERTAR", 
    resultClasses = { Menu.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_TITULO", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_PERFIL_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "actualizarMenu", 
    procedureName = "PK_MENU.ACTUALIZAR", 
    resultClasses = { Menu.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_TITULO", 
          type = String.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_PERFIL_USUARIO", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ID_MENU", 
          type = Integer.class, 
          mode = ParameterMode.IN),
        @StoredProcedureParameter(
          name = "p_ORDEN", 
          type = Integer.class, 
          mode = ParameterMode.IN),
         }),
    @NamedStoredProcedureQuery(
    name = "borrarMenu", 
    procedureName = "PK_MENU.BORRAR", 
    resultClasses = { Menu.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "p_ID_MENU", 
          type = Integer.class, 
          mode = ParameterMode.IN)
         })
})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MENU")
    private BigDecimal idMenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "ORDEN")
    private int orden;
 
    private Collection<MenuItem> menuItemCollection;
    @Column(name = "PERFIL_USUARIO")
    private int perfilUsuario;

    public Menu() {
    }

    public Menu(BigDecimal idMenu) {
        this.idMenu = idMenu;
    }

    public Menu(BigDecimal idMenu, String titulo) {
        this.idMenu = idMenu;
        this.titulo = titulo;
    }

    public BigDecimal getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(BigDecimal idMenu) {
        this.idMenu = idMenu;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlTransient
    public Collection<MenuItem> getMenuItemCollection() {
        return menuItemCollection;
    }

    public void setMenuItemCollection(Collection<MenuItem> menuItemCollection) {
        this.menuItemCollection = menuItemCollection;
    }

    public int getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(int perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }
    
     public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Menu[ idMenu=" + idMenu + " ]";
    }
    
}
