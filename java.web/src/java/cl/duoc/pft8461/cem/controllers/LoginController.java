/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.UsuarioEntity;
import cl.duoc.pft8461.cem.utilities.HashPwd;
import cl.duoc.pft8461.cem.ws.PerfilUsuarioWS_Service;
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

/**
 *
 * @author Joe
 */

@Controller
@SessionAttributes
public class LoginController {
    
    private final UsuarioWS_Service ws = new UsuarioWS_Service();
    private final PerfilUsuarioWS_Service wsPerfil = new PerfilUsuarioWS_Service();
    
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
            System.out.println();
            // test string "asdasd"
            System.out.println("L0+h5zDT8Q==$ee0ce6c01fe5d84297de6b7c4da5c83d2bd4746874cf5c07e33d1d1da76cef5347d8f1ba686a355a7f7eed8ea78842163efe46292b7e2bdc85964f1f6c1ba951");
            System.out.println(HashPwd.check(request.getParameter("password"), "L0+h5zDT8Q==$ee0ce6c01fe5d84297de6b7c4da5c83d2bd4746874cf5c07e33d1d1da76cef5347d8f1ba686a355a7f7eed8ea78842163efe46292b7e2bdc85964f1f6c1ba951"));
            
System.out.println(this.ws.getUsuarioWSPort().autenticar(request.getParameter("login"), HashPwd.getHash(request.getParameter("password"))));
            UsuarioEntity usr = new UsuarioEntity(this.ws.getUsuarioWSPort().autenticar(request.getParameter("login"), HashPwd.getHash(request.getParameter("password"))));
            //UsuarioEntity usr = new UsuarioEntity(this.ws.getUsuarioWSPort().findUsuario(2));
            if(usr != null){
                session.setAttribute("usuario", usr);
                session.setAttribute("logeado", "1");
                session.setAttribute("userSession", usr.getNombre() + " " + usr.getApellidoPat() + " " + usr.getApellidoMat());
                session.setAttribute("perfil", usr.getIdPerfilUsuario());
                //session.setAttribute("since", usr.getCreado().toString());
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
        throws ServletException, IOException {
        //HttpSession session = request.getSession();
        ModelAndView mav = new ModelAndView();
        
        if (request.getParameter("pass").equals(request.getParameter("pass1"))) {
            
            String usuario = request.getParameter("login");
            String clave = request.getParameter("pass");
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
    
}
