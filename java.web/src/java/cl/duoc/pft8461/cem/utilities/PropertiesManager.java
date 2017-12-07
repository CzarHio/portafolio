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
    private InputStream inputStream;
    private final String propFileName = "resources/config.properties";
    private Properties prop = new Properties();
    
    /**
     *
     * @param key
     * @return
     * @throws java.io.IOException
     */
    public String get(String key) throws IOException {
        
        String result = "";

        this.inputStream = getClass().getClassLoader().getResourceAsStream(this.propFileName);

        try {
            if (this.inputStream != null) {
                this.prop.load(this.inputStream);
            } else {
                throw new FileNotFoundException("Archivo de propiedades '" + this.propFileName + "' no fue encontrado en el directorio especificado.");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        
        if (this.prop.containsKey(key))
            result = this.prop.getProperty(key);

        return result;
    }
}
