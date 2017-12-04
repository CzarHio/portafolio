/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.EstadoPrograma;
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
@WebService(serviceName = "EstadoProgramaWS")
public class EstadoProgramaWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editEstadoPrograma")
    @Oneway
    public void editEstadoPrograma(
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "estado") String estado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarEstadoPrograma");
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ESTADO", estado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeEstadoPrograma")
    @Oneway
    public void removeEstadoPrograma(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarEstadoPrograma");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_ESTADO", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findEstadoPrograma")
    public EstadoPrograma findEstadoPrograma(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoPrograma");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", id);
   
        return (EstadoPrograma)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createEstadoPrograma")
    @Oneway
    public void createEstadoPrograma(
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "estado") String estado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarEstadoPrograma");
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ESTADO", estado);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllEstadoPrograma")
    public List<EstadoPrograma> findAllEstadoPrograma() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoPrograma");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findEstadoProgramaPor")
    public List<EstadoPrograma> findEstadoProgramaPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoProgramaPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
