/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.EstadoFamilia;
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
@WebService(serviceName = "EstadoFamiliaWS")
public class EstadoFamiliaWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editEstadoFamilia")
    @Oneway
    public void editEstadoFamilia(
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "estado") String estado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarEstadoFamilia");
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ESTADO", estado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeEstadoFamilia")
    @Oneway
    public void removeEstadoFamilia(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarEstadoFamilia");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_ESTADO", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findEstadoFamilia")
    public EstadoFamilia findEstadoFamilia(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoFamilia");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", id);
   
        return (EstadoFamilia)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createEstadoFamilia")
    @Oneway
    public void createEstadoFamilia(
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "estado") String estado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarEstadoFamilia");
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ESTADO", estado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllEstadoFamilia")
    public List<EstadoFamilia> findAllEstadoFamilia() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoFamilia");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findEstadoFamiliaPor")
    public List<EstadoFamilia> findEstadoFamiliaPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoFamiliaPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
