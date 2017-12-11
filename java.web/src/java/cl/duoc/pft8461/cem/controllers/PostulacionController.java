/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.utilities.Mail;
import cl.duoc.pft8461.cem.ws.Centro;
import cl.duoc.pft8461.cem.ws.EstadoPostulacion;
import cl.duoc.pft8461.cem.ws.EstadoPostulacionWS;
import cl.duoc.pft8461.cem.ws.EstadoPostulacionWS_Service;
import cl.duoc.pft8461.cem.ws.Pais;
import cl.duoc.pft8461.cem.ws.PaisWS;
import cl.duoc.pft8461.cem.ws.PaisWS_Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import cl.duoc.pft8461.cem.ws.Postulacion;
import cl.duoc.pft8461.cem.ws.PostulacionWS;
import cl.duoc.pft8461.cem.ws.PostulacionWS_Service;
import cl.duoc.pft8461.cem.ws.Usuario;
import cl.duoc.pft8461.cem.ws.UsuarioWS;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Joe
 */
@Controller
@SessionAttributes
public class PostulacionController extends BaseController {

    private final PostulacionWS postulacionWS = new PostulacionWS_Service().getPostulacionWSPort();
    private final UsuarioWS usuarioWS = new UsuarioWS_Service().getUsuarioWSPort();
    private final PaisWS paisWS = new PaisWS_Service().getPaisWSPort();
    private final EstadoPostulacionWS estadoPostulacionWS = new EstadoPostulacionWS_Service().getEstadoPostulacionWSPort();
    private Map<Integer, Pais> paises = new HashMap<Integer, Pais>();
    private List<Pais> listaPais = this.paisWS.findAllPais();

    public PostulacionController() {
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
    @RequestMapping(value = {"postulacion/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ModelAndView mav = new ModelAndView();

        List<EstadoPostulacion> listaEstados = this.estadoPostulacionWS.findAllEstadoPostulacion();
        List<Pais> listaPais = this.paisWS.findAllPais();

        List<Postulacion> listaPostulacion = this.postulacionWS.findFullAllPostulacion();
        mav.addObject("listado", listaPostulacion);
        mav.addObject("paises", listaPais);
        mav.addObject("estados", listaEstados);

        mav.addObject("tituloPagina", "Postulacion");
        mav.addObject("subtituloPagina", "Listado de Postulacions:");
        mav.setViewName("postulacion/lista");
        return mav;
    }

    @RequestMapping(value = {"postulacion/guardar.htm"}, method = RequestMethod.POST)
    public void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        postulacionWS.createPostulacion(
                ((BigDecimal) session.getAttribute("id_alumno")).intValueExact(),
                Integer.parseInt(request.getParameter("idFamilia")),
                1,
                Integer.parseInt(request.getParameter("idParticipacion"))
        );

        response.sendRedirect("/java.web/home.htm");

    }

    @RequestMapping(value = {"postulacion/ver.htm"}, method = RequestMethod.POST)
    public ModelAndView ver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        Postulacion pos = postulacionWS.findFullPostulacion(Integer.parseInt(request.getParameter("idPostulacion")));

        mav.addObject("postulacion", pos);

        mav.setViewName("postulacion/ver");
        return mav;
    }
    
    @RequestMapping(value = {"postulacion/estado.htm"}, method = RequestMethod.POST)
    public ModelAndView estado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        Postulacion pos = postulacionWS.findFullPostulacion(Integer.parseInt(request.getParameter("idPostulacion")));
        List<EstadoPostulacion> listaEstados = new EstadoPostulacionWS_Service().getEstadoPostulacionWSPort().findAllEstadoPostulacion();
        mav.addObject("postulacion", pos);
        mav.addObject("listaEstados", listaEstados);

        mav.setViewName("postulacion/estado");
        return mav;
    }
    
    @RequestMapping(value = {"postulacion/cambiarEstado.htm"}, method = RequestMethod.POST)
    public void cambiarEstado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        postulacionWS.cambiarEstadoPostulacion(
                Integer.parseInt(request.getParameter("idPostulacion")), 
                Integer.parseInt(request.getParameter("idEstado"))
        );
        try {
            Usuario usuario = this.usuarioWS.findUsuario(Integer.parseInt(request.getParameter("idUsuario")));
            if (!this.isEmpty(usuario.getEmail()))
                this.sendMail(usuario.getEmail());
            
        } catch (Exception e) {
            System.out.println(e);
        }
       response.sendRedirect("/java.web/home.htm");
    }
    
    private boolean sendMail(String to) {
        boolean sent = false;
        try {
            Mail mail = new Mail();
            mail.init();
            mail.from("mail@test.com");
            mail.to(to);
            mail.subject("Aceptación de Participación");
            mail.content("Estimado,\n\nEl siguiente correo tiene como fin "
                    + "informar de que ha sido aprobado su participación en el programa."
                    + "\n\nGracias por confiar en nosotros.\n"
                    + "\n\n\n\n\nCentro de Estudios Montreal.");
            sent = mail.send();
        } catch (Exception e) {
            System.out.println("Error mail: " + e);
        }
        
        return sent;
    }
}
