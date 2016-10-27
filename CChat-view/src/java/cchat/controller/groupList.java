/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.controller;

import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterGrupo;
import cchat.view.proxi.stubManterGrupo;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aluno
 */
class groupList {
    public static ArrayList<String> execute(HttpServletRequest request) {      
        try {  
            Sessao user = (Sessao)request.getSession().getAttribute("user");
            IManterGrupo manter = new stubManterGrupo();
            ArrayList<String> users = manter.listarGruposDoUsuario(user);
            return users;          
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return null;        
    }
}
