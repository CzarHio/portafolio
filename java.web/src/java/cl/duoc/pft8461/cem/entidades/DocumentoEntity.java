/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.entidades;

import cl.duoc.pft8461.cem.ws.Documento;
import java.io.Serializable;

/**
 *
 * @author Joe
 */
public class DocumentoEntity extends Documento implements Model, Serializable {
    
    
    public DocumentoEntity(Documento d) {
        this.idDocumento = d.getIdDocumento();
        this.idEstado = d.getIdEstado();
        this.idFamilia = d.getIdFamilia();
        this.idRevisor = d.getIdRevisor();
        this.idTipoDocumento = d.getIdTipoDocumento();
        this.descripcion = d.getDescripcion();
        this.estado = d.getEstado();
        this.nombreFamilia = d.getNombreFamilia();
        this.revision = d.getRevision();
        this.ruta = d.getRuta();
        this.tipoDocumento = d.getTipoDocumento();
        this.titulo = d.getTitulo();
    }
    
    @Override
    public String toJson() {
        String json = "{\"data\":{";
        json += "\"idDocumento\" : " + this.idDocumento + ",";
        json += "\"idEstado\" : " + this.idEstado + ",";
        json += "\"idFamilia\" : " + this.idFamilia + ",";
        json += "\"idRevisor\" : " + this.idRevisor + ",";
        json += "\"idTipoDocumento\" : " + this.idTipoDocumento + ",";
        json += "\"descripcion\" : \"" + this.descripcion + "\",";
        json += "\"estado\" : \"" + this.estado + "\",";
        json += "\"nombreFamilia\" : \"" + this.nombreFamilia + "\",";
        json += "\"revision\" : \"" + this.revision + "\",";
        json += "\"ruta\" : \"" + this.ruta + "\",";
        json += "\"tipoDocumento\" : \"" + this.tipoDocumento + "\",";
        json += "\"titulo\" : \"" + this.titulo + "\"";
        json += "}}";
        
        return json;
    }
    
}
