/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Nota;
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
@WebService(serviceName = "NotaWS")
public class NotaWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editNota")
    @Oneway
    public void editNota(
            @WebParam(name = "id_nota") int idNota,
            @WebParam(name = "id_postulacion") int idPostulacion,
            @WebParam(name = "id_curso") int idCurso,
            @WebParam(name = "nota") int nota
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarNota");
        spq.setParameter("p_ID_NOTA", idNota);
        spq.setParameter("p_ID_POSTULACION", idPostulacion);
        spq.setParameter("p_ID_CURSO", idCurso);
        spq.setParameter("p_NOTA", nota);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeNota")
    @Oneway
    public void removeNota(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarNota");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_NOTA", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findNota")
    public Nota findNota(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarNota");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_NOTA", id);
   
        return (Nota)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createNota")
    @Oneway
    public void createNota(
            @WebParam(name = "id_postulacion") int idPostulacion,
            @WebParam(name = "id_curso") int idCurso,
            @WebParam(name = "nota") int nota
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarNota");
        spq.setParameter("p_ID_POSTULACION", idPostulacion);
        spq.setParameter("p_ID_CURSO", idCurso);
        spq.setParameter("p_NOTA", nota);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllNota")
    public List<Nota> findAllNota() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarNota");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_NOTA", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findNotaPor")
    public List<Nota> findNotaPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarNotaPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
