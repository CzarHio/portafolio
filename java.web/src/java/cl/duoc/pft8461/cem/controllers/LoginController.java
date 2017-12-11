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
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Joe
 */

@Controller
@SessionAttributes
public class LoginController extends BaseController {
    
    private final UsuarioWS_Service ws = new UsuarioWS_Service();
    
    public LoginController() {
        this.reloadFotos();
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
            UsuarioEntity usr = new UsuarioEntity(this.ws.getUsuarioWSPort().autenticar(request.getParameter("login"), HashPwd.getHash(request.getParameter("password"))));

            if(usr != null){
                session.setAttribute("usuario", usr);
                session.setAttribute("logeado", "1");
                session.setAttribute("userSession", usr.getNombre() + " " + usr.getApellidoPat() + " " + usr.getApellidoMat());
                session.setAttribute("perfil", usr.getIdPerfilUsuario());
                session.setAttribute("foto", this.fotos.containsKey(usr.getIdUsuario().toString()+"1")?this.fotos.get(usr.getIdUsuario()+"1").getNombreArchivo():"/java.web/resources/dist/img/app/no_img.png");
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
     * Método recupera
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @RequestMapping(value = {"recupera.htm"}, method = RequestMethod.GET)
    public ModelAndView recupera(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String json = "{\"response\": 0}";
        
        String user = request.getParameter("user");
        String token = request.getParameter("tkn");
            
        if (!this.isEmpty(user)) {
            Calendar time = Calendar.getInstance();
            time.add(Calendar.MINUTE, 30);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(time.getTime());
            
            try {
                byte[] tkn = (timeStamp + "$" + user + "$" + HashPwd.getHash(this.pm.get("APP_KEY"))).getBytes();
                String new_token = new String(Base64.encodeBase64(tkn));
                String email = this.ws.getUsuarioWSPort().setToken(user, new_token);
                if (this.sendMailToken(email, this.pm.get("APP_HOST") + "/java.web/recupera.htm?tkn=" + new_token))
                    json = "{\"response\": 1}";
            } catch (Exception e) {
                System.out.println(e);
            }
            
            mav.addObject("json", json);
            mav.setViewName("include/json");
            
        } else {
            boolean error = true;
            
            if (!this.isEmpty(token)) {
                
                Date now = new Date();
                try {
                    String _token = new String(Base64.decodeBase64(token.getBytes(Charset.forName("UTF-8"))));
                    String[] data = _token.split("\\$");
                    Date tkn_date = new SimpleDateFormat("yyyyMMdd_HHmmss").parse(data[0]);

                    if (now.before(tkn_date)) {
                        user = data[1];
                        mav.addObject("usuario", user);
                        error = false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            if (error)
                mav.addObject("error", "Token no válido. Intente solicitando reestablecer contraseña nuevamente");
            
            mav.setViewName("recupera");

        }
        
        return mav;
    }
    
    /**
     * Método recupera
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    @RequestMapping(value = {"postRecupera.htm"}, method = RequestMethod.POST)
    public ModelAndView postRecupera(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        
        String usuario = request.getParameter("usuario");
        
        if (request.getParameter("password").equals(request.getParameter("password1"))) {
            try {
                this.ws.getUsuarioWSPort().setPwd(usuario, HashPwd.getHash(request.getParameter("password")));
                mav.addObject("success", "Contraseña reestablecida.");
            } catch (Exception ex) {
                mav.addObject("error", "Ocurrió un error al intentar reestablecer contraseña. Intente solicitando un nuevo reestablecimiento.");
                System.out.println(ex);
            }
            mav.setViewName("login");
            
        } else {
            mav.addObject("usuario", usuario);
            mav.addObject("error", "Contraseñas deben coincidir.");
            mav.setViewName("recupera");
        }
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
            
            this.sendMailBienvenida(email);
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
                
                this.sendMailBienvenida(email);
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
                
                this.sendMailBienvenida(email);
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
    
    private boolean sendMailToken(String to, String link) {
        boolean sent = false;
        try {
            Mail mail = new Mail();
            mail.init();
            mail.from("mail@test.com");
            mail.to(to);
            mail.subject("Recuperación de contraseña Sistema CEM");
            mail.content("Estimado,\n\nEl siguiente correo fue generedao a"
                    + " petición suya, para la recuperación de su contraseña.\n\n"
                    + "A continuación, puede reestablecer su contraseña, ingresando al siguiente link:"
                    + link
                    + "\n\nGracias por confiar en nosotros.\n"
                    + "\n\n\n\n\nCentro de Estudios Montreal.");
            sent = mail.send();
        } catch (Exception e) {
            System.out.println("Error mail: " + e);
        }
        
        return sent;
    }
    
}
