/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.ws.Alumno;
import cl.duoc.pft8461.cem.ws.AlumnoWS;
import cl.duoc.pft8461.cem.ws.AlumnoWS_Service;
import cl.duoc.pft8461.cem.ws.Curso;
import cl.duoc.pft8461.cem.ws.CursoWS;
import cl.duoc.pft8461.cem.ws.CursoWS_Service;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Joe-Xidu
 */
public class AlumnoController extends BaseController {
    
    private final AlumnoWS alumnoWS = new AlumnoWS_Service().getAlumnoWSPort();
    private final CursoWS cursoWS = new CursoWS_Service().getCursoWSPort();

    public AlumnoController() {
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
    @RequestMapping(value = {"alumno/calificaciones.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        Integer alumno = null;
        
        if ((Integer) session.getAttribute("perfil") != 4) {
            try {
                alumno = Integer.parseInt(request.getParameter("alumno"));
            } catch (Exception e) {}

            
            List<Alumno> alumnos = this.alumnoWS.findFullAllAlumno();

            mav.addObject("alumnos", alumnos);
        } else {
            alumno = (Integer) session.getAttribute("id_alumno");
        }
        
        if (alumno != null) {
            List<Curso> cursos = this.cursoWS.findFullCursoPor("p.id_alumno", alumno.toString());
            mav.addObject("cursos", cursos);
        }
        
        mav.addObject("tituloPagina", "Alumno");
        mav.addObject("subtituloPagina", "Calificaciones:");
        mav.setViewName("alumno/lista");
        return mav;
    }
}
