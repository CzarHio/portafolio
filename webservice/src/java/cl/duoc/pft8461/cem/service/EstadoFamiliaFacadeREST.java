/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.service;

import cl.duoc.pft8461.cem.entidades.EstadoFamilia;
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
@Path("entidades.estadofamilia")
public class EstadoFamiliaFacadeREST extends AbstractFacade<EstadoFamilia> {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    public EstadoFamiliaFacadeREST() {
        super(EstadoFamilia.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(EstadoFamilia entity) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarEstadoFamilia");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", entity.getIdEstado());
        spq.setParameter("p_ESTADO", entity.getEstado());
        storedProcedure.execute();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") BigDecimal id, EstadoFamilia entity) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarEstadoFamilia");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", id);
        spq.setParameter("p_ESTADO", entity.getEstado());
        storedProcedure.execute();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarEstadoFamilia");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", id);
        storedProcedure.execute();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public EstadoFamilia find(@PathParam("id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoFamilia");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", id);
   
        return (EstadoFamilia)storedProcedure.getSingleResult();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<EstadoFamilia> findAll() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarEstadoFamilia");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_ESTADO", 0);
   
        return storedProcedure.getResultList();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<EstadoFamilia> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
