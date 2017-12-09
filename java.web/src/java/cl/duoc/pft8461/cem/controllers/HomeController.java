/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.ws.Documento;
import cl.duoc.pft8461.cem.ws.DocumentoWS;
import cl.duoc.pft8461.cem.ws.DocumentoWS_Service;
import cl.duoc.pft8461.cem.ws.Familia;
import cl.duoc.pft8461.cem.ws.FamiliaWS;
import cl.duoc.pft8461.cem.ws.FamiliaWS_Service;
import cl.duoc.pft8461.cem.ws.Participacion;
import cl.duoc.pft8461.cem.ws.ParticipacionWS;
import cl.duoc.pft8461.cem.ws.ParticipacionWS_Service;
import cl.duoc.pft8461.cem.ws.Postulacion;
import cl.duoc.pft8461.cem.ws.PostulacionWS;
import cl.duoc.pft8461.cem.ws.PostulacionWS_Service;
import cl.duoc.pft8461.cem.ws.Usuario;
import cl.duoc.pft8461.cem.ws.UsuarioWS;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Joe
 */
@Controller
@SessionAttributes
public class HomeController {

    private final UsuarioWS usuarioWS = new UsuarioWS_Service().getUsuarioWSPort();
    private final ParticipacionWS participacionWS = new ParticipacionWS_Service().getParticipacionWSPort();
    private final PostulacionWS postulacionWS = new PostulacionWS_Service().getPostulacionWSPort();
    private final FamiliaWS familiaWS = new FamiliaWS_Service().getFamiliaWSPort();
    private final DocumentoWS documentoWS = new DocumentoWS_Service().getDocumentoWSPort();
    
    public HomeController() {
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
    @RequestMapping(value = {"home.htm"}, method = RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        List<Participacion> listaParticipacion;
        List<Usuario> listaUsuario;
        List<Postulacion> listaPostulacion;
        List<Familia> listaFamilia;
        List<Documento> listaDocumento;
        
        switch ((Integer) session.getAttribute("perfil")) {
            case 1:
                mav.setViewName("home/home_admin");
                listaUsuario = this.usuarioWS.findUsuarioPor("id_perfil_usuario", "4");
                listaParticipacion = this.participacionWS.findFullParticipacionPor("p.id_estado", "1");
                listaPostulacion = this.postulacionWS.findPostulacionFullPor("p.id_estado", "1");
                listaFamilia = this.familiaWS.findAllFamilia();
                mav.addObject("listaUsuario", listaUsuario);
                mav.addObject("listaParticipacion", listaParticipacion);
                mav.addObject("listaPostulacion", listaPostulacion);
                mav.addObject("listaFamilia", listaFamilia);
                break;
            case 2:
                listaParticipacion = this.participacionWS.findFullParticipacionPor("p.id_estado", "1");
                listaPostulacion = this.postulacionWS.findPostulacionFullPor("p.id_estado", "1");
                listaUsuario = this.usuarioWS.findUsuarioPor("id_perfil_usuario", "4");     
                mav.addObject("listaParticipacion", listaParticipacion);
                mav.addObject("listaPostulacion", listaPostulacion);
                mav.addObject("listaUsuario", listaUsuario);
                
                mav.setViewName("home/home_cem");
                break;
            case 3:
                BigDecimal idCentro = (BigDecimal) session.getAttribute("id_centro");
                listaParticipacion = this.participacionWS.findFullParticipacionPor("p.id_centro", idCentro.toString());
                listaFamilia = this.familiaWS.findAllFamilia();
                mav.addObject("listaParticipacion", listaParticipacion);
                mav.addObject("listaFamilia", listaFamilia);
                mav.setViewName("home/home_cel");
                break;
            case 4:
                BigDecimal idAlumno = (BigDecimal) session.getAttribute("id_alumno");
                listaPostulacion = this.postulacionWS.findPostulacionFullPor("al.id_alumno", idAlumno.toString());
                listaParticipacion = this.participacionWS.findFullParticipacionPor("p.id_estado", "2");
                mav.addObject("listaParticipacion", listaParticipacion);
                mav.addObject("listaPostulacion", listaPostulacion);
                mav.setViewName("home/home_alumno");
                break;
            case 5:
                BigDecimal idFamilia = (BigDecimal) session.getAttribute("id_familia");
                listaPostulacion = this.postulacionWS.findPostulacionFullPor("f.id_familia", idFamilia.toString());
                listaDocumento = this.documentoWS.findDocumentoPor("id_familia", idFamilia.toString());
                mav.addObject("listaDocumento", listaDocumento);
                mav.addObject("listaPostulacion", listaPostulacion);
                mav.setViewName("home/home_familia");
                break;
            default:
                mav.setViewName("home/home");
                break;
        };

        return mav;

    }

}
