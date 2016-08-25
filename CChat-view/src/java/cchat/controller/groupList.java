/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.controller;

import cchat.common.services.IManterGrupo;
import cchat.common.services.IManterUsuario;
import cchat.view.proxi.stubManterGrupo;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aluno
 */
class groupList {
    public static ArrayList<String> execute(HttpServletRequest request) {
        String host = "localhost";
        int port = 2223;
        
        try {  
            IManterGrupo manter = new stubManterGrupo(host,port);
            ArrayList<String> users = manter.listarGrupos();
            return users;          
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return null;        
    }
}
