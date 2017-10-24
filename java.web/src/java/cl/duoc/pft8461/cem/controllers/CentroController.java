/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.ArbolMenu;
import cl.duoc.pft8461.cem.ws.Centro;
import cl.duoc.pft8461.cem.ws.CentroWS;
import cl.duoc.pft8461.cem.ws.CentroWS_Service;
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
import cl.duoc.pft8461.cem.ws.Menu;
import cl.duoc.pft8461.cem.ws.MenuItem;
import cl.duoc.pft8461.cem.ws.MenuItemWS_Service;
import cl.duoc.pft8461.cem.ws.MenuWS_Service;
import cl.duoc.pft8461.cem.ws.Centro;
import cl.duoc.pft8461.cem.ws.CentroWS;
import cl.duoc.pft8461.cem.ws.CentroWS_Service;
import cl.duoc.pft8461.cem.ws.Ciudad;
import cl.duoc.pft8461.cem.ws.CiudadWS;
import cl.duoc.pft8461.cem.ws.CiudadWS_Service;
import cl.duoc.pft8461.cem.ws.Usuario;
import cl.duoc.pft8461.cem.ws.UsuarioWS;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;
import java.util.LinkedList;
import sun.reflect.generics.tree.Tree;

/**
 *
 * @author Joe
 */
@Controller
@SessionAttributes
public class CentroController {

    private final CentroWS centroWS = new CentroWS_Service().getCentroWSPort();

    public CentroController() {
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
    @RequestMapping(value = {"centro/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        List<Centro> listaCentro = centroWS.findAllCentro();
        mav.addObject("listado", listaCentro);

        mav.addObject("listaMenu", this.getMenu());
        mav.addObject("tituloPagina", "Centro");
        mav.addObject("subtituloPagina", "Listado de Centros:");
        mav.setViewName("centroLista");
        return mav;
    }

    @RequestMapping(value = {"centro/nuevo.htm"}, method = RequestMethod.GET)
    public ModelAndView nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        UsuarioWS usuarioWS = new UsuarioWS_Service().getUsuarioWSPort();
        CiudadWS ciudadWS = new CiudadWS_Service().getCiudadWSPort();

        List<Usuario> celUsuario = usuarioWS.findUsuarioPor("ID_PERFIL_USUARIO", "3");
        List<Ciudad> ciudades = ciudadWS.findAllCiudad();
        mav.addObject("celUsuario", celUsuario);
        mav.addObject("ciudades", ciudades);
        mav.setViewName("centroForm");

        return mav;
    }

    @RequestMapping(value = {"centro/editar.htm"}, method = RequestMethod.GET)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        UsuarioWS usuarioWS = new UsuarioWS_Service().getUsuarioWSPort();
        CiudadWS ciudadWS = new CiudadWS_Service().getCiudadWSPort();

        List<Usuario> celUsuario = usuarioWS.findUsuarioPor("ID_PERFIL_USUARIO", "3");
        List<Ciudad> ciudades = ciudadWS.findAllCiudad();
        mav.addObject("celUsuario", celUsuario);
        mav.addObject("ciudades", ciudades);
        Centro centro = centroWS.findCentro(Integer.parseInt(request.getParameter("id")));
        mav.addObject("centro", centro);
        mav.setViewName("centroForm");

        return mav;
    }

    @RequestMapping(value = {"centro/borrar.htm"}, method = RequestMethod.GET)
    public ModelAndView borrarGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        Centro centro = centroWS.findCentro(Integer.parseInt(request.getParameter("id")));
        mav.addObject("centro", centro);
        mav.setViewName("centroBorrar");
        return mav;
    }

    @RequestMapping(value = {"centro/borrar.htm"}, method = RequestMethod.POST)
    public void borrarPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        centroWS.removeCentro(Integer.parseInt(request.getParameter("inputIdCentro")));

        response.sendRedirect("lista.htm");;
    }

    @RequestMapping(value = {"centro/guardar.htm"}, method = RequestMethod.POST)
    public void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            if (request.getParameter("inputIdCentro") == null) {
            centroWS.createCentro(
                    request.getParameter("inputNombreCentro"),
                    Integer.parseInt(request.getParameter("inputUsuario")),
                    Integer.parseInt(request.getParameter("inputCiudad")));
        } else {
            centroWS.editCentro(
                    Integer.parseInt(request.getParameter("inputIdCentro")),
                    request.getParameter("inputNombreCentro"),
                    Integer.parseInt(request.getParameter("inputUsuario")),
                    Integer.parseInt(request.getParameter("inputCiudad")));
        }
        response.sendRedirect("lista.htm");

    }

    private List<ArbolMenu> getMenu() {
        MenuWS_Service menuWS = new MenuWS_Service();
        MenuItemWS_Service menuItemWS = new MenuItemWS_Service();
        List<ArbolMenu> arbol = new LinkedList<>();
        List<Menu> listaMenu = menuWS.getMenuWSPort().findMenuPor("PERFIL_USUARIO", "1");

        for (Menu m : listaMenu) {
            ArbolMenu am = new ArbolMenu(m);
            am.setListaMenu(menuItemWS.getMenuItemWSPort().findMenuItemPor("ID_MENU", m.getIdMenu().toString()));
            arbol.add(am);
        }
        return arbol;
    }
}
