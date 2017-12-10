/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.MenuItemEntity;
import cl.duoc.pft8461.cem.ws.MenuItemWS;
import cl.duoc.pft8461.cem.ws.MenuItemWS_Service;
import java.io.IOException;
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
public class MenuItemController extends BaseController {
    
    private MenuItemWS menuItemWS = new MenuItemWS_Service().getMenuItemWSPort();
    
    public MenuItemController() {
    }

    @RequestMapping(value = {"menuItem/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        MenuItemEntity m = new MenuItemEntity(this.menuItemWS.findMenuItem(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", m.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"menuItem/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.menuItemWS.removeMenuItem(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"menuItem/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (this.isEmpty(request.getParameter("idMenuItem"))) {
                this.menuItemWS.createMenuItem(
                    request.getParameter("titulo"),
                    request.getParameter("url"),
                    Integer.parseInt(request.getParameter("idMenu")),
                    Integer.parseInt(request.getParameter("orden")));
            } else {
                this.menuItemWS.editMenuItem(
                    Integer.parseInt(request.getParameter("idMenuItem")),
                    request.getParameter("titulo"),
                    request.getParameter("url"),
                    Integer.parseInt(request.getParameter("idMenu")),
                    Integer.parseInt(request.getParameter("padre")),
                    Integer.parseInt(request.getParameter("orden")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
}
