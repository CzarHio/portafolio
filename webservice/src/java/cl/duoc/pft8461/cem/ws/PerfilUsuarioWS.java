/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.PerfilUsuario;
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
@WebService(serviceName = "PerfilUsuarioWS")
public class PerfilUsuarioWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editPerfilUsuario")
    @Oneway
    public void editPerfilUsuario(
            @WebParam(name = "id_perfil_usuario") int idPerfilUsuario,
            @WebParam(name = "nombre_perfil") String nombrePerfil
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("actualizarPerfilUsuario");
        spq.setParameter("p_ID_PERFIL_USUARIO", idPerfilUsuario);
        spq.setParameter("p_NOMBRE_PERFIL", nombrePerfil);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "removePerfilUsuario")
    @Oneway
    public void removePerfilUsuario(@WebParam(name = "entity") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarPerfilUsuario");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_PERFIL_USUARIO", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findPerfilUsuario")
    public PerfilUsuario findPerfilUsuario(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarPerfilUsuario");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_PERFIL_USUARIO", id);
   
        return (PerfilUsuario)storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createPerfilUsuario")
    @Oneway
    public void createPerfilUsuario(
            @WebParam(name = "id_perfil_usuario") int idPerfilUsuario,
            @WebParam(name = "nombre_perfil") String nombrePerfil
    ) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("insertarPerfilUsuario");
        spq.setParameter("p_ID_PERFIL_USUARIO", idPerfilUsuario);
        spq.setParameter("p_NOMBRE_PERFIL", nombrePerfil);
        StoredProcedureQuery storedProcedure = spq;
        
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findAllPerfilUsuario")
    public List<PerfilUsuario> findAllPerfilUsuario() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarPerfilUsuario");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("p_ID_PERFIL_USUARIO", 0);
   
        return storedProcedure.getResultList();
    }
    
    @WebMethod(operationName = "findPerfilUsuarioPor")
    public List<PerfilUsuario> findPerfilUsuarioPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarPerfilUsuarioPor");
        StoredProcedureQuery storedProcedure = 
        spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);
   
        return storedProcedure.getResultList();
    }
}
