/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;


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

    @RequestMapping(value = {"usuario/nuevo.htm"}, method = RequestMethod.GET)
    public ModelAndView nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        mav.addObject("perfilesUsuario", perfilesUsuario);
        mav.setViewName("usuarioForm");

        return mav;
    }

    @RequestMapping(value = {"usuario/editar.htm"}, method = RequestMethod.GET)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        Usuario usr = usuarioWS.findUsuario(Integer.parseInt(request.getParameter("id")));
        mav.addObject("usr", usr);
        mav.addObject("perfilesUsuario", perfilesUsuario);
        mav.setViewName("usuarioForm");

        return mav;
    }

    @RequestMapping(value = {"usuario/borrar.htm"}, method = RequestMethod.GET)
    public ModelAndView borrarGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        Usuario usr = usuarioWS.findUsuario(Integer.parseInt(request.getParameter("id")));
        mav.addObject("usr", usr);
        mav.setViewName("usuarioBorrar");
        return mav;
    }

    @RequestMapping(value = {"usuario/borrar.htm"}, method = RequestMethod.POST)
    public void borrarPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        usuarioWS.removeUsuario(Integer.parseInt(request.getParameter("inputIdUsuario")));

        response.sendRedirect("lista.htm");;
    }

    @RequestMapping(value = {"usuario/guardar.htm"}, method = RequestMethod.POST)
    public void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("inputIdUsuario") == null) {
            usuarioWS.createUsuario(
                    request.getParameter("inputUsuario"),
                    request.getParameter("inputClave"),
                    request.getParameter("inputNombre"),
                    request.getParameter("inputApellidoPat"),
                    request.getParameter("inputApellidoMat"),
                    request.getParameter("inputEmail"),
                    Integer.parseInt(request.getParameter("inputPerfilUsuario")));
        } else {
            usuarioWS.editUsuario(
                    Integer.parseInt(request.getParameter("inputIdUsuario")),
                    request.getParameter("inputUsuario"),
                    request.getParameter("inputNombre"),
                    request.getParameter("inputApellidoPat"),
                    request.getParameter("inputApellidoMat"),
                    request.getParameter("inputEmail"),
                    Integer.parseInt(request.getParameter("inputPerfilUsuario")));
        }
        response.sendRedirect("lista.htm");

    }
}