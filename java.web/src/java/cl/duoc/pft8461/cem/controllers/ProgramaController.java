/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;


import cl.duoc.pft8461.cem.entidades.ProgramaEntity;
import cl.duoc.pft8461.cem.entidades.UsuarioEntity;
import cl.duoc.pft8461.cem.ws.EstadoPrograma;
import cl.duoc.pft8461.cem.ws.EstadoProgramaWS;
import cl.duoc.pft8461.cem.ws.EstadoProgramaWS_Service;
import cl.duoc.pft8461.cem.ws.Familia;
import cl.duoc.pft8461.cem.ws.FamiliaWS_Service;
import cl.duoc.pft8461.cem.ws.Foto;
import cl.duoc.pft8461.cem.ws.Pais;
import cl.duoc.pft8461.cem.ws.PaisWS;
import cl.duoc.pft8461.cem.ws.PaisWS_Service;
import cl.duoc.pft8461.cem.ws.Participacion;
import cl.duoc.pft8461.cem.ws.ParticipacionWS;
import cl.duoc.pft8461.cem.ws.ParticipacionWS_Service;
import java.io.IOException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author Joe
 */
@Controller
@SessionAttributes
public class ProgramaController extends BaseController {

    private final ProgramaWS programaWS = new ProgramaWS_Service().getProgramaWSPort();
    private final PaisWS paisWS = new PaisWS_Service().getPaisWSPort();
    private final ParticipacionWS participacionWS = new ParticipacionWS_Service().getParticipacionWSPort();
    private final EstadoProgramaWS estadoProgramaWS = new EstadoProgramaWS_Service().getEstadoProgramaWSPort();
    private Map<Integer, Pais> paises = new HashMap<Integer, Pais>();
    private List<Pais> listaPais = this.paisWS.findAllPais();

    public ProgramaController() {
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
    @RequestMapping(value = {"programa/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        
        List<EstadoPrograma> listaEstados = this.estadoProgramaWS.findAllEstadoPrograma();
        List<Pais> listaPais = this.paisWS.findAllPais();
        
        List<Programa> listaPrograma = this.programaWS.findFullAllPrograma();
        mav.addObject("listado", listaPrograma);
        mav.addObject("paises", listaPais);
        mav.addObject("estados", listaEstados);

        mav.addObject("tituloPagina", "Programa");
        mav.addObject("subtituloPagina", "Listado de Programas:");
        mav.setViewName("programa/lista");
        return mav;
    }
    
    @RequestMapping(value = {"programa/unirse.htm"}, method = RequestMethod.GET)
    public ModelAndView unirse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        List<Programa> listaPrograma = this.programaWS.findAllPrograma();
        
        mav.addObject("listado", listaPrograma);
        mav.addObject("paises", this.paises);

        mav.addObject("tituloPagina", "Unirse a Programa");
        mav.addObject("subtituloPagina", "Seleccione Programas:");
        mav.setViewName("programa/unirse");
        return mav;
    }
    
    @RequestMapping(value = {"programa/postulacion.htm"}, method = RequestMethod.GET)
    public ModelAndView postulacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        List<Programa> listaPrograma = this.programaWS.findAllPrograma();
        
        mav.addObject("listado", listaPrograma);
        mav.addObject("paises", this.paises);

        mav.addObject("tituloPagina", "Postular a Programa");
        mav.addObject("subtituloPagina", "Seleccione Programas:");
        mav.setViewName("programa/postulacion");
        return mav;
    }

    @RequestMapping(value = {"programa/editar.htm"}, method = RequestMethod.POST)
    public ModelAndView editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        ProgramaEntity pro = new ProgramaEntity(this.programaWS.findPrograma(Integer.parseInt(request.getParameter("id"))));
        mav.addObject("json", pro.toJson());
        mav.setViewName("include/json");
        
