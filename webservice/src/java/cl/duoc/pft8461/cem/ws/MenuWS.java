/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Menu;
import cl.duoc.pft8461.cem.entidades.MenuItem;
import java.util.ArrayList;
import java.util.Collection;
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
@WebService(serviceName = "MenuWS")
public class MenuWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editMenu")
    @Oneway
    public void editMenu(
            @WebParam(name = "id_menu") int idMenu,
            @WebParam(name = "titulo") String titulo,
            @WebParam(name = "perfil_usuario") int perfilUsuario,
            @WebParam(name = "orden") int orden
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarMenu");
        spq.setParameter("p_ID_MENU", idMenu);
        spq.setParameter("p_TITULO", titulo);
        spq.setParameter("p_PERFIL_USUARIO", perfilUsuario);
        spq.setParameter("p_ORDEN", orden);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removeMenu")
    @Oneway
    public void removeMenu(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarMenu");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_MENU", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findMenu")
    public Menu findMenu(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarMenu");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_MENU", id);
   
        return (Menu)storedProcedure.getSingleResult();
    }
    
    @WebMethod(operationName = "findMenuPor")
    public List<Menu> findMenuPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarMenuPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }

    @WebMethod(operationName = "createMenu")
    @Oneway
    public void createMenu(
            @WebParam(name = "titulo") String titulo,
            @WebParam(name = "perfil_usuario") int perfilUsuario
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarMenu");
        spq.setParameter("p_TITULO", titulo);
        spq.setParameter("p_PERFIL_USUARIO", perfilUsuario);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }


    @WebMethod(operationName = "findAllMenu")
    public List<Menu> findAllMenu() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarMenu");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_MENU", 0);
   
        return storedProcedure.getResultList();
    }
    
}
