/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.PaisEntity;
import cl.duoc.pft8461.cem.ws.Pais;
import cl.duoc.pft8461.cem.ws.PaisWS;
import cl.duoc.pft8461.cem.ws.PaisWS_Service;
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
public class PaisController extends BaseController {
    
    private final PaisWS paisWS = new PaisWS_Service().getPaisWSPort();

    public PaisController() {
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
    @RequestMapping(value = {"pais/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        List<Pais> listaPais = this.paisWS.findAllPais();
        mav.addObject("listado", listaPais);

        mav.addObject("tituloPagina", "País");
        mav.addObject("subtituloPagina", "Listado de Paises:");
        mav.setViewName("pais/lista");
        return mav;
    }

    @RequestMapping(value = {"pais/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        PaisEntity usr = new PaisEntity(this.paisWS.findPais(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", usr.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"pais/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.paisWS.removePais(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"pais/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (this.isEmpty(request.getParameter("idPais"))) {
                this.paisWS.createPais(
                    request.getParameter("nombrePais"));
            } else {
                this.paisWS.editPais(
                    Integer.parseInt(request.getParameter("idPais")),
                    request.getParameter("nombrePais"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
    
}
