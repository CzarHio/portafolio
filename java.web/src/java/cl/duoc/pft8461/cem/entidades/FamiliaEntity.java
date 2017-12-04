/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Familia;
import cl.duoc.pft8461.cem.ws.Usuario;
import java.io.Serializable;

/**
 *
 * @author Joe-Xidu
 */
public class FamiliaEntity extends Familia implements Model, Serializable {

    public FamiliaEntity(Familia f) {
        this.idCentro = f.getIdCentro();
        this.idEstado = f.getIdEstado();
        this.idFamilia = f.getIdFamilia();
        this.idUsuario = f.getIdUsuario();
    }
    
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"idCentro\" : " + this.idCentro + ",";
        json += "\"idEstado\" : " + this.idEstado + ",";
        json += "\"idFamilia\" : " + this.idFamilia + ",";
        json += "\"idUsuario\" : " + this.idUsuario + "";
        json += "}}";
        
        return json;
    }
}
