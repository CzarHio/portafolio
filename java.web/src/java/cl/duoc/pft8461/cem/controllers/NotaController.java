/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.NotaEntity;
import cl.duoc.pft8461.cem.ws.Nota;
import cl.duoc.pft8461.cem.ws.NotaWS;
import cl.duoc.pft8461.cem.ws.NotaWS_Service;
import cl.duoc.pft8461.cem.ws.Postulacion;
import cl.duoc.pft8461.cem.ws.PostulacionWS;
import cl.duoc.pft8461.cem.ws.PostulacionWS_Service;
import cl.duoc.pft8461.cem.ws.Usuario;
import cl.duoc.pft8461.cem.ws.UsuarioWS;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
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
public class NotaController extends BaseController {
    
    private final NotaWS notaWS = new NotaWS_Service().getNotaWSPort();
    private final UsuarioWS usuarioWS = new UsuarioWS_Service().getUsuarioWSPort();
    private final PostulacionWS postulacionWS = new PostulacionWS_Service().getPostulacionWSPort();
    private List<Postulacion> listaPostulacion;
    private Map<Integer, Postulacion> postulaciones;
    private Map<Integer, NotaEntity> notas;
    private List<Usuario> listaUsuario = this.usuarioWS.findUsuarioPor("ID_PERFIL_USUARIO", "4");
    private Map<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();
    
    public NotaController() {
        for (Usuario usuario : this.listaUsuario) {
            this.usuarios.put(usuario.getIdUsuario().intValue(), usuario);
        }
    }
    
    @RequestMapping(value = {"notas/lista.htm"}, method = RequestMethod.POST)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        this.postulaciones = new HashMap<Integer, Postulacion>();
        
        this.listaPostulacion = this.postulacionWS.findFullAllPostulacion();
        
        for (Postulacion postulacion : this.listaPostulacion) {
            this.postulaciones.put(postulacion.getIdPostulacion().intValue(), postulacion);
        }
        
        NotaEntity notaEntity;
        List<Nota> listaNotas = this.notaWS.findNotaPor("id_curso", request.getParameter("id"));
        
        List<NotaEntity> _notas = new ArrayList<>();
                
        for (Nota nota : listaNotas) {
            notaEntity = new NotaEntity();
            notaEntity.setIdNota(nota.getIdNota());
            notaEntity.setIdCurso(nota.getIdCurso());
            notaEntity.setIdPostulacion(nota.getIdPostulacion());
            notaEntity.setNota(nota.getNota());
            notaEntity.setNombreAlumno(postulaciones.get(nota.getIdPostulacion()).getNombreAlumno());
            notaEntity.setIdAlumno(postulaciones.get(nota.getIdPostulacion()).getIdAlumno());
            
            _notas.add(notaEntity);
        }
        
        JSONArray json = new JSONArray(_notas);
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"notas/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        NotaEntity nota = new NotaEntity(this.notaWS.findNota(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", nota.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"notas/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (this.isEmpty(request.getParameter("idNota"))) {
                this.notaWS.createNota(
                    Integer.parseInt(request.getParameter("idPostulacion")),
                    Integer.parseInt(request.getParameter("idCurso")),
                    Integer.parseInt(request.getParameter("nota")));
            } else {
                this.notaWS.editNota(
                    Integer.parseInt(request.getParameter("idNota")),
                    Integer.parseInt(request.getParameter("idPostulacion")),
                    Integer.parseInt(request.getParameter("idCurso")),
                    Integer.parseInt(request.getParameter("nota")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
}
