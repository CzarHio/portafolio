/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.EstadoParticipacion;
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
@WebService(serviceName = "EstadoParticipacionWS")
public class EstadoParticipacionWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editEstadoParticipacion")
    @Oneway
    public void editEstadoParticipacion(
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "estado") String estado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarEstadoParticipacion");
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ESTADO", estado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeEstadoParticipacion")
    @Oneway
    public void removeEstadoParticipacion(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarEstadoParticipacion");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_ESTADO", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findEstadoParticipacion")
    public EstadoParticipacion findEstadoParticipacion(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoParticipacion");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", id);
   
        return (EstadoParticipacion)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createEstadoParticipacion")
    @Oneway
    public void createEstadoParticipacion(
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "estado") String estado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarEstadoParticipacion");
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ESTADO", estado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllEstadoParticipacion")
    public List<EstadoParticipacion> findAllEstadoParticipacion() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoParticipacion");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findEstadoParticipacionPor")
    public List<EstadoParticipacion> findEstadoParticipacionPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoParticipacionPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
