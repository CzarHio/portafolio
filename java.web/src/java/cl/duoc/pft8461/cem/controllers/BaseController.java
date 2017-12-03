/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.controllers;

/**
 *
 * @author Joe
 */
public class BaseController {
    
    protected boolean isEmpty(String data) {
         return data == null || data.equals("0") || data.equals("");
    }
    
}
