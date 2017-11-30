/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;


import cl.duoc.pft8461.cem.entidades.ProgramaEntity;
import cl.duoc.pft8461.cem.ws.EstadoPrograma;
import cl.duoc.pft8461.cem.ws.EstadoProgramaWS;
import cl.duoc.pft8461.cem.ws.EstadoProgramaWS_Service;
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
import cl.duoc.pft8461.cem.ws.Programa;
import cl.duoc.pft8461.cem.ws.ProgramaWS;
import cl.duoc.pft8461.cem.ws.ProgramaWS_Service;


/**
 *
 * @author Joe
 */
@Controller
@SessionAttributes
public class ProgramaController {

    private final ProgramaWS programaWS = new ProgramaWS_Service().getProgramaWSPort();
    private final PaisWS paisWS = new PaisWS_Service().getPaisWSPort();
    private final EstadoProgramaWS estadoProgramaWS = new EstadoProgramaWS_Service().getEstadoProgramaWSPort();

    public ProgramaController() {

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
    @RequestMapping(value = {"programa/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        List<Programa> listaPrograma = programaWS.findAllPrograma();
        List<Pais> listaPais = paisWS.findAllPais();
        List<EstadoPrograma> listaEstadoPrograma = estadoProgramaWS.findAllEstadoPrograma();
        mav.addObject("listado", listaPrograma);
        mav.addObject("paises", listaPais);
        mav.addObject("estados", listaEstadoPrograma);

        mav.addObject("tituloPagina", "Programa");
        mav.addObject("subtituloPagina", "Listado de Programas:");
        mav.setViewName("programaLista");
        return mav;
    }

    @RequestMapping(value = {"programa/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        ProgramaEntity pro = new ProgramaEntity(programaWS.findPrograma(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", pro.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"programa/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrarGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            programaWS.removePrograma(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"programa/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (request.getParameter("idPrograma") == null) {
                programaWS.createPrograma(
                    Integer.parseInt(request.getParameter("idEstado")),
                    request.getParameter("nombrePrograma"),
                    request.getParameter("idPais"));
            } else {
                programaWS.editPrograma(
                    Integer.parseInt(request.getParameter("idPrograma")),
                    Integer.parseInt(request.getParameter("idEstado")),
                    request.getParameter("nombrePrograma"),
                    request.getParameter("idPais"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }

}
