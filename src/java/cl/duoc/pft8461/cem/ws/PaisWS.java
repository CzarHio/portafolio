/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Pais;
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
@WebService(serviceName = "PaisWS")
public class PaisWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editPais")
    @Oneway
    public void editPais(
            @WebParam(name = "id_pais") int idPais,
            @WebParam(name = "nombre_pais") String nombrePais
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarPais");
        spq.setParameter("p_ID_PAIS", idPais);
        spq.setParameter("p_NOMBRE_PAIS", nombrePais);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removePais")
    @Oneway
    public void removePais(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarPais");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_PAIS", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findPais")
    public Pais findPais(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarPais");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_PAIS", id);
   
        return (Pais)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createPais")
    @Oneway
    public void createPais(
            @WebParam(name = "nombre_pais") String nombrePais
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarPais");
        spq.setParameter("p_NOMBRE_PAIS", nombrePais);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllPais")
    public List<Pais> findAllPais() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarPais");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_PAIS", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findPaisPor")
    public List<Pais> findPaisPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarPaisPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
