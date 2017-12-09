/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Menu;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class MenuEntity extends Menu implements Model, Serializable {
    
    
    public MenuEntity(Menu m) {
        this.idMenu = m.getIdMenu();
        this.orden = m.getOrden();
        this.perfilUsuario = m.getPerfilUsuario();
        this.titulo = m.getTitulo();
    }
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"orden\" : " + this.orden + ",";
        json += "\"perfilUsuario\" : " + this.perfilUsuario + ",";
        json += "\"titulo\" : \"" + this.titulo + "\",";
        json += "\"idMenu\" : " + this.idMenu + "";
        json += "}}";
        
        return json;
    }
}