        return mav;
    }

    @RequestMapping(value = {"programa/borrar.htm"}, method = RequestMethod.POST)
    public ModelAndView borrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        try {
            json = "{\"response\": 1}";
            this.programaWS.removePrograma(Integer.parseInt(request.getParameter("id")));
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
            if (this.isEmpty(request.getParameter("idPrograma"))) {
                this.programaWS.createPrograma(
                    Integer.parseInt(request.getParameter("idEstado")),
                    request.getParameter("nombrePrograma"),
                    Integer.parseInt(request.getParameter("idPais")),
                    Integer.parseInt(request.getParameter("idTipoPrograma")),
                    Integer.parseInt(request.getParameter("maxcupos")),
                    Integer.parseInt(request.getParameter("mincupos")));
            } else {
                this.programaWS.editPrograma(
                    Integer.parseInt(request.getParameter("idPrograma")),
                    Integer.parseInt(request.getParameter("idEstado")),
                    request.getParameter("nombrePrograma"),
                    Integer.parseInt(request.getParameter("idPais")),
                    Integer.parseInt(request.getParameter("idTipoPrograma")),
                    Integer.parseInt(request.getParameter("maxcupos")),
                    Integer.parseInt(request.getParameter("mincupos")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;

    }
    
    @RequestMapping(value = {"programa/ver.htm"}, method = RequestMethod.POST)
    public ModelAndView ver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        Participacion par = new  ParticipacionWS_Service().getParticipacionWSPort().findFullParticipacion(Integer.parseInt(request.getParameter("idParticipacion")));
        Programa pro = new  ProgramaWS_Service().getProgramaWSPort().findFullPrograma(par.getIdPrograma());
        
        mav.addObject("participacion", par);
        mav.addObject("programa", pro);
        mav.setViewName("programa/ver");
        
        return mav;

    }
    
     @RequestMapping(value = {"programa/resumen.htm"}, method = RequestMethod.POST)
    public ModelAndView resumen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        Familia fam = new  FamiliaWS_Service().getFamiliaWSPort().findFullFamilia(Integer.parseInt(request.getParameter("idFamilia")));
        Participacion par = new  ParticipacionWS_Service().getParticipacionWSPort().findFullParticipacion(Integer.parseInt(request.getParameter("idParticipacion")));
        
       // mav.addObject("participacion", par);
        mav.addObject("familia", fam);
        mav.addObject("participacion", par);
        mav.setViewName("programa/resumen");
        
        return mav;

    }

    @RequestMapping(value = {"programa/findImagen.htm"}, method = RequestMethod.POST)
    public ModelAndView findImagen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"data\": {";
        
        String id = request.getParameter("id");
        String tipo = this.pm.get("TIPO_PROGRAMA");
        
        if (this.fotos.containsKey(id.concat(tipo))) {
            Foto foto = this.fotos.get(id.concat(tipo));
            json += "\"src\": \"" + foto.getNombreArchivo() + "\"";
        }
        
        json += "}}";
        mav.addObject("json", json);
        mav.setViewName("include/json");
        
        return mav;
    }
    
    @RequestMapping(value = {"programa/imagen.htm"},headers = "Content-Type=multipart/form-data", method = RequestMethod.POST)
    public void imagen(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        UsuarioEntity usrSession = (UsuarioEntity) request.getSession().getAttribute("usuario");
        try {
            if (!file.isEmpty()) {
                String tipo = this.pm.get("TIPO_PROGRAMA");
                String id = request.getParameter("id");
                String fileName = "programa_" + id + "." + file.getContentType().split("/")[1];
                String path = request.getSession().getServletContext().getRealPath("/resources/") + this.pm.get("FILES_PATH") + fileName;
                String web_path = this.pm.get("APP_HOST") + this.pm.get("WEB_PATH") + fileName;
                
                if (this.saveFile(file.getBytes(), path)) {
                    if (!this.fotos.containsKey(id.concat(tipo))) {
                        this.fotoWS.createFoto(tipo, usrSession.getIdUsuario().intValue(), web_path, 1, Integer.parseInt(id), "Foto Programa", "");
                    } else {
                        this.fotoWS.editFoto(tipo, this.fotos.get(id.concat(tipo)).getIdFoto().intValue(), usrSession.getIdUsuario().intValue(), web_path, 1, Integer.parseInt(id), "Foto Programa", "");
                    }
                } else
                    mav.addObject("error", "Ocurrió un error al intentar subir el archivo. Intente nuevamente.");
            }
        } catch (Exception e) {
            mav.addObject("error", "Ocurrió un error al intentar subir el archivo. Intente nuevamente.");
            System.out.println(e);
        }
        
        this.reloadFotos();
        response.sendRedirect("/java.web/programa/lista.htm");
        
    }
}
