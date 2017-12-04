/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Region;
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
@WebService(serviceName = "RegionWS")
public class RegionWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editRegion")
    @Oneway
    public void editRegion(
            @WebParam(name = "id_region") int idRegion,
            @WebParam(name = "nombre_region") String nombreRegion,
            @WebParam(name = "id_pais") int idPais
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarRegion");
        spq.setParameter("p_ID_REGION", idRegion);
        spq.setParameter("p_NOMBRE_REGION", nombreRegion);
        spq.setParameter("p_ID_PAIS", idPais);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeRegion")
    @Oneway
    public void removeRegion(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarRegion");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_REGION", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findRegion")
    public Region findRegion(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarRegion");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_REGION", id);
   
        return (Region)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createRegion")
    @Oneway
    public void createRegion(
            @WebParam(name = "nombre_region") String nombreRegion,
            @WebParam(name = "id_pais") int idPais
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarRegion");
        spq.setParameter("p_NOMBRE_REGION", nombreRegion);
        spq.setParameter("p_ID_PAIS", idPais);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllRegion")
    public List<Region> findAllRegion() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarRegion");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_REGION", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findRegionPor")
    public List<Region> findRegionPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarRegionPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
