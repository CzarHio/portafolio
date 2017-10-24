/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.entidades.ArbolMenu;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import cl.duoc.pft8461.cem.ws.Menu;
import cl.duoc.pft8461.cem.ws.MenuItem;
import cl.duoc.pft8461.cem.ws.MenuItemWS_Service;
import cl.duoc.pft8461.cem.ws.MenuWS_Service;
import cl.duoc.pft8461.cem.ws.PerfilUsuario;
import cl.duoc.pft8461.cem.ws.PerfilUsuarioWS_Service;
import cl.duoc.pft8461.cem.ws.Usuario;
import cl.duoc.pft8461.cem.ws.UsuarioWS_Service;
import java.util.LinkedList;
import sun.reflect.generics.tree.Tree;

/**
 *
 * @author Joe
 */

@Controller
@SessionAttributes
public class UsuarioController {
    
    private final UsuarioWS_Service ws = new UsuarioWS_Service();
    private  List<PerfilUsuario> perfilesUsuario;
    
    public UsuarioController() {
        PerfilUsuarioWS_Service perfilUsuarioWS = new PerfilUsuarioWS_Service();
        perfilesUsuario = perfilUsuarioWS.getPerfilUsuarioWSPort().findAllPerfilUsuario();
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
    @RequestMapping(value = {"usuario/lista.htm"}, method = RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        UsuarioWS_Service usuarioWS = new UsuarioWS_Service();
        List<Usuario> listaUsuario = usuarioWS.getUsuarioWSPort().findAllUsuarios();
        mav.addObject("listado", listaUsuario);
        mav.addObject("perfilesUsuario",perfilesUsuario);
        
        mav.addObject("listaMenu", this.getMenu());
        mav.addObject("tituloPagina", "Usuario");
        mav.addObject("subtituloPagina", "Listado de Usuarios:");
        mav.setViewName("usuarioLista");
        return mav;
    }
    
    private List<ArbolMenu> getMenu(){
        MenuWS_Service menuWS = new MenuWS_Service();
        MenuItemWS_Service menuItemWS = new MenuItemWS_Service();
        List<ArbolMenu> arbol = new LinkedList<>();
        List<Menu> listaMenu = menuWS.getMenuWSPort().findMenuPor("PERFIL_USUARIO", "1");
        
        for (Menu m : listaMenu){
           ArbolMenu am = new ArbolMenu(m);
           am.setListaMenu(menuItemWS.getMenuItemWSPort().findMenuItemPor("ID_MENU", m.getIdMenu().toString()));
           arbol.add(am);
        }
        return arbol;
    }
}
