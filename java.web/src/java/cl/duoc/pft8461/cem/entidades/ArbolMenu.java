/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;
import cl.duoc.pft8461.cem.ws.Menu;
import cl.duoc.pft8461.cem.ws.MenuItem;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Czar
 */
public class ArbolMenu extends Menu{
    private List<MenuItem> listaMenu;


    public ArbolMenu(Menu m) {
            this.idMenu = m.getIdMenu();
            this.orden = m.getOrden();
            this.perfilUsuario = m.getPerfilUsuario();
            this.titulo = m.getTitulo();
    }
    
    /**
     * @return the listaMenu
     */
    
    public List<MenuItem> getListaMenu() {
        return listaMenu;
    }

    /**
     * @param listaMenu the listaMenu to set
     */
    public void setListaMenu(List<MenuItem> listaMenu) {
        this.listaMenu = listaMenu;
    }
}

