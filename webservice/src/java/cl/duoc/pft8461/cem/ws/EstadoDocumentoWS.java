/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.EstadoDocumento;
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
@WebService(serviceName = "EstadoDocumentoWS")
public class EstadoDocumentoWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editEstadoDocumento")
    @Oneway
    public void editEstadoDocumento(
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "estado") String estado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarEstadoDocumento");
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ESTADO", estado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeEstadoDocumento")
    @Oneway
    public void removeEstadoDocumento(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarEstadoDocumento");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_ESTADO", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findEstadoDocumento")
    public EstadoDocumento findEstadoDocumento(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoDocumento");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", id);
   
        return (EstadoDocumento)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createEstadoDocumento")
    @Oneway
    public void createEstadoDocumento(
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "estado") String estado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarEstadoDocumento");
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ESTADO", estado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllEstadoDocumento")
    public List<EstadoDocumento> findAllEstadoDocumento() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoDocumento");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findEstadoDocumentoPor")
    public List<EstadoDocumento> findEstadoDocumentoPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoDocumentoPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
