/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Ciudad;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class CiudadEntity extends Ciudad implements Model, Serializable  {
    
    
    public CiudadEntity(Ciudad c) {
        this.idCiudad = c.getIdCiudad();
        this.idRegion = c.getIdRegion();
        this.nombreCiudad = c.getNombreCiudad();
    }
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"nombreCiudad\" : \"" + this.nombreCiudad + "\",";
        json += "\"idRegion\" : " + this.idRegion + ",";
        json += "\"idCiudad\" : " + this.idCiudad + "";
        json += "}}";
        
        return json;
    }
}
