/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Curso;
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
@WebService(serviceName = "CursoWS")
public class CursoWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editCurso")
    @Oneway
    public void editCurso(
            @WebParam(name = "id_curso") int idCurso,
            @WebParam(name = "id_programa") int idPrograma,
            @WebParam(name = "nombre_curso") String nombreCurso
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarCurso");
        spq.setParameter("p_ID_CURSO", idCurso);
        spq.setParameter("p_ID_PROGRAMA", idPrograma);
        spq.setParameter("p_NOMBRE_CURSO", nombreCurso);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeCurso")
    @Oneway
    public void removeCurso(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarCurso");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_CURSO", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findCurso")
    public Curso findCurso(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarCurso");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_CURSO", id);
   
        return (Curso)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createCurso")
    @Oneway
    public void createCurso(
            @WebParam(name = "id_programa") int idPrograma,
            @WebParam(name = "nombre_curso") String nombreCurso
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarCurso");
        spq.setParameter("p_ID_PROGRAMA", idPrograma);
        spq.setParameter("p_NOMBRE_CURSO", nombreCurso);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllCurso")
    public List<Curso> findAllCurso() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarCurso");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_CURSO", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findCursoPor")
    public List<Curso> findCursoPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarCursoPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
