/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Programa;
import java.io.Serializable;

/**
 *
 * @author Joe-Xidu
 */
public class ProgramaEntity extends Programa implements Model, Serializable  {
    
    
    public ProgramaEntity(Programa p) {
        this.idPrograma = p.getIdPrograma();
        this.nombrePrograma = p.getNombrePrograma();
        this.idPais = p.getIdPais();
        this.idEstado = p.getIdEstado();
        this.fechaCreacion = p.getFechaCreacion();
    }
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"nombrePrograma\" : \"" + this.nombrePrograma + "\",";
        json += "\"fechaCreacion\" : \"" + this.fechaCreacion + "\",";
        json += "\"idPais\" : " + this.idPais + ",";
        json += "\"idEstado\" : " + this.idEstado + ",";
        json += "\"idPrograma\" : " + this.idPrograma + "";
        json += "}}";
        
        return json;
    }
}
