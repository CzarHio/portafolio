/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Pais;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class PaisEntity extends Pais implements Model, Serializable  {
    
    
    public PaisEntity(Pais p) {
        this.idPais = p.getIdPais();
        this.nombrePais = p.getNombrePais();
    }
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"nombrePais\" : \"" + this.nombrePais + "\",";
        json += "\"idPais\" : " + this.idPais + "";
        json += "}}";
        
        return json;
    }
}