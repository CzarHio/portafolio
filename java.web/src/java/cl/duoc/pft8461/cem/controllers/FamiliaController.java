/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.FamiliaEntity;
import cl.duoc.pft8461.cem.entidades.RegionEntity;
import cl.duoc.pft8461.cem.ws.Centro;
import cl.duoc.pft8461.cem.ws.CentroWS;
import cl.duoc.pft8461.cem.ws.CentroWS_Service;
import cl.duoc.pft8461.cem.ws.EstadoFamilia;
import cl.duoc.pft8461.cem.ws.EstadoFamiliaWS;
import cl.duoc.pft8461.cem.ws.EstadoFamiliaWS_Service;
import cl.duoc.pft8461.cem.ws.Familia;
import cl.duoc.pft8461.cem.ws.FamiliaWS;
import cl.duoc.pft8461.cem.ws.FamiliaWS_Service;
import cl.duoc.pft8461.cem.ws.Pais;
import cl.duoc.pft8461.cem.ws.Usuario;
import cl.duoc.pft8461.cem.ws.UsuarioWS;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;
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
public class FamiliaController extends BaseController {
    
    private final FamiliaWS familiaWS = new FamiliaWS_Service().getFamiliaWSPort();
    private final CentroWS centroWS = new CentroWS_Service().getCentroWSPort();
    private final UsuarioWS usuarioWS = new UsuarioWS_Service().getUsuarioWSPort();
    private final EstadoFamiliaWS estadoFamiliaWS = new EstadoFamiliaWS_Service().getEstadoFamiliaWSPort();
    private Map<Integer, Centro> centros = new HashMap<Integer, Centro>();
    private List<Centro> listaCentro = this.centroWS.findAllCentro();
    private Map<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();
    private List<Usuario> listaUsuario = this.usuarioWS.findAllUsuarios();
    private Map<Integer, EstadoFamilia> estadosFamilia = new HashMap<Integer, EstadoFamilia>();
    private List<EstadoFamilia> listaEstadoFamilia = this.estadoFamiliaWS.findAllEstadoFamilia();

    public FamiliaController() {
        for (Centro centro : this.listaCentro) {
            this.centros.put(centro.getIdCentro().intValue(), centro);
        }
        for (Usuario usuario : this.listaUsuario) {
            this.usuarios.put(usuario.getIdUsuario().intValue(), usuario);
        }
        for (EstadoFamilia estadoFamilia : this.listaEstadoFamilia) {
            this.estadosFamilia.put(estadoFamilia.getIdEstado().intValue(), estadoFamilia);
        }
    }
    
    @RequestMapping(value = {"familia/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        List<Familia> listaFamilia = this.familiaWS.findAllFamilia();
        mav.addObject("listado", listaFamilia);
        mav.addObject("centros", this.centros);
        mav.addObject("usuarios", this.usuarios);
        mav.addObject("estadosFamilia", this.estadosFamilia);
        mav.addObject("tituloPagina", "Familia");
        mav.addObject("subtituloPagina", "Listado de Familias:");
        mav.setViewName("familia/lista");
        return mav;
    }

    @RequestMapping(value = {"familia/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        FamiliaEntity usr = new FamiliaEntity(this.familiaWS.findFamilia(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", usr.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"familia/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.familiaWS.removeFamilia(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"familia/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (this.isEmpty(request.getParameter("idFamilia"))) {
                this.familiaWS.createFamilia(
                    Integer.parseInt(request.getParameter("idUsuario")),
                    Integer.parseInt(request.getParameter("idCentro")),
                    Integer.parseInt(request.getParameter("idEstado")));
            } else {
                this.familiaWS.editFamilia(
                    Integer.parseInt(request.getParameter("idFamilia")),
                    Integer.parseInt(request.getParameter("idUsuario")),
                    Integer.parseInt(request.getParameter("idCentro")),
                    Integer.parseInt(request.getParameter("idEstado")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
    
}
