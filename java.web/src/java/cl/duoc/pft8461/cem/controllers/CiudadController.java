/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.CiudadEntity;
import cl.duoc.pft8461.cem.ws.Ciudad;
import cl.duoc.pft8461.cem.ws.CiudadWS;
import cl.duoc.pft8461.cem.ws.CiudadWS_Service;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Joe
 */
public class CiudadController extends BaseController {

    private final CiudadWS ciudadWS = new CiudadWS_Service().getCiudadWSPort();
    private final RegionWS regionWS = new RegionWS_Service().getRegionWSPort();
    private Map<Integer, Region> regiones = new HashMap<Integer, Region>();
    private List<Region> listaRegion = this.regionWS.findAllRegion();
    
    public CiudadController() {
        for (Region region : this.listaRegion) {
            this.regiones.put(region.getIdRegion().intValue(), region);
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
    @RequestMapping(value = {"ciudad/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        List<Ciudad> listaCiudad = this.ciudadWS.findAllCiudad();
        mav.addObject("listado", listaCiudad);
        mav.addObject("regiones", regiones);

        mav.addObject("tituloPagina", "Ciudad");
        mav.addObject("subtituloPagina", "Listado de Ciudades:");
        mav.setViewName("ciudad/lista");
        return mav;
    }

    @RequestMapping(value = {"ciudad/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        CiudadEntity c = new CiudadEntity(this.ciudadWS.findCiudad(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", c.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"ciudad/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.ciudadWS.removeCiudad(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"ciudad/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (this.isEmpty(request.getParameter("idCiudad"))) {
                this.ciudadWS.createCiudad(
                    request.getParameter("nombreCiudad"),
                    Integer.parseInt(request.getParameter("idRegion")));
            } else {
                this.ciudadWS.editCiudad(
                    Integer.parseInt(request.getParameter("idCiudad")),
                    request.getParameter("nombreCiudad"),
                    Integer.parseInt(request.getParameter("idRegion")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
    
    
    
}
