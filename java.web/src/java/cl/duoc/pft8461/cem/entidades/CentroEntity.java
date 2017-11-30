/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Centro;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class CentroEntity extends Centro implements Model, Serializable  {
    
    
    public CentroEntity(Centro c) {
        this.idCentro = c.getIdCentro();
        this.nombreCentro = c.getNombreCentro();
        this.idCiudad = c.getIdCiudad();
        this.idUsuario = c.getIdUsuario();
    }
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"nombreCentro\" : \"" + this.nombreCentro + "\",";
        json += "\"idCiudad\" : " + this.idCiudad + ",";
        json += "\"idUsuario\" : " + this.idUsuario + ",";
        json += "\"idCentro\" : " + this.idCentro + "";
        json += "}}";
        
        return json;
    }
    
}
