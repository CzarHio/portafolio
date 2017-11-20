/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.ws;

import cl.duoc.pft8461.cem.entidades.Usuario;
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
@WebService(serviceName = "UsuarioWS")
public class UsuarioWS {

    @PersistenceContext(unitName = "webservicePU")
    private EntityManager em;

    @WebMethod(operationName = "editUsuario")
    @Oneway
    public void editUsuario(
            @WebParam(name = "id_usuario") int idUsuario,
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido_pat") String apellido_pat,
            @WebParam(name = "apellido_mat") String apellido_mat,
            @WebParam(name = "email") String email,
            @WebParam(name = "perfil_usuario") int perfilUsuario
    ) {
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

    @WebMethod(operationName = "removeUsuario")
    @Oneway
    public void removeUsuario(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("borrarUsuario");
        StoredProcedureQuery storedProcedure = spq.setParameter("p_ID_USUARIO", id);
        storedProcedure.execute();
    }

    @WebMethod(operationName = "findUsuario")
    public Usuario findUsuario(@WebParam(name = "id") int id) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarUsuario");
        StoredProcedureQuery storedProcedure
                = spq.setParameter("p_ID_USUARIO", id);

        return (Usuario) storedProcedure.getSingleResult();
    }

    @WebMethod(operationName = "createUsuario")
    @Oneway
    public void createUsuario(
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "clave") String clave,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido_pat") String apellido_pat,
            @WebParam(name = "apellido_mat") String apellido_mat,
            @WebParam(name = "email") String email,
            @WebParam(name = "perfil_usuario") int perfilUsuario
    ) {
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

    @WebMethod(operationName = "findAllUsuarios")
    public List<Usuario> findAllUsuarios() {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarUsuario");
        StoredProcedureQuery storedProcedure
                = spq.setParameter("p_ID_USUARIO", 0);

        return storedProcedure.getResultList();
    }

    @WebMethod(operationName = "findUsuarioPor")
    public List<Usuario> findUsuarioPor(@WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("seleccionarUsuarioPor");
        StoredProcedureQuery storedProcedure
                = spq.setParameter("ve_campo", campo);
        spq.setParameter("ve_valor", valor);

        return storedProcedure.getResultList();
    }

    @WebMethod(operationName = "autenticar")
    public Usuario autenticar(@WebParam(name = "usuario") String usuario, @WebParam(name = "clave") String clave) {

        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("autenticarUsuario");
        spq.setParameter("p_USUARIO", usuario);
        spq.setParameter("p_CLAVE", clave);
        try {
            StoredProcedureQuery storedProcedure = spq;
            return (Usuario) storedProcedure.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

}
