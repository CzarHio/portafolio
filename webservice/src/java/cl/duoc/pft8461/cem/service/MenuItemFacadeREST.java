/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.service;

import cl.duoc.pft8461.cem.entidades.MenuItem;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Czar
 */
@Stateless
@Path("entidades.menuitem")
public class MenuItemFacadeREST extends AbstractFacade<MenuItem> {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    public MenuItemFacadeREST() {
        super(MenuItem.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(MenuItem entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") BigDecimal id, MenuItem entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") int id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public MenuItem find(@PathParam("id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarMenuItem");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_MENU_ITEM", id);
   
        return (MenuItem)storedProcedure.getSingleResult();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<MenuItem> findAll() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarMenuItem");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_MENU_ITEM", 0);
   
        return storedProcedure.getResultList();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<MenuItem> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
