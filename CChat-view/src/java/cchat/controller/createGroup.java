/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.controller;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterGrupo;
import cchat.common.services.IManterUsuario;
import cchat.view.proxi.stubManterGrupo;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aluno
 */
class createGroup {
    public static ArrayList<String> execute(HttpServletRequest request) {
        String host = "localhost";
        int port = 2223;
        
        try {  
            System.out.println("NOME GRUPO -> "+request.getParameter("grupo"));
            IManterGrupo manter = new stubManterGrupo(host,port);
            Grupo grupo = new Grupo();
            grupo.setNome(request.getParameter("grupo"));
            boolean z = manter.criarGrupo(grupo);
            System.out.println(z);            
           
            Sessao user = (Sessao) request.getSession().getAttribute("user");   
            System.out.println("ADD "+user.getNome()+" TO "+grupo.getNome()+" -> "+manter.adicionar(grupo, user));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return null;        
    }
}
