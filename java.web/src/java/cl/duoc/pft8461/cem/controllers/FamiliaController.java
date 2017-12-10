/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.DocumentoEntity;
import cl.duoc.pft8461.cem.entidades.FamiliaEntity;
import cl.duoc.pft8461.cem.ws.Centro;
import cl.duoc.pft8461.cem.ws.CentroWS;
import cl.duoc.pft8461.cem.ws.CentroWS_Service;
import cl.duoc.pft8461.cem.ws.Documento;
import cl.duoc.pft8461.cem.ws.DocumentoWS;
import cl.duoc.pft8461.cem.ws.DocumentoWS_Service;
import cl.duoc.pft8461.cem.ws.EstadoDocumento;
import cl.duoc.pft8461.cem.ws.EstadoDocumentoWS;
import cl.duoc.pft8461.cem.ws.EstadoDocumentoWS_Service;
import cl.duoc.pft8461.cem.ws.EstadoFamilia;
import cl.duoc.pft8461.cem.ws.EstadoFamiliaWS;
import cl.duoc.pft8461.cem.ws.EstadoFamiliaWS_Service;
import cl.duoc.pft8461.cem.ws.Familia;
import cl.duoc.pft8461.cem.ws.FamiliaWS;
import cl.duoc.pft8461.cem.ws.FamiliaWS_Service;
import cl.duoc.pft8461.cem.ws.TipoDocumento;
import cl.duoc.pft8461.cem.ws.TipoDocumentoWS;
import cl.duoc.pft8461.cem.ws.TipoDocumentoWS_Service;
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
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
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
    private final DocumentoWS documentoWS = new DocumentoWS_Service().getDocumentoWSPort();
    private final TipoDocumentoWS tipoDocumentoWS = new TipoDocumentoWS_Service().getTipoDocumentoWSPort();
    private final EstadoDocumentoWS estadoDocumentoWS = new EstadoDocumentoWS_Service().getEstadoDocumentoWSPort();
    private List<EstadoDocumento> listaEstadoDoc = this.estadoDocumentoWS.findAllEstadoDocumento();
    private List<TipoDocumento> listaTipoDoc = this.tipoDocumentoWS.findAllTipoDocumento();
    private Map<Integer, Centro> centros = new HashMap<Integer, Centro>();
    private List<Centro> listaCentro = this.centroWS.findAllCentro();
    private Map<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();
    private List<Usuario> listaUsuario = this.usuarioWS.findUsuarioPor("ID_PERFIL_USUARIO", "5");
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
        mav.addObject("listaTipoDoc", this.listaTipoDoc);
        mav.addObject("listaEstadoDoc", this.listaEstadoDoc);
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
                    Integer.parseInt(request.getParameter("idEstado")),
                    request.getParameter("direccion"),
                    request.getParameter("nombreFamilia"),
                    request.getParameter("descripcion"));
            } else {
                this.familiaWS.editFamilia(
                    Integer.parseInt(request.getParameter("idFamilia")),
                    Integer.parseInt(request.getParameter("idUsuario")),
                    Integer.parseInt(request.getParameter("idCentro")),
                    Integer.parseInt(request.getParameter("idEstado")),
                    request.getParameter("direccion"),
                    request.getParameter("nombreFamilia"),
                    request.getParameter("descripcion"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
    
    @RequestMapping(value = {"familia/selfam.htm"}, method = RequestMethod.POST)
    public ModelAndView selfam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        List<Familia> listadoFamilia = new FamiliaWS_Service().getFamiliaWSPort().findFamiliaPor("id_centro", request.getParameter("idCentro"));

        mav.addObject("ListadoFamilia", listadoFamilia);
        mav.setViewName("familia/setfam");
        
        return mav;

    }
    
    @RequestMapping(value = {"familia/archivos.htm"}, method = RequestMethod.POST)
    public ModelAndView archivos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
    
        List<Documento> listaDocumento = this.documentoWS.findDocumentoPor("id_familia", request.getParameter("id"));
        JSONArray json = new JSONArray(listaDocumento);
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }
    
    @RequestMapping(value = {"familia/upload.htm"}, method = RequestMethod.POST)
    public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        
        try {
            if (!file.isEmpty()) {
                String fileName = "familia_" + request.getParameter("idFamiliaDoc") + "_" + request.getParameter("titulo") + "." + file.getContentType().split("/")[1];
                String path = request.getSession().getServletContext().getRealPath("/resources/") + this.pm.get("FILES_PATH") + fileName;
                String web_path = this.pm.get("WEB_PATH") + fileName;
                
                if (this.saveFile(file.getBytes(), path)) {
                    System.out.println(web_path);
                    if (this.isEmpty(request.getParameter("idDocumento"))) {
                        this.documentoWS.createDocumento(
                            web_path,
                            Integer.parseInt(request.getParameter("idFamiliaDoc")),
                            request.getParameter("titulo"),
                            Integer.parseInt(request.getParameter("idTipoDocumento")),
                            request.getParameter("descripcion"),
                            Integer.parseInt(request.getParameter("idEstado")));
                    } else {
                        this.documentoWS.editDocumento(
                            Integer.parseInt(request.getParameter("idDocumento")),
                            web_path,
                            Integer.parseInt(request.getParameter("idFamiliaDoc")),
                            request.getParameter("titulo"),
                            Integer.parseInt(request.getParameter("idTipoDocumento")),
                            request.getParameter("descripcion"),
                            Integer.parseInt(request.getParameter("idEstado")));
                    }

                } else
                    mav.addObject("error", "Ocurrió un error al intentar subir el archivo. Intente nuevamente.");
            }
        } catch (Exception e) {
            mav.addObject("error", "Ocurrió un error al intentar subir el archivo. Intente nuevamente.");
            System.out.println(e);
        }
        
        response.sendRedirect("/java.web/familia/lista.htm");
    }

    @RequestMapping(value = {"familia/borrarDocumento.htm"}, method = RequestMethod.POST)
    public ModelAndView borrarDocumento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.documentoWS.removeDocumento(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"familia/editarDocumento.htm"}, method = RequestMethod.POST)
    public ModelAndView editarDocumento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        DocumentoEntity doc = new DocumentoEntity(this.documentoWS.findDocumento(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", doc.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }
}
