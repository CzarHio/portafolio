/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Familia;
import cl.duoc.pft8461.cem.service.FamiliaFacadeREST;
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
@WebService(serviceName = "FamiliaWS")
public class FamiliaWS {

   @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editFamilia")
    @Oneway
    public void editFamilia(
            @WebParam(name = "id_familia") int idFamilia,
            @WebParam(name = "id_usuario") int idUsuario,
            @WebParam(name = "id_centro") int idCentro,
            @WebParam(name = "id_estado") int idEstado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarFamilia");
        spq.setParameter("p_ID_FAMILIA", idFamilia);
        spq.setParameter("p_ID_USUARIO", idUsuario);
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ID_CENTRO", idCentro);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeFamilia")
    @Oneway
    public void removeFamilia(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarFamilia");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_FAMILIA", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findFamilia")
    public Familia findFamilia(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarFamilia");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_FAMILIA", id);
   
        return (Familia)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createFamilia")
    @Oneway
    public void create(
            @WebParam(name = "id_usuario") int idUsuario,
            @WebParam(name = "id_centro") int idCentro,
            @WebParam(name = "id_estado") int idEstado
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarFamilia");
        spq.setParameter("p_ID_USUARIO", idUsuario);
        spq.setParameter("p_ID_ESTADO", idEstado);
        spq.setParameter("p_ID_CENTRO", idCentro);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }


    @WebMethod(operationName = "findAllFamilia")
    public List<Familia> findAll() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarFamilia");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_FAMILIA", 0);
   
        return storedProcedure.getResultList();
    }
    
        @WebMethod(operationName = "findFamiliaPor")
    public List<Familia> findFamiliaPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarFamiliaPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
