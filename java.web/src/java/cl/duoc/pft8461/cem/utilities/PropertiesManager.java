/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Joe
 */
public class PropertiesManager {
    private String result = "";
    private InputStream inputStream;
    
    /**
     *
     * @param key
     * @return
     * @throws IOException
     */
    public String getProperty(String key) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "resources/config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Archivo de propiedades '" + propFileName + "' no fue encontrado en el directorio especificado.");
            }

            result = prop.getProperty(key);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
