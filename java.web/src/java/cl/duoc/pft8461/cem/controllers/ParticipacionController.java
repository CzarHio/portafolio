/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.ws.EstadoParticipacion;
import cl.duoc.pft8461.cem.ws.EstadoParticipacionWS;
import cl.duoc.pft8461.cem.ws.EstadoParticipacionWS_Service;
import cl.duoc.pft8461.cem.ws.Pais;
import cl.duoc.pft8461.cem.ws.PaisWS;
import cl.duoc.pft8461.cem.ws.PaisWS_Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import cl.duoc.pft8461.cem.ws.Participacion;
import cl.duoc.pft8461.cem.ws.ParticipacionWS;
import cl.duoc.pft8461.cem.ws.ParticipacionWS_Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Joe
 */
@Controller
@SessionAttributes
public class ParticipacionController extends BaseController {

    private final ParticipacionWS participacionWS = new ParticipacionWS_Service().getParticipacionWSPort();
    private final PaisWS paisWS = new PaisWS_Service().getPaisWSPort();
    private final EstadoParticipacionWS estadoParticipacionWS = new EstadoParticipacionWS_Service().getEstadoParticipacionWSPort();
    private Map<Integer, Pais> paises = new HashMap<Integer, Pais>();
    private List<Pais> listaPais = this.paisWS.findAllPais();

    public ParticipacionController() {
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
    @RequestMapping(value = {"participacion/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ModelAndView mav = new ModelAndView();

        List<EstadoParticipacion> listaEstados = this.estadoParticipacionWS.findAllEstadoParticipacion();
        List<Pais> listaPais = this.paisWS.findAllPais();

        List<Participacion> listaParticipacion = this.participacionWS.findFullAllParticipacion();
        mav.addObject("listado", listaParticipacion);
        mav.addObject("paises", listaPais);
        mav.addObject("estados", listaEstados);

        mav.addObject("tituloPagina", "Participacion");
        mav.addObject("subtituloPagina", "Listado de Participacions:");
        mav.setViewName("participacion/lista");
        return mav;
    }

    @RequestMapping(value = {"participacion/guardar.htm"}, method = RequestMethod.POST)
    public void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
     /*   participacionWS.createParticipacion(
                ((BigDecimal) session.getAttribute("id_alumno")).intValueExact(),
                Integer.parseInt(request.getParameter("idFamilia")),
                1,
                Integer.parseInt(request.getParameter("idParticipacion"))
        );*/

        response.sendRedirect("/java.web/home.htm");

    }
    
    @RequestMapping(value = {"participacion/ver.htm"}, method = RequestMethod.POST)
    public ModelAndView ver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ModelAndView mav = new ModelAndView();

        Participacion par = participacionWS.findFullParticipacion(Integer.parseInt(request.getParameter("idParticipacion")));
       
        mav.addObject("participacion", par);
        mav.setViewName("participacion/ver");
        return mav;
    }
    
    @RequestMapping(value = {"participacion/estado.htm"}, method = RequestMethod.POST)
    public ModelAndView estado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ModelAndView mav = new ModelAndView();
        List<EstadoParticipacion> listaEstados = new EstadoParticipacionWS_Service().getEstadoParticipacionWSPort().findAllEstadoParticipacion();
        Participacion par = participacionWS.findFullParticipacion(Integer.parseInt(request.getParameter("idParticipacion")));
       
        mav.addObject("participacion", par);
        mav.addObject("listaEstados", listaEstados);
        mav.setViewName("participacion/estado");
        return mav;
    }
    
    @RequestMapping(value = {"participacion/cambiarEstado.htm"}, method = RequestMethod.POST)
    public void cambiarEstado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        System.out.println(session.getAttribute("id_usuario") + " " + session.getAttribute("userSession"));
        
        participacionWS.cambiarEstadoParticipacion(
                Integer.parseInt(request.getParameter("idParticipacion")),
                Integer.parseInt(request.getParameter("idEstado")),
                request.getParameter("revision"),
                ((BigDecimal) session.getAttribute("id_usuario")).intValueExact());
       response.sendRedirect("/java.web/home.htm");
    }
}
