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
@Table(name = "MENU_ITEM")
@XmlRootElement
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "seleccionarMenuItem",
            procedureName = "PK_MENU_ITEM.SELECCIONAR",
            resultClasses = {MenuItem.class},
            parameters = {
                @StoredProcedureParameter(
                        name = "p_ID_MENU_ITEM",
                        type = Integer.class,
                        mode = ParameterMode.IN)
                ,
        @StoredProcedureParameter(
                        name = "vs_cursor",
                        type = Integer.class,
                        mode = ParameterMode.REF_CURSOR)})
    ,
    @NamedStoredProcedureQuery(
            name = "seleccionarMenuItemPor",
            procedureName = "PK_MENU_ITEM.SELECCIONAPOR",
            resultClasses = {MenuItem.class},
            parameters = {
                @StoredProcedureParameter(
                        name = "ve_campo",
                        type = String.class,
                        mode = ParameterMode.IN)
                ,
        @StoredProcedureParameter(
                        name = "ve_valor",
                        type = String.class,
                        mode = ParameterMode.IN)
                ,
        @StoredProcedureParameter(
                        name = "vs_cursor",
                        type = Integer.class,
                        mode = ParameterMode.REF_CURSOR)})
    ,
    @NamedStoredProcedureQuery(
            name = "insertarMenuItem",
            procedureName = "PK_MENU_ITEM.INSERTAR",
            resultClasses = {MenuItem.class},
            parameters = {
                @StoredProcedureParameter(
                        name = "p_TITULO",
                        type = String.class,
                        mode = ParameterMode.IN)
                ,
        @StoredProcedureParameter(
                        name = "p_ID_MENU",
                        type = Integer.class,
                        mode = ParameterMode.IN)
                ,
        @StoredProcedureParameter(
                        name = "p_URL",
                        type = String.class,
                        mode = ParameterMode.IN)
                ,
        @StoredProcedureParameter(
                        name = "p_PADRE",
                        type = Integer.class,
                        mode = ParameterMode.IN),})
    ,
    @NamedStoredProcedureQuery(
            name = "actualizarMenuItem",
            procedureName = "PK_MENU_ITEM.ACTUALIZAR",
            resultClasses = {MenuItem.class},
            parameters = {
                @StoredProcedureParameter(
                        name = "p_ID_MENU_ITEM",
                        type = Integer.class,
                        mode = ParameterMode.IN)
                ,
        @StoredProcedureParameter(
                        name = "p_TITULO",
                        type = String.class,
                        mode = ParameterMode.IN)
                ,
        @StoredProcedureParameter(
                        name = "p_ID_MENU",
                        type = Integer.class,
                        mode = ParameterMode.IN)
                ,
        @StoredProcedureParameter(
                        name = "p_URL",
                        type = String.class,
                        mode = ParameterMode.IN)
                ,
        @StoredProcedureParameter(
                        name = "p_PADRE",
                        type = Integer.class,
                        mode = ParameterMode.IN)
                ,
        @StoredProcedureParameter(
                        name = "p_ORDEN",
                        type = Integer.class,
                        mode = ParameterMode.IN),})
    ,
    @NamedStoredProcedureQuery(
            name = "borrarMenuItem",
            procedureName = "PK_MENU_ITEM.BORRAR",
            resultClasses = {MenuItem.class},
            parameters = {
                @StoredProcedureParameter(
                        name = "p_ID_MENU_ITEM",
                        type = Integer.class,
                        mode = ParameterMode.IN)
            })
})
public class MenuItem implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MENU_ITEM")
    private BigDecimal idMenuItem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "URL")
    private String url;
    @Column(name = "ID_MENU")
    private int idMenu;
    @Column(name = "PADRE")
    private int padre;
    @Column(name = "ORDEN")
    private int orden;
    @Column(name = "ICONO")
    private String icono;
    @Column(name = "ESTADO")
    private int estado;

    public MenuItem() {
    }

    public MenuItem(BigDecimal idMenuItem) {
        this.idMenuItem = idMenuItem;
    }

    public MenuItem(BigDecimal idMenuItem, String titulo, String url) {
        this.idMenuItem = idMenuItem;
        this.titulo = titulo;
        this.url = url;
    }

    public BigDecimal getIdMenuItem() {
        return idMenuItem;
    }

    public void setIdMenuItem(BigDecimal idMenuItem) {
        this.idMenuItem = idMenuItem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getPadre() {
        return padre;
    }

    public void setPadre(int padre) {
        this.padre = padre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public int geEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenuItem != null ? idMenuItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuItem)) {
            return false;
        }
        MenuItem other = (MenuItem) object;
        if ((this.idMenuItem == null && other.idMenuItem != null) || (this.idMenuItem != null && !this.idMenuItem.equals(other.idMenuItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MenuItem[ idMenuItem=" + idMenuItem + " ]";
    }

}
