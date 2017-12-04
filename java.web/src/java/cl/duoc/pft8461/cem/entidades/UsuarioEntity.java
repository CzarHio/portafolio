/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Usuario;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class UsuarioEntity extends Usuario implements Model, Serializable {

    public UsuarioEntity(Usuario u) {
        this.apellidoMat = u.getApellidoMat();
        this.apellidoPat = u.getApellidoPat();
        this.clave = u.getClave();
        this.creado = u.getCreado();
        this.email = u.getEmail();
        this.idPerfilUsuario = u.getIdPerfilUsuario();
        this.idUsuario = u.getIdUsuario();
        this.nombre = u.getNombre();
        this.token = u.getToken();
        this.usuario = u.getUsuario();
    }
    
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"apellidoMat\" : \"" + this.apellidoMat + "\",";
        json += "\"apellidoPat\" : \"" + this.apellidoPat + "\",";
        if (this.clave != null)
            json += "\"clave\" : \"" + this.clave + "\",";
        else
            json += "\"clave\" : " + this.clave + ",";
        json += "\"creado\" : \"" + this.creado + "\",";
        json += "\"email\" : \"" + this.email + "\",";
        json += "\"idPerfilUsuario\" : " + this.idPerfilUsuario + ",";
        json += "\"idUsuario\" : " + this.idUsuario + ",";
        json += "\"nombre\" : \"" + this.nombre + "\",";
        json += "\"token\" : \"" + this.token + "\",";
        json += "\"usuario\" : \"" + this.usuario + "\"";
        json += "}}";
        
        return json;
    }

    
}
