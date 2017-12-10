/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.MenuEntity;
import cl.duoc.pft8461.cem.ws.Menu;
import cl.duoc.pft8461.cem.ws.MenuItem;
import cl.duoc.pft8461.cem.ws.MenuItemWS;
import cl.duoc.pft8461.cem.ws.MenuItemWS_Service;
import cl.duoc.pft8461.cem.ws.MenuWS;
import cl.duoc.pft8461.cem.ws.MenuWS_Service;
import cl.duoc.pft8461.cem.ws.PerfilUsuario;
import cl.duoc.pft8461.cem.ws.PerfilUsuarioWS;
import cl.duoc.pft8461.cem.ws.PerfilUsuarioWS_Service;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Joe
 */
public class MenuController extends BaseController {
    
    private MenuWS menuWS = new MenuWS_Service().getMenuWSPort();
    private MenuItemWS menuItemWS = new MenuItemWS_Service().getMenuItemWSPort();
    private PerfilUsuarioWS perfilUsuarioWS = new PerfilUsuarioWS_Service().getPerfilUsuarioWSPort();
    private List<PerfilUsuario> listaPerfil = this.perfilUsuarioWS.findAllPerfilUsuario();

    public MenuController() {
    }
    
    @RequestMapping(value = {"menu/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        List<Menu> listaMenu = this.menuWS.findAllMenu();
        List<MenuItem> listaMenuItem = this.menuItemWS.findAllMenuItem();
        mav.addObject("listado", listaMenu);
        mav.addObject("menuItem", listaMenuItem);
        mav.addObject("perfiles", this.listaPerfil);

        mav.addObject("tituloPagina", "Menú");
        mav.addObject("subtituloPagina", "Listado de Menúes:");
        mav.setViewName("menu/lista");
        return mav;
    }

    @RequestMapping(value = {"menu/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        MenuEntity m = new MenuEntity(this.menuWS.findMenu(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", m.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"menu/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.menuWS.removeMenu(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"menu/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (this.isEmpty(request.getParameter("idMenu"))) {
                this.menuWS.createMenu(
                    request.getParameter("titulo"),
                    Integer.parseInt(request.getParameter("perfilUsuario")));
            } else {
                this.menuWS.editMenu(
                    Integer.parseInt(request.getParameter("idMenu")),
                    request.getParameter("titulo"),
                    Integer.parseInt(request.getParameter("orden")),
                    Integer.parseInt(request.getParameter("perfilUsuario")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
    
}
