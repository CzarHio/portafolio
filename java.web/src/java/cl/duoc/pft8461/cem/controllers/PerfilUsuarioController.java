/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.PerfilUsuarioEntity;
import cl.duoc.pft8461.cem.ws.PerfilUsuario;
import cl.duoc.pft8461.cem.ws.PerfilUsuarioWS;
import cl.duoc.pft8461.cem.ws.PerfilUsuarioWS_Service;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Joe
 */
@Controller
@SessionAttributes
public class PerfilUsuarioController extends BaseController {
    
    private final PerfilUsuarioWS perfilUsuarioWS = new PerfilUsuarioWS_Service().getPerfilUsuarioWSPort();

    public PerfilUsuarioController() {
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
    @RequestMapping(value = {"perfil-usuario/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        List<PerfilUsuario> listaPerfilUsuario = this.perfilUsuarioWS.findAllPerfilUsuario();
        mav.addObject("listado", listaPerfilUsuario);

        mav.addObject("tituloPagina", "Perfil Usuario");
        mav.addObject("subtituloPagina", "Listado de Perfiles de Usuario:");
        mav.setViewName("perfilUsuario/lista");
        return mav;
    }

    @RequestMapping(value = {"perfil-usuario/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        PerfilUsuarioEntity pu = new PerfilUsuarioEntity(this.perfilUsuarioWS.findPerfilUsuario(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", pu.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"perfil-usuario/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.perfilUsuarioWS.removePerfilUsuario(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"perfil-usuario/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (this.isEmpty(request.getParameter("idPerfilUsuario"))) {
                this.perfilUsuarioWS.createPerfilUsuario(
                    request.getParameter("nombrePerfil"));
            } else {
                this.perfilUsuarioWS.editPerfilUsuario(
                    Integer.parseInt(request.getParameter("idPerfilUsuario")),
                    request.getParameter("nombrePerfil"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
}
