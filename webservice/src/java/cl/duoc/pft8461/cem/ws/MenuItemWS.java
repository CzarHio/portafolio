/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.MenuItem;
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
@WebService(serviceName = "MenuItemWS")
public class MenuItemWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editMenuItem")
    @Oneway
    public void editMenuItem(
            @WebParam(name = "id_menu_item") int idMenuItem,
            @WebParam(name = "titulo") String titulo,
            @WebParam(name = "url") String url,
            @WebParam(name = "id_menu") int idMenu,
            @WebParam(name = "padre") int padre,
            @WebParam(name = "orden") int orden
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarMenuItem");
        spq.setParameter("p_ID_MENU_ITEM", idMenuItem);
        spq.setParameter("p_TITULO", titulo);
        spq.setParameter("p_URL", url);
        spq.setParameter("p_ID_MENU", idMenu);
        spq.setParameter("p_PADRE", padre);
        spq.setParameter("p_ORDEN", orden);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeMenuItem")
    @Oneway
    public void removeMenuItem(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarMenuItem");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_MENU_ITEM", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findMenuItem")
    public MenuItem findMenuItem(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarMenuItem");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_MENU_ITEM", id);
   
        return (MenuItem)storedProcedure.getSingleResult();
    }
    
    @WebMethod(operationName = "findMenuItemPor")
    public List<MenuItem> findMenuItemPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarMenuItemPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
  
        return storedProcedure.getResultList();
    }

    @WebMethod(operationName = "createMenuItem")
    @Oneway
    public void createMenuItem(
            @WebParam(name = "titulo") String titulo,
            @WebParam(name = "url") String url,
            @WebParam(name = "id_menu") int idMenu,
            @WebParam(name = "orden") int orden
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarMenuItem");
        spq.setParameter("p_TITULO", titulo);
        spq.setParameter("p_URL", url);
        spq.setParameter("p_ID_MENU", idMenu);
        spq.setParameter("p_ORDEN", orden);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllMenuItem")
    public List<MenuItem> findAllMenuItem() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarMenuItem");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_MENU_ITEM", 0);
   
        return storedProcedure.getResultList();
    }

}
