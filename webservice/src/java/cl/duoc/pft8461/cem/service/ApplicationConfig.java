/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Czar
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(cl.duoc.pft8461.cem.service.CentroFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.CiudadFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.CursoFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.DocumentoFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.EstadoDocumentoFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.EstadoFamiliaFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.EstadoParticipacionFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.EstadoProgramaFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.FamiliaFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.MenuFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.MenuItemFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.NotaFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.PaisFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.ParticipacionFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.PerfilUsuarioFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.PostulacionFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.ProgramaFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.RegionFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.TipoDocumentoFacadeREST.class);
        resources.add(cl.duoc.pft8461.cem.service.UsuarioFacadeREST.class);
    }
    
}
