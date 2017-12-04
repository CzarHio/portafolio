/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.RegionEntity;
import cl.duoc.pft8461.cem.ws.Pais;
import cl.duoc.pft8461.cem.ws.PaisWS;
import cl.duoc.pft8461.cem.ws.PaisWS_Service;
import cl.duoc.pft8461.cem.ws.Region;
import cl.duoc.pft8461.cem.ws.RegionWS;
import cl.duoc.pft8461.cem.ws.RegionWS_Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class RegionController extends BaseController {
    
    private final RegionWS regionWS = new RegionWS_Service().getRegionWSPort();
    private final PaisWS paisWS = new PaisWS_Service().getPaisWSPort();
    private Map<Integer, Pais> paises = new HashMap<Integer, Pais>();
    private List<Pais> listaPais = this.paisWS.findAllPais();

    public RegionController() {
        for (Pais pais : this.listaPais) {
            this.paises.put(pais.getIdPais().intValue(), pais);
        }
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
    @RequestMapping(value = {"region/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        List<Region> listaRegion = this.regionWS.findAllRegion();
        mav.addObject("listado", listaRegion);
        mav.addObject("paises", paises);

        mav.addObject("tituloPagina", "Región");
        mav.addObject("subtituloPagina", "Listado de Regiones:");
        mav.setViewName("region/lista");
        return mav;
    }

    @RequestMapping(value = {"region/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        RegionEntity usr = new RegionEntity(this.regionWS.findRegion(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", usr.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"region/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.regionWS.removeRegion(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"region/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (this.isEmpty(request.getParameter("idRegion"))) {
                this.regionWS.createRegion(
                    request.getParameter("nombreRegion"),
                    Integer.parseInt(request.getParameter("idPais")));
            } else {
                this.regionWS.editRegion(
                    Integer.parseInt(request.getParameter("idRegion")),
                    request.getParameter("nombreRegion"),
                    Integer.parseInt(request.getParameter("idPais")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
}
