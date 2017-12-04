/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Postulacion;
import cl.duoc.pft8461.cem.service.PostulacionFacadeREST;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author Czar
 */
@WebService(serviceName = "PostulacionWS")
public class PostulacionWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;


    @WebMethod(operationName = "editPostulacion")
    @Oneway
    public void editPostulacion(
            @WebParam(name = "id_postulacion") int idPostulacion,
            @WebParam(name = "id_usuario") int idUsuario,
            @WebParam(name = "id_familia") int idFamilia,
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "id_participacion") int idParticipacion
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarParticipacion");
        spq.setParameter("p_ID_POSTULACION", idPostulacion);
        spq.setParameter("p_ID_USUARIOO", idUsuario);
        spq.setParameter("p_ID_FAMILIA", idFamilia);
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ID_PARTICIPACION", idParticipacion);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removePostulacion")
    @Oneway
    public void removePostulacion(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarParticipacion");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_POSTULACION", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findPostulacion")
    public Postulacion findPostulacion(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarPostulacion");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_POSTULACION", id);
   
        return (Postulacion)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createPostulacion")
    @Oneway
    public void createPostulacion(
            @WebParam(name = "id_usuario") int idUsuario,
            @WebParam(name = "id_familia") int idFamilia,
            @WebParam(name = "id_estado") int idEstado,
            @WebParam(name = "id_participacion") int idParticipacion
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarPostulacion");
        spq.setParameter("p_ID_USUARIOO", idUsuario);
        spq.setParameter("p_ID_FAMILIA", idFamilia);
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ID_PARTICIPACION", idParticipacion);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllPostulacion")
    public List<Postulacion> findAllPostulacion() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarPostulacion");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_POSTULACION", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findPostulacionPor")
    public List<Postulacion> findPostulacionPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarPostulacionPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
