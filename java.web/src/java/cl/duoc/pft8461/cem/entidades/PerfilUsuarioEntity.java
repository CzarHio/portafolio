/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.PerfilUsuario;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class PerfilUsuarioEntity extends PerfilUsuario implements Model, Serializable  {
    
    
    public PerfilUsuarioEntity(PerfilUsuario p) {
        this.idPerfilUsuario = p.getIdPerfilUsuario();
        this.nombrePerfil = p.getNombrePerfil();
    }
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"nombrePerfil\" : \"" + this.nombrePerfil + "\",";
        json += "\"idPerfilUsuario\" : " + this.idPerfilUsuario + "";
        json += "}}";
        
        return json;
    }
}
