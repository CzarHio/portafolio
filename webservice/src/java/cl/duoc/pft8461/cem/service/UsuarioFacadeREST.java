/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.service;

import cl.duoc.pft8461.cem.entidades.Usuario;
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
@Path("entidades.usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(@PathParam("usuario") String usuario, 
            @PathParam("clave") String clave,
            @PathParam("nombre") String nombre,
            @PathParam("apellido_pat") String apellido_pat,
            @PathParam("apellido_mat") String apellido_mat,
            @PathParam("email") String email,
            @PathParam("perfil_usuario") int perfilUsuario ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarUsuario");
        spq.setParameter("p_APELLIDO_MAT", apellido_mat);
        spq.setParameter("p_ID_PERFIL_USUARIO", perfilUsuario);
        spq.setParameter("p_CLAVE", clave);
        spq.setParameter("p_APELLIDO_PAT", apellido_pat);
        spq.setParameter("p_EMAIL", email);
        spq.setParameter("p_USUARIO", usuario);
        spq.setParameter("p_NOMBRE", nombre);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") int idUsuario,
            @PathParam("usuario") String usuario, 
            @PathParam("nombre") String nombre,
            @PathParam("apellido_pat") String apellido_pat,
            @PathParam("apellido_mat") String apellido_mat,
            @PathParam("email") String email,
            @PathParam("perfil_usuario") int perfilUsuario) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarUsuario");
        spq.setParameter("p_APELLIDO_MAT", apellido_mat);
        spq.setParameter("p_ID_PERFIL_USUARIO", perfilUsuario);
        spq.setParameter("p_ID_USUARIO", idUsuario);
        spq.setParameter("p_APELLIDO_PAT", apellido_pat);
        spq.setParameter("p_EMAIL", email);
        spq.setParameter("p_USUARIO", usuario);
        spq.setParameter("p_NOMBRE", nombre);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarUsuario");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_USUARIO", id);
        storedProcedure.execute();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario find(@PathParam("id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarUsuario");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_USUARIO", id);
   
        return (Usuario)storedProcedure.getSingleResult();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarUsuario");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_USUARIO", 0);
   
        return storedProcedure.getResultList();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @Path("autenticar")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario autenticar(@PathParam("usuario") String usuario, @PathParam("clave") String clave) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("autenticarUsuario");
        spq.setParameter("p_USUARIO", usuario);
        spq.setParameter("p_CLAVE", clave);
        StoredProcedureQuery storedProcedure =  spq;
        return (Usuario)storedProcedure.getSingleResult();
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
