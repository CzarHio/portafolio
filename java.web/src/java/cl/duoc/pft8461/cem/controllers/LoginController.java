/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.UsuarioEntity;
import cl.duoc.pft8461.cem.utilities.HashPwd;
import cl.duoc.pft8461.cem.utilities.Mail;
import cl.duoc.pft8461.cem.ws.Alumno;
import cl.duoc.pft8461.cem.ws.AlumnoWS_Service;
import cl.duoc.pft8461.cem.ws.Carrera;
import cl.duoc.pft8461.cem.ws.CarreraWS;
import cl.duoc.pft8461.cem.ws.CarreraWS_Service;
import cl.duoc.pft8461.cem.ws.Centro;
import cl.duoc.pft8461.cem.ws.CentroWS;
import cl.duoc.pft8461.cem.ws.CentroWS_Service;
import cl.duoc.pft8461.cem.ws.Familia;
import cl.duoc.pft8461.cem.ws.FamiliaWS_Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joe
 */

@Controller
@SessionAttributes
public class LoginController {
    
    private final UsuarioWS_Service ws = new UsuarioWS_Service();
    
    public LoginController() {
    }
    
    /**
     * Método form, que carga el formulario de login
     * de la aplicación del Sistema CEM.
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @RequestMapping(value = {"login.htm"}, method = RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        //this.sendMail("");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        System.out.println(timeStamp);
        
        mav.setViewName("login");
        return mav;
    }
    
    /**
     * Método postLogin
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @RequestMapping(value = {"postLogin.htm"}, method = RequestMethod.POST)
    public ModelAndView postLogin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        ModelAndView mav = new ModelAndView();
        
        try {
            UsuarioEntity usr = new UsuarioEntity(this.ws.getUsuarioWSPort().autenticar(request.getParameter("login"), HashPwd.getHash(request.getParameter("password"))));

            if(usr != null){
                session.setAttribute("usuario", usr);
                session.setAttribute("logeado", "1");
                session.setAttribute("userSession", usr.getNombre() + " " + usr.getApellidoPat() + " " + usr.getApellidoMat());
                session.setAttribute("perfil", usr.getIdPerfilUsuario());
                //session.setAttribute("since", usr.getCreado().toString());
                session.setAttribute("id_usuario", usr.getIdUsuario());
                switch (usr.getIdPerfilUsuario()) {
                    case 3:
                        List<Centro> listCentro = new CentroWS_Service().getCentroWSPort().findCentroPor("id_usuario", usr.getIdUsuario().toString());
                        session.setAttribute("id_centro", listCentro.get(0).getIdCentro());
                        break;
                    case 4:
                        List<Alumno> listAlumno = new AlumnoWS_Service().getAlumnoWSPort().findAlumnoPor("id_usuario", usr.getIdUsuario().toString());
                        session.setAttribute("id_alumno", listAlumno.get(0).getIdAlumno());
                        break;
                    case 5:
                        List<Familia> listFamilia = new FamiliaWS_Service().getFamiliaWSPort().findFamiliaPor("id_usuario", usr.getIdUsuario().toString());
                        session.setAttribute("id_familia", listFamilia.get(0).getIdFamilia());
                        break;
                }
                response.sendRedirect("./home.htm");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mav.addObject("error", "Usuario o Contraseña no válidos.");
        mav.setViewName("login");
        return mav;
    }
    
    /**
     * Método logout
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @RequestMapping(value = {"salir.htm"}, method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("./login.htm");
    }
    
    /**
     * Método register
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @RequestMapping(value = {"registrarse.htm"}, method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        
        CentroWS centroWS = new CentroWS_Service().getCentroWSPort();
        CarreraWS carreraWS = new CarreraWS_Service().getCarreraWSPort();
        List<Centro> listaCentro = centroWS.findAllCentro();
        List<Carrera> listaCarrera = carreraWS.findAllCarrera();
        mav.addObject("listaCentro", listaCentro);
        mav.addObject("listaCarrera", listaCarrera);
                    
        mav.setViewName("register");
        return mav;
    }
    
    /**
     * Método postRegister
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @RequestMapping(value = {"registro.htm"}, method = RequestMethod.POST)
    public ModelAndView postRegister(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, Exception {
        
        ModelAndView mav = new ModelAndView();
        
        if (request.getParameter("pass").equals(request.getParameter("pass1"))) {
            
            String usuario = request.getParameter("login");
            String clave = HashPwd.getHash(request.getParameter("pass"));
            String email = request.getParameter("email");
            String nombre = request.getParameter("nombre");
            String apellidoPat = request.getParameter("apellidoPat");
            String apellidoMat = request.getParameter("apellidoMat");
            
            this.ws.getUsuarioWSPort().createUsuario(usuario, clave, nombre, apellidoPat, apellidoMat, email, 0);
            
            response.sendRedirect("./login.htm");
            return null;
            
        } else {
        
            mav.addObject("error", "Contraseñas no coinciden.");
            mav.setViewName("register");
            return mav;
            
        }
        
    }
    
    @RequestMapping(value = {"registro_alumno.htm"}, method = RequestMethod.POST)
    public ModelAndView postRegisterAlumno(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        ModelAndView mav = new ModelAndView();
        
        if (request.getParameter("pass").equals(request.getParameter("pass1"))) {
            
            try {
                String usuario = request.getParameter("login");
                String clave = HashPwd.getHash(request.getParameter("pass"));
                String email = request.getParameter("email");
                String nombre = request.getParameter("nombre");
                String apellidoPat = request.getParameter("apellidoPat");
                String apellidoMat = request.getParameter("apellidoMat");
                int idCarrera = Integer.parseInt(request.getParameter("idCarrera"));
                int semestre = Integer.parseInt(request.getParameter("semestre"));
                int ingreso = Integer.parseInt(request.getParameter("ingreso"));
                
                this.ws.getUsuarioWSPort().createUsuarioAlumno(usuario, clave, nombre, apellidoPat, apellidoMat, email, 4, idCarrera, semestre, ingreso);
                
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            response.sendRedirect("./login.htm");
            return null;
        } else {
        
            mav.addObject("error", "Contraseñas no coinciden.");
            mav.setViewName("register");
            return mav;
            
        }
        
    }
    
    @RequestMapping(value = {"registro_familia.htm"}, method = RequestMethod.POST)
    public ModelAndView postRegisterFamilia(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        ModelAndView mav = new ModelAndView();
        
        if (request.getParameter("pass").equals(request.getParameter("pass1"))) {
            
            try {
                String usuario = request.getParameter("login");
                String clave = HashPwd.getHash(request.getParameter("pass"));
                String email = request.getParameter("email");
                String nombre = request.getParameter("nombre");
                String apellidoPat = request.getParameter("apellidoPat");
                String apellidoMat = request.getParameter("apellidoMat");
                int idCentro = Integer.parseInt(request.getParameter("idCentro"));
                String nombreFamilia = request.getParameter("nombreFamilia");
                String descripcion = request.getParameter("descripcion");
                String direccion = request.getParameter("direccion");
                
                this.ws.getUsuarioWSPort().createUsuarioFamilia(usuario, clave, nombre, apellidoPat, apellidoMat, email, 5, idCentro, nombreFamilia, descripcion, direccion);
                
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            response.sendRedirect("./login.htm");
            return null;
            
        } else {
        
            mav.addObject("error", "Contraseñas no coinciden.");
            mav.setViewName("register");
            return mav;
            
        }
        
    }
    
    private boolean sendMailBienvenida(String to) {
        boolean sent = false;
        try {
            Mail mail = new Mail();
            mail.init();
            mail.from("mail@test.com");
            mail.to(to);
            mail.subject("Bienvenido al Sistema CEM");
            mail.content("Estimado,\n\nLe damos la más cordial bienvenida "
                    + "al Sistema CEM.\nAquí podrá postular a programas (si es un Alumno) "
                    + "o participar de uno como Familia.\n\nGracias por confiar en nosotros\n"
                    + "\n\n\n\n\nCentro de Estudios Montreal");
            sent = mail.send();
        } catch (Exception e) {
            System.out.println("Error mail: " + e);
        }
        
        return sent;
    }
    
}
