/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;

/**
 *
 * @author Joe
 */

@Controller
@SessionAttributes
public class HomeController {
    
    private final UsuarioWS_Service ws = new UsuarioWS_Service();
    
    public HomeController() {
    }
    
    /**
     * Método form, que carga el formulario de login
     * de la aplicación del Sistema CEM.
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @RequestMapping(value = {"home.htm"}, method = RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
     
        mav.setViewName("home");
        return mav;
    }

}
