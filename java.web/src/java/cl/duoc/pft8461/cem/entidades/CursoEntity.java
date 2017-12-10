/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Curso;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class CursoEntity extends Curso implements Model, Serializable {
    
    
    public CursoEntity(Curso c) {
        this.idCurso = c.getIdCurso();
        this.nombreCurso = c.getNombreCurso();
        this.idPrograma = c.getIdPrograma();
        this.creditos = c.getCreditos();
        this.descripcion = c.getDescripcion();
    }
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"idCurso\" : " + this.idCurso + ",";
        json += "\"nombreCurso\" : \"" + this.nombreCurso + "\",";
        json += "\"creditos\" : " + this.creditos + ",";
        json += "\"descripcion\" : \"" + this.descripcion + "\",";
        json += "\"idPrograma\" : " + this.idPrograma + "";
        json += "}}";
        
        return json;
    }
}
