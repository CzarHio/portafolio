/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Nota;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class NotaEntity extends Nota implements Model, Serializable {

    private String nombreAlumno;
    private int idAlumno;

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    public NotaEntity() {
    }

    public NotaEntity(Nota n) {
        this.idNota = n.getIdNota();
        this.idCurso = n.getIdCurso();
        this.idPostulacion = n.getIdPostulacion();
        this.nota = n.getNota();
    }
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"idNota\" : " + this.idNota + ",";
        json += "\"idCurso\" : " + this.idCurso + ",";
        json += "\"idPostulacion\" : " + this.idPostulacion + ",";
        json += "\"idAlumno\" : " + this.idAlumno + ",";
        json += "\"nombreAlumno\" : \"" + this.nombreAlumno + "\",";
        json += "\"nota\" : " + this.nota + "";
        json += "}}";
        
        return json;
    }
    
}
