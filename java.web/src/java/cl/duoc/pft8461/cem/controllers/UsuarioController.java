/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;


import cl.duoc.pft8461.cem.entidades.UsuarioEntity;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import cl.duoc.pft8461.cem.ws.PerfilUsuario;
import cl.duoc.pft8461.cem.ws.PerfilUsuarioWS_Service;
import cl.duoc.pft8461.cem.ws.Usuario;
import cl.duoc.pft8461.cem.ws.UsuarioWS;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;

/**
 *
 * @author Joe
 */
@Controller
@SessionAttributes
public class UsuarioController {

    private final UsuarioWS usuarioWS = new UsuarioWS_Service().getUsuarioWSPort();
    private List<PerfilUsuario> perfilesUsuario;

    public UsuarioController() {
        PerfilUsuarioWS_Service perfilUsuarioWS = new PerfilUsuarioWS_Service();
        perfilesUsuario = perfilUsuarioWS.getPerfilUsuarioWSPort().findAllPerfilUsuario();
    }

    /**
     * Método form, que carga el formulario de login de la aplicación del
     * Sistema CEM.
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = {"usuario/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        List<Usuario> listaUsuario = usuarioWS.findAllUsuarios();
        mav.addObject("listado", listaUsuario);
        mav.addObject("perfilesUsuario", perfilesUsuario);

        mav.addObject("tituloPagina", "Usuario");
        mav.addObject("subtituloPagina", "Listado de Usuarios:");
        mav.setViewName("usuarioLista");
        return mav;
    }

    @RequestMapping(value = {"usuario/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        UsuarioEntity usr = new UsuarioEntity(usuarioWS.findUsuario(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", usr.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"usuario/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrarPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            usuarioWS.removeUsuario(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"usuario/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (request.getParameter("idUsuario") == null) {
                usuarioWS.createUsuario(
                    request.getParameter("usuario"),
                    request.getParameter("clave"),
                    request.getParameter("nombre"),
                    request.getParameter("apellidoPat"),
                    request.getParameter("apellidoMat"),
                    request.getParameter("email"),
                    Integer.parseInt(request.getParameter("idPerfilUsuario")));
            } else {
                usuarioWS.editUsuario(
                        Integer.parseInt(request.getParameter("idUsuario")),
                        request.getParameter("usuario"),
                        request.getParameter("nombre"),
                        request.getParameter("apellidoPat"),
                        request.getParameter("apellidoMat"),
                        request.getParameter("email"),
                        Integer.parseInt(request.getParameter("idPerfilUsuario")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
}