/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Region;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class RegionEntity extends Region implements Model, Serializable  {
    
    public RegionEntity(Region r) {
        this.idRegion = r.getIdRegion();
        this.nombreRegion = r.getNombreRegion();
        this.idPais = r.getIdPais();
    }
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"nombreRegion\" : \"" + this.nombreRegion + "\",";
        json += "\"idRegion\" : " + this.idRegion + ",";
        json += "\"idPais\" : " + this.idPais + "";
        json += "}}";
        
        return json;
    }
    
}
