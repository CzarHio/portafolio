/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.ws.Menu;
import cl.duoc.pft8461.cem.ws.MenuItem;
import cl.duoc.pft8461.cem.ws.MenuItemWS;
import cl.duoc.pft8461.cem.ws.MenuItemWS_Service;
import cl.duoc.pft8461.cem.ws.MenuWS;
import cl.duoc.pft8461.cem.ws.MenuWS_Service;
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

        mav.addObject("tituloPagina", "Menú");
        mav.addObject("subtituloPagina", "Listado de Menúes:");
        mav.setViewName("menu/lista");
        return mav;
    }
    
    
}
