/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.CentroEntity;
import cl.duoc.pft8461.cem.entidades.UsuarioEntity;
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
import cl.duoc.pft8461.cem.ws.Centro;
import cl.duoc.pft8461.cem.ws.CentroWS;
import cl.duoc.pft8461.cem.ws.CentroWS_Service;
import cl.duoc.pft8461.cem.ws.Ciudad;
import cl.duoc.pft8461.cem.ws.CiudadWS;
import cl.duoc.pft8461.cem.ws.CiudadWS_Service;
import cl.duoc.pft8461.cem.ws.Foto;
import cl.duoc.pft8461.cem.ws.Usuario;
import cl.duoc.pft8461.cem.ws.UsuarioWS;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Joe
 */
@Controller
@SessionAttributes
public class CentroController extends BaseController {

    private final CentroWS centroWS = new CentroWS_Service().getCentroWSPort();
    private final UsuarioWS usuarioWS = new UsuarioWS_Service().getUsuarioWSPort();
    private List<Usuario> listaUsuario = this.usuarioWS.findUsuarioPor("ID_PERFIL_USUARIO", "3");
    private Map<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();
    private final CiudadWS ciudadWS = new CiudadWS_Service().getCiudadWSPort();
    private List<Ciudad> listaCiudad = this.ciudadWS.findAllCiudad();
    private Map<Integer, Ciudad> ciudades = new HashMap<Integer, Ciudad>();

    public CentroController() {
        for (Ciudad ciudad : this.listaCiudad) {
            this.ciudades.put(ciudad.getIdCiudad().intValue(), ciudad);
        }
        for (Usuario usuario : this.listaUsuario) {
            this.usuarios.put(usuario.getIdUsuario().intValue(), usuario);
        }
        this.reloadFotos();
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
    @RequestMapping(value = {"centro/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        
        List<Centro> listaCentro = this.centroWS.findAllCentro();
        
        mav.addObject("usuarios", this.usuarios);
        mav.addObject("ciudades", this.ciudades);
        mav.addObject("listado", listaCentro);
        mav.addObject("tituloPagina", "Centro");
        mav.addObject("subtituloPagina", "Listado de Centros:");
        mav.setViewName("centroLista");
        return mav;
    }

    @RequestMapping(value = {"centro/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        CentroEntity centro = new CentroEntity(this.centroWS.findCentro(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", centro.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"centro/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrarPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.centroWS.removeCentro(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"centro/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (this.isEmpty(request.getParameter("idCentro"))) {
                this.centroWS.createCentro(
                        request.getParameter("nombreCentro"),
                        Integer.parseInt(request.getParameter("idUsuario")),
                        Integer.parseInt(request.getParameter("idCiudad")));
            } else {
                this.centroWS.editCentro(
                        Integer.parseInt(request.getParameter("idCentro")),
                        request.getParameter("nombreCentro"),
                        Integer.parseInt(request.getParameter("idUsuario")),
                        Integer.parseInt(request.getParameter("idCiudad")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }

    @RequestMapping(value = {"centro/findImagen.htm"}, method = RequestMethod.POST)
    public ModelAndView findImagen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"data\": {";
        
        String id = request.getParameter("id");
        String tipo = this.pm.getProperty("TIPO_CENTRO");
        
        if (this.fotos.containsKey(id.concat(tipo))) {
            Foto foto = this.fotos.get(id.concat(tipo));
            json += "\"src\": \"" + foto.getNombreArchivo() + "\"";
        }
        
        json += "}}";
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }
    
    @RequestMapping(value = {"centro/imagen.htm"},headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
    public void imagen(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        UsuarioEntity usrSession = (UsuarioEntity) request.getSession().getAttribute("usuario");
        try {
            if (!file.isEmpty()) {
                String tipo = this.pm.getProperty("TIPO_CENTRO");
                String id = request.getParameter("id");
                String fileName = "centro_" + id + "." + file.getContentType().split("/")[1];
                String path = request.getSession().getServletContext().getRealPath("/resources/") + this.pm.getProperty("FILES_PATH") + fileName;
                String web_path = this.pm.getProperty("WEB_PATH") + fileName;
                
                if (this.saveFile(file.getBytes(), path)) {
                    if (!this.fotos.containsKey(id.concat(tipo))) {
                        this.fotoWS.createFoto(tipo, usrSession.getIdUsuario().intValue(), web_path, 1, Integer.parseInt(id), "Foto Centro", "");
                    } else {
                        this.fotoWS.editFoto(tipo, this.fotos.get(id.concat(tipo)).getIdFoto().intValue(), usrSession.getIdUsuario().intValue(), web_path, 1, Integer.parseInt(id), "Foto Perfil", "");
                    }
                } else
                    mav.addObject("error", "Ocurrió un error al intentar subir el archivo. Intente nuevamente.");
            }
        } catch (Exception e) {
            mav.addObject("error", "Ocurrió un error al intentar subir el archivo. Intente nuevamente.");
            System.out.println(e);
        }
        
        this.reloadFotos();
        response.sendRedirect("/java.web/centro/lista.htm");
        
    }

}
