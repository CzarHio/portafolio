/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Programa;
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
@WebService(serviceName = "ProgramaWS")
public class ProgramaWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editPrograma")
    @Oneway
    public void editPrograma(
            @WebParam(name = "id_programa") int idPrograma,
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "nombre_programa") String nombrePrograma,
            @WebParam(name = "id_pais") String idPais
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarPrograma");
        spq.setParameter("p_ID_PROGRAMA", idPrograma);
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_NOMBRE_PROGRAMA", nombrePrograma);
        spq.setParameter("p_ID_PAIS", idPais);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removePrograma")
    @Oneway
    public void removePrograma(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarPrograma");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_PROGRAMA", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findPrograma")
    public Programa findPrograma(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarPrograma");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_PROGRAMA", id);
   
        return (Programa)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createPrograma")
    @Oneway
    public void createPrograma(
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "nombre_programa") String nombrePrograma,
            @WebParam(name = "id_pais") String idPais
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarPrograma");
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_NOMBRE_PROGRAMA", nombrePrograma);
        spq.setParameter("p_ID_PAIS", idPais);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllPrograma")
    public List<Programa> findAllPrograma() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarPrograma");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_PROGRAMA", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findProgramaPor")
    public List<Programa> findProgramaPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarProgramaPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
