/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.TipoDocumento;
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
@WebService(serviceName = "TipoDocumentoWS")
public class TipoDocumentoWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editTipoDocumento")
    @Oneway
    public void editTipoDocumento(
            @WebParam(name = "id_tipo_documento") int idTipoDocumento,
            @WebParam(name = "tipo_documento") String tipoDocumento
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarTipoDocumento");
        spq.setParameter("p_ID_TIPO_DOCUMENTO", idTipoDocumento);
        spq.setParameter("p_TIPO_DOCUMENTO", tipoDocumento);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeTipoDocumento")
    @Oneway
    public void removeTipoDocumento(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarTipoDocumento");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_TIPO_DOCUMENTO", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findTipoDocumento")
    public TipoDocumento findTipoDocumento(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarTipoDocumento");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_TIPO_DOCUMENTO", id);
   
        return (TipoDocumento)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createTipoDocumento")
    @Oneway
    public void createTipoDocumento(
            @WebParam(name = "id_tipo_documento") int idTipoDocumento,
            @WebParam(name = "tipo_documento") String tipoDocumento
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarTipoDocumento");
        spq.setParameter("p_ID_TIPO_DOCUMENTO", idTipoDocumento);
        spq.setParameter("p_TIPO_DOCUMENTO", tipoDocumento);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllTipoDocumento")
    public List<TipoDocumento> findAllTipoDocumento() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarTipoDocumento");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_TIPO_DOCUMENTO", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findTipoDocumentoPor")
    public List<TipoDocumento> findTipoDocumentoPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarTipoDocumentoPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
