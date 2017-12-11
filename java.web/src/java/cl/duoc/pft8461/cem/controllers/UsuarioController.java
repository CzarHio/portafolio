/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;


import cl.duoc.pft8461.cem.entidades.UsuarioEntity;
import cl.duoc.pft8461.cem.utilities.HashPwd;
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
import cl.duoc.pft8461.cem.ws.PerfilUsuario;
import cl.duoc.pft8461.cem.ws.PerfilUsuarioWS_Service;
import cl.duoc.pft8461.cem.ws.Usuario;
import cl.duoc.pft8461.cem.ws.UsuarioWS;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Joe
 */
@Controller
@SessionAttributes
public class UsuarioController extends BaseController {

    private final UsuarioWS usuarioWS = new UsuarioWS_Service().getUsuarioWSPort();
    private List<PerfilUsuario> perfilesUsuario;

    public UsuarioController() {
        PerfilUsuarioWS_Service perfilUsuarioWS = new PerfilUsuarioWS_Service();
        this.perfilesUsuario = perfilUsuarioWS.getPerfilUsuarioWSPort().findAllPerfilUsuario();
        
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
    @RequestMapping(value = {"usuario/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        
        List<Usuario> listaUsuario = this.usuarioWS.findAllUsuarios();
        mav.addObject("listado", listaUsuario);
        mav.addObject("fotos", this.fotos);
        mav.addObject("perfilesUsuario", this.perfilesUsuario);

        mav.addObject("tituloPagina", "Usuario");
        mav.addObject("subtituloPagina", "Listado de Usuarios:");
        mav.setViewName("usuarioLista");
        return mav;
    }

    @RequestMapping(value = {"usuario/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        UsuarioEntity usr = new UsuarioEntity(this.usuarioWS.findUsuario(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", usr.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"usuario/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.usuarioWS.removeUsuario(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"usuario/guardar.htm"}, method = RequestMethod.POST)
    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            if (this.isEmpty(request.getParameter("idUsuario"))) {
                this.usuarioWS.createUsuario(
                    request.getParameter("usuario"),
                    HashPwd.getHash(request.getParameter("clave")),
                    request.getParameter("nombre"),
                    request.getParameter("apellidoPat"),
                    request.getParameter("apellidoMat"),
                    request.getParameter("email"),
                    Integer.parseInt(request.getParameter("idPerfilUsuario")));
            } else {
                this.usuarioWS.editUsuario(
                        Integer.parseInt(request.getParameter("idUsuario")),
                        request.getParameter("usuario"),
                        request.getParameter("nombre"),
                        request.getParameter("apellidoPat"),
                        request.getParameter("apellidoMat"),
                        request.getParameter("email"),
                        Integer.parseInt(request.getParameter("idPerfilUsuario")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
    
    @RequestMapping(value = {"usuario/imagen.htm"},headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
    public void imagen(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        UsuarioEntity usrSession = (UsuarioEntity) request.getSession().getAttribute("usuario");
        try {
            if (!file.isEmpty()) {
                String tipo = this.pm.get("TIPO_USUARIO");
                String id = request.getParameter("id");
                String fileName = "user_" + id + "." + file.getContentType().split("/")[1];
                String path = request.getSession().getServletContext().getRealPath("/resources/") + this.pm.get("FILES_PATH") + fileName;
                String web_path = this.pm.get("APP_HOST") + this.pm.get("WEB_PATH") + fileName;
                
                if (this.saveFile(file.getBytes(), path)) {
                    if (!this.fotos.containsKey(id.concat(tipo))) {
                        this.fotoWS.createFoto(tipo, usrSession.getIdUsuario().intValue(), web_path, 1, Integer.parseInt(id), "Foto Perfil", "");
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
        response.sendRedirect("/java.web/usuario/lista.htm");
        
    }
}