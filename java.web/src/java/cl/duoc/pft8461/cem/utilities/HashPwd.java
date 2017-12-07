/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.utilities;

import java.security.MessageDigest;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Joe
 */
public class HashPwd {

    private static final int SALT_LEN = 64;

    /**
     * Genera un string plano con dos secciones, una random y la contraseña MD5
     * complementada con una APP_KEY obtenida desde las propiedades de la aplicación.
     * Contraseñas vacías no son permitidas.
     * 
     * @param password
     * @return texto plano con hash
     * @throws java.lang.Exception */
    public static String getHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(SALT_LEN);
        PropertiesManager pm = new PropertiesManager();
        
        return Base64.encodeBase64(salt) + "$" + hash(password, pm.get("APP_KEY"));
    }

    /**
     * Comprueba si la contraseña de texto sin cifrar corresponde
     * a un hash, formateado, almacenado de la contraseña.
     * 
     * @param password
     * @param stored
     * @return si las contraseñas ingresadas coinciden o no
     * @throws java.lang.Exception */
    public static boolean check(String password, String stored) throws Exception{
        String[] saltAndPass = stored.split("\\$");
        PropertiesManager pm = new PropertiesManager();
        
        if (saltAndPass.length != 2) {
            throw new IllegalStateException("The stored password have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, pm.get("APP_KEY"));
        return hashOfInput.equals(saltAndPass[1]);
    }
    
    /**
     * Genera el hashe MD5 de la contraseña.
     * 
     * @param password
     * @param key
     * @return string plano con hash */
    private static String hash(String password, String key) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update((password + key).getBytes());
        byte[] b = md.digest();
        StringBuilder sb = new StringBuilder();
        String hex;
        
        for (int i=0;i<b.length;i++) {
            hex = Integer.toHexString(0xFF & b[i]);
            if(hex.length() == 1)
                sb.append('0');

            sb.append(hex);
        }
        
        return sb.toString();
    }
}
