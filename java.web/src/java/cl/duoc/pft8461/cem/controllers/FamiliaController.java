/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.ws.Familia;
import cl.duoc.pft8461.cem.ws.FamiliaWS;
import cl.duoc.pft8461.cem.ws.FamiliaWS_Service;
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
public class FamiliaController {
    
    private final FamiliaWS familiaWS = new FamiliaWS_Service().getFamiliaWSPort();

    public FamiliaController() {
    }
    
    @RequestMapping(value = {"familia/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        List<Familia> listaFamilia = this.familiaWS.findAllFamilia();
        mav.addObject("listado", listaFamilia);
        mav.addObject("tituloPagina", "Familia");
        mav.addObject("subtituloPagina", "Listado de Familias:");
        mav.setViewName("familia/lista");
        return mav;
    }
    
}
