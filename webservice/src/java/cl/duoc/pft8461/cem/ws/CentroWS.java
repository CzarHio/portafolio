/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Centro;
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
@WebService(serviceName = "CentroWS")
public class CentroWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editCentro")
    @Oneway
    public void editCentro(
            @WebParam(name = "id_centro") int idCentro,
            @WebParam(name = "nombre_centro") String nombreCentro,
            @WebParam(name = "id_usuario") int idUsuario,
            @WebParam(name = "id_ciudad") int idCiudad
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarCentro");
        spq.setParameter("p_ID_CENTRO", idCentro);
        spq.setParameter("p_NOMBRE_CENTRO", nombreCentro);
        spq.setParameter("p_ID_USUARIO", idUsuario);
        spq.setParameter("p_ID_CIUDAD", idCiudad);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeCentro")
    @Oneway
    public void removeCentro(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarCentro");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_CENTRO", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findCentro")
    public Centro findCentro(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarCentro");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_CENTRO", id);
   
        return (Centro)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createCentro")
    @Oneway
    public void createCentro(
            @WebParam(name = "nombre_centro") String nombreCentro,
            @WebParam(name = "id_usuario") int idUsuario,
            @WebParam(name = "id_ciudad") int idCiudad
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarCentro");
        spq.setParameter("p_NOMBRE_CENTRO", nombreCentro);
        spq.setParameter("p_ID_USUARIO", idUsuario);
        spq.setParameter("p_ID_CIUDAD", idCiudad);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllCentro")
    public List<Centro> findAllCentro() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarCentro");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_CENTRO", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findCentroPor")
    public List<Centro> findCentroPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarCentroPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
