/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.interceptors;

import cl.duoc.pft8461.cem.entidades.ArbolMenu;
import cl.duoc.pft8461.cem.ws.Menu;
import cl.duoc.pft8461.cem.ws.MenuItemWS_Service;
import cl.duoc.pft8461.cem.ws.MenuWS_Service;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Czar
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (!request.getRequestURI().equals("/java.web/login.htm")
                && !request.getRequestURI().equals("/java.web/postLogin.htm")
                && !request.getRequestURI().equals("/java.web/registro.htm")
                && !request.getRequestURI().equals("/java.web/registrarse.htm")
                && !request.getRequestURI().equals("/java.web/programa/certificado.htm")
                && !request.getRequestURI().equals("/java.web/salir.htm")) {
            if (session.getAttribute("logeado") == null || !session.getAttribute("logeado").equals("1")) {
                //whatever you want to do
                //return throw new ModelAndViewDefiningException(new ModelAndView("userLogout", "command", null));
                response.sendRedirect("/java.web/login.htm");
                return false;
            } else {
                if (session.getAttribute("menu") == null || session.getAttribute("menu").equals("")) {
                    session.setAttribute("menu", getMenu((Integer) session.getAttribute("perfil")));
                }
                return true;
            }
        }
        return true;
    }

    private List<ArbolMenu> getMenu(Integer perfil) {
        MenuWS_Service menuWS = new MenuWS_Service();
        MenuItemWS_Service menuItemWS = new MenuItemWS_Service();
        List<ArbolMenu> arbol = new LinkedList<>();
        List<Menu> listaMenu = menuWS.getMenuWSPort().findMenuPor("PERFIL_USUARIO", perfil.toString());

        for (Menu m : listaMenu) {
            ArbolMenu am = new ArbolMenu(m);
            am.setListaMenu(menuItemWS.getMenuItemWSPort().findMenuItemPor("ID_MENU", m.getIdMenu().toString()));
            arbol.add(am);
        }
        return arbol;
    }
}
