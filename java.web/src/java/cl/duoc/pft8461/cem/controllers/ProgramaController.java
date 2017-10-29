/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;


import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        mav.addObject("listado", listaPrograma);;

        mav.addObject("tituloPagina", "Programa");
        mav.addObject("subtituloPagina", "Listado de Programas:");
        mav.setViewName("programaLista");
        return mav;
    }

    @RequestMapping(value = {"programa/nuevo.htm"}, method = RequestMethod.GET)
    public ModelAndView nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("programaForm");

        return mav;
    }

    @RequestMapping(value = {"programa/editar.htm"}, method = RequestMethod.GET)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        Programa usr = programaWS.findPrograma(Integer.parseInt(request.getParameter("id")));
        mav.addObject("usr", usr);

        mav.setViewName("programaForm");

        return mav;
    }

    @RequestMapping(value = {"programa/borrar.htm"}, method = RequestMethod.GET)
    public ModelAndView borrarGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        Programa usr = programaWS.findPrograma(Integer.parseInt(request.getParameter("id")));
        mav.addObject("usr", usr);
        mav.setViewName("programaBorrar");
        return mav;
    }

    @RequestMapping(value = {"programa/borrar.htm"}, method = RequestMethod.POST)
    public void borrarPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        programaWS.removePrograma(Integer.parseInt(request.getParameter("inputIdPrograma")));

        response.sendRedirect("lista.htm");;
    }

    @RequestMapping(value = {"programa/guardar.htm"}, method = RequestMethod.POST)
    public void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      /*  if (request.getParameter("inputIdPrograma") == null) {
            programaWS.createPrograma(
                    request.getParameter("inputPrograma"),
                    request.getParameter("inputClave"),
                    request.getParameter("inputNombre"),
                    request.getParameter("inputApellidoPat"),
                    request.getParameter("inputApellidoMat"),
                    request.getParameter("inputEmail"),
                    Integer.parseInt(request.getParameter("inputPerfilPrograma")));
        } else {
            programaWS.editPrograma(
                    Integer.parseInt(request.getParameter("inputIdPrograma")),
                    request.getParameter("inputPrograma"),
                    request.getParameter("inputNombre"),
                    request.getParameter("inputApellidoPat"),
                    request.getParameter("inputApellidoMat"),
                    request.getParameter("inputEmail"),
                    Integer.parseInt(request.getParameter("inputPerfilPrograma")));
        }*/
        response.sendRedirect("lista.htm");

    }

}
