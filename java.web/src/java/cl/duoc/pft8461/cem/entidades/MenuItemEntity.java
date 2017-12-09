/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.MenuItem;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class MenuItemEntity extends MenuItem implements Model, Serializable  {
    
    
    public MenuItemEntity(MenuItem m) {
        this.idMenuItem = m.getIdMenuItem();
        this.idMenu = m.getIdMenu();
        this.orden = m.getOrden();
        this.padre = m.getPadre();
        this.titulo = m.getTitulo();
        this.url = m.getUrl();
        this.icono = m.getIcono();
    }
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"idMenu\" : " + this.idMenu + ",";
        json += "\"orden\" : " + this.orden + ",";
        json += "\"padre\" : " + this.padre + ",";
        json += "\"titulo\" : \"" + this.titulo + "\",";
        json += "\"url\" : \"" + this.url + "\",";
        json += "\"icono\" : \"" + (this.icono!=null?this.icono:"") + "\",";
        json += "\"idMenuItem\" : " + this.idMenuItem + "";
        json += "}}";
        
        return json;
    }
}