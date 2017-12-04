/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.EstadoPostulacion;
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
@WebService(serviceName = "EstadoPostulacionWS")
public class EstadoPostulacionWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editEstadoPostulacion")
    @Oneway
    public void editEstadoPostulacion(
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "estado") String estado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarEstadoPostulacion");
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ESTADO", estado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeEstadoPostulacion")
    @Oneway
    public void removeEstadoPostulacion(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarEstadoParticipacion");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_ESTADO", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findEstadoPostulacion")
    public EstadoPostulacion findEstadoPostulacion(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoPostulacion");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", id);
   
        return (EstadoPostulacion)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createEstadoPostulacion")
    @Oneway
    public void createEstadoPostulacion(
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "estado") String estado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarEstadoPostulacion");
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ESTADO", estado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllEstadoPostulacion")
    public List<EstadoPostulacion> findAllEstadoPostulacion() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoPostulacion");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findEstadoPostulacionPor")
    public List<EstadoPostulacion> findEstadoPostulacionPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoPostulacionPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
