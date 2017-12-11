/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.ws.EstadoPostulacion;
import cl.duoc.pft8461.cem.ws.EstadoPostulacionWS;
import cl.duoc.pft8461.cem.ws.EstadoPostulacionWS_Service;
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
import cl.duoc.pft8461.cem.ws.Postulacion;
import cl.duoc.pft8461.cem.ws.PostulacionWS;
import cl.duoc.pft8461.cem.ws.PostulacionWS_Service;
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
public class PostulacionController extends BaseController {

    private final PostulacionWS postulacionWS = new PostulacionWS_Service().getPostulacionWSPort();
    private final PaisWS paisWS = new PaisWS_Service().getPaisWSPort();
    private final EstadoPostulacionWS estadoPostulacionWS = new EstadoPostulacionWS_Service().getEstadoPostulacionWSPort();
    private Map<Integer, Pais> paises = new HashMap<Integer, Pais>();
    private List<Pais> listaPais = this.paisWS.findAllPais();

    public PostulacionController() {
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
    @RequestMapping(value = {"postulacion/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ModelAndView mav = new ModelAndView();

        List<EstadoPostulacion> listaEstados = this.estadoPostulacionWS.findAllEstadoPostulacion();
        List<Pais> listaPais = this.paisWS.findAllPais();

        List<Postulacion> listaPostulacion = this.postulacionWS.findFullAllPostulacion();
        mav.addObject("listado", listaPostulacion);
        mav.addObject("paises", listaPais);
        mav.addObject("estados", listaEstados);

        mav.addObject("tituloPagina", "Postulacion");
        mav.addObject("subtituloPagina", "Listado de Postulacions:");
        mav.setViewName("postulacion/lista");
        return mav;
    }

    @RequestMapping(value = {"postulacion/guardar.htm"}, method = RequestMethod.POST)
    public void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        postulacionWS.createPostulacion(
                ((BigDecimal) session.getAttribute("id_alumno")).intValueExact(),
                Integer.parseInt(request.getParameter("idFamilia")),
                1,
                Integer.parseInt(request.getParameter("idParticipacion"))
        );

        response.sendRedirect("/java.web/home.htm");

    }

    @RequestMapping(value = {"postulacion/ver.htm"}, method = RequestMethod.POST)
    public ModelAndView ver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        Postulacion pos = postulacionWS.findFullPostulacion(Integer.parseInt(request.getParameter("idPostulacion")));

        mav.addObject("postulacion", pos);

        mav.setViewName("postulacion/ver");
        return mav;
    }
}
