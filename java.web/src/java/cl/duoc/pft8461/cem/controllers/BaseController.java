/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

import cl.duoc.pft8461.cem.utilities.PropertiesManager;
import cl.duoc.pft8461.cem.ws.Foto;
import cl.duoc.pft8461.cem.ws.FotoWS;
import cl.duoc.pft8461.cem.ws.FotoWS_Service;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joe
 */
public class BaseController {
    
    protected final FotoWS fotoWS = new FotoWS_Service().getFotoWSPort();
    protected List<Foto> listaFotos = this.fotoWS.findAllFoto();
    protected Map<String, Foto> fotos = new HashMap<String, Foto>();
    protected final static String FILES_PATH = "\\dist\\img\\app\\";
    protected final static String WEB_PATH = "/java.web/resources/dist/img/app/";
    protected final static String TIPO_USUARIO = "1";
    protected PropertiesManager pm = new PropertiesManager();
    
    protected boolean isEmpty(String data) {
         return data == null || data.equals("0") || data.equals("");
    }
    
    protected boolean saveFile(byte[] content, String path) {
        boolean saved = false;
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path), false));
            stream.write(content);
            stream.close();
            saved = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return saved;
    }
    
    protected void reloadFotos() {
        this.listaFotos = this.fotoWS.findAllFoto();
        for (Foto foto : this.listaFotos) {
            this.fotos.put(foto.getIdInstancia().toString() + foto.getTipo().toString(), foto);
        }
    }
    
}
