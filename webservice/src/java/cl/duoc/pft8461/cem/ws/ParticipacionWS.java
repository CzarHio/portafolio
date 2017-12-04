/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Participacion;
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
@WebService(serviceName = "ParticipacionWS")
public class ParticipacionWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editParticipacion")
    @Oneway
    public void editParticipacion(
            @WebParam(name = "id_particiapcion") int idParticipacion,
            @WebParam(name = "id_centro") int idCentro,
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "id_programa") int idPrograma
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarParticipacion");
        spq.setParameter("p_ID_PARTICIPACION", idParticipacion);
        spq.setParameter("p_ID_CENTRO", idCentro);
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ID_PROGRAMA", idPrograma);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeParticipacion")
    @Oneway
    public void removeParticipacion(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarParticipacion");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_PARTICIPACION", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findParticipacion")
    public Participacion findParticipacion(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarParticipacion");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_PARTICIPACION", id);
   
        return (Participacion)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createParticipacion")
    @Oneway
    public void createParticipacion(
            @WebParam(name = "id_centro") int idCentro,
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "id_programa") int idPrograma
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarParticipacion");
        spq.setParameter("p_ID_CENTRO", idCentro);
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ID_PROGRAMA", idPrograma);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllParticipacion")
    public List<Participacion> findAllParticipacion() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarParticipacion");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_PARTICIPACION", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findParticipacionPor")
    public List<Participacion> findParticipacionPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarParticipacionPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
