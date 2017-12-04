/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Documento;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Czar
 */
@WebService(serviceName = "DocumentoWS")
public class DocumentoWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editDocumento")
    @Oneway
    public void editDocumento(
            @WebParam(name = "id_documento") int idDocumento,
            @WebParam(name = "ruta") String ruta,
            @WebParam(name = "id_familia") int idFamilia,
            @WebParam(name = "titulo") String titulo,
            @WebParam(name = "id_tipo_documento") int idTipoDocumento,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "id_estado") int idEstado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarDocumento");
        spq.setParameter("p_ID_DOCUMENTO", idDocumento);
        spq.setParameter("p_RUTA", ruta);
        spq.setParameter("p_ID_FAMILIA", idFamilia);
        spq.setParameter("p_TITULO", titulo);
        spq.setParameter("p_ID_TIPO_DOCUMENTO", idTipoDocumento);
        spq.setParameter("p_DESCRIPCION", descripcion);
        spq.setParameter("p_ID_ESTADO", idEstado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeDocumento")
    @Oneway
    public void removeDocumento(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarDocumento");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_DOCUMENTO", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findDocumento")
    public Documento findDocumento(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarDocumento");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_DOCUMENTO", id);
   
        return (Documento)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createDocumento")
    @Oneway
    public void createDocumento(
            @WebParam(name = "ruta") String ruta,
            @WebParam(name = "id_familia") int idFamilia,
            @WebParam(name = "titulo") String titulo,
            @WebParam(name = "id_tipo_documento") int idTipoDocumento,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "id_estado") int idEstado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarDocumento");
        spq.setParameter("p_RUTA", ruta);
        spq.setParameter("p_ID_FAMILIA", idFamilia);
        spq.setParameter("p_TITULO", titulo);
        spq.setParameter("p_ID_TIPO_DOCUMENTO", idTipoDocumento);
        spq.setParameter("p_DESCRIPCION", descripcion);
        spq.setParameter("p_ID_ESTADO", idEstado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllDocumento")
    public List<Documento> findAllDocumento() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarDocumento");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_DOCUMENTO", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findDocumentoPor")
    public List<Documento> findDocumentoPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarDocumentoPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
