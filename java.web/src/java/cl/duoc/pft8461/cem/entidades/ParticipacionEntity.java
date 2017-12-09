/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Participacion;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class ParticipacionEntity extends Participacion implements Model, Serializable {

    public ParticipacionEntity(Participacion p) {
        this.idParticipacion = p.getIdParticipacion();
        this.estado = p.getEstado();
        this.fecha = p.getFecha();
        this.idEstado = p.getIdEstado();
        this.idCentro = p.getIdCentro();
        this.idPrograma = p.getIdPrograma();
        this.idTipoPrograma = p.getIdTipoPrograma();
        this.nombreCentro = p.getEstado();
        this.maxcupos = p.getMaxcupos();
        this.mincupos = p.getMincupos();
        this.postulacionesSeleccionadas = p.getPostulacionesSeleccionadas();
        this.nombrePrograma = p.getNombrePrograma();
        this.nombreRevisor = p.getNombreRevisor();
        this.revisor = p.getRevisor();
        this.tipoPrograma = p.getTipoPrograma();
    }

    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"idParticipacion\" : \"" + this.idParticipacion + "\",";
        json += "\"estado\" : \"" + this.estado + "\",";
        json += "\"fecha\" : " + this.fecha + ",";
        json += "\"idEstado\" : " + this.idEstado + ",";
        json += "\"idPrograma\" : " + this.idPrograma + ",";
        json += "\"idTipoPrograma\" : \"" + this.idTipoPrograma + "\",";
        json += "\"nombreCentro\" : \"" + this.nombreCentro + "\",";
        json += "\"maxcupos\" : " + this.maxcupos + ",";
        json += "\"mincupos\" : " + this.mincupos + ",";
        json += "\"postulacionesSeleccionadas\" : " + this.postulacionesSeleccionadas + "";
        json += "\"nombrePrograma\" : \"" + this.nombrePrograma + "\",";
        json += "\"nombreRevisor\" : " + this.nombreRevisor + ",";
        json += "\"revisor\" : " + this.revisor + ",";
        json += "\"tipoPrograma\" : " + this.tipoPrograma + "";
        json += "}}";

        return json;
    }
}
