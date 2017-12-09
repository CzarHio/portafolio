/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.CursoEntity;
import cl.duoc.pft8461.cem.utilities.PDF;
import cl.duoc.pft8461.cem.ws.Curso;
import cl.duoc.pft8461.cem.ws.CursoWS;
import cl.duoc.pft8461.cem.ws.CursoWS_Service;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
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
public class CursosController extends BaseController {
    
    private final CursoWS cursoWS = new CursoWS_Service().getCursoWSPort();
    
    public CursosController() {
    }
    
    @RequestMapping(value = {"cursos/lista.htm"}, method = RequestMethod.POST)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        List<Curso> listaCurso = this.cursoWS.findFullCursoPor("c.id_programa", request.getParameter("id"));
        JSONArray json = new JSONArray(listaCurso);
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"cursos/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        CursoEntity cur = new CursoEntity(this.cursoWS.findCurso(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", cur.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"cursos/certificado.htm"}, method = RequestMethod.POST)
    public ModelAndView certificado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        
        PDF pdf = new PDF();
        String certificado = "";
        try {
            certificado = pdf.generate(request, Integer.parseInt(request.getParameter("id")));
        } catch (DocumentException ex) {
            Logger.getLogger(CursosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mav.addObject("json", "{\"url\": \"" + certificado + "\"}");
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"cursos/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.cursoWS.removeCurso(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"cursos/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (this.isEmpty(request.getParameter("idCurso"))) {
                this.cursoWS.createCurso(
                    Integer.parseInt(request.getParameter("idPrograma")),
                    request.getParameter("nombreCurso"),
                    Integer.parseInt(request.getParameter("creditos")),
                    request.getParameter("descripcion"));
            } else {
                this.cursoWS.editCurso(
                    Integer.parseInt(request.getParameter("idCurso")),
                    Integer.parseInt(request.getParameter("idPrograma")),
                    request.getParameter("nombreCurso"),
                    Integer.parseInt(request.getParameter("creditos")),
                    request.getParameter("descripcion"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
}
