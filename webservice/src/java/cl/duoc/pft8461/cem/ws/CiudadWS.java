/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Ciudad;
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
@WebService(serviceName = "CiudadWS")
public class CiudadWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editCiudad")
    @Oneway
    public void editCiudad(
            @WebParam(name = "id_ciudad") int idCiudad,
            @WebParam(name = "nombre_ciudad") String nombreCiudad,
            @WebParam(name = "id_region") int idRegion
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarCiudad");
        spq.setParameter("p_ID_CIUDAD", idCiudad);
        spq.setParameter("p_NOMBRE_CIUDAD", nombreCiudad);
        spq.setParameter("p_ID_REGION", idRegion);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeCiudad")
    @Oneway
    public void removeCiudad(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarCiudad");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_CIUDAD", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findCiudad")
    public Ciudad findCiudad(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarCiudad");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_CIUDAD", id);
   
        return (Ciudad)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createCiudad")
    @Oneway
    public void createCiudad(
            @WebParam(name = "nombre_ciudad") String nombreCiudad,
            @WebParam(name = "id_region") int idRegion
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarCiudad");
        spq.setParameter("p_NOMBRE_CIUDAD", nombreCiudad);
        spq.setParameter("p_ID_REGION", idRegion);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllCiudad")
    public List<Ciudad> findAllCiudad() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarCiudad");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_CIUDAD", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findCiudadPor")
    public List<Ciudad> findCiudadPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarCiudadPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
