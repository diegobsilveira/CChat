/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.controller;

import cchat.common.model.domain.*;
import cchat.common.model.domain.impl.*;
import cchat.common.services.*;
import cchat.view.proxi.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aluno
 */
public class adicionarAoGrupo {
    public static void execute(HttpServletRequest request) {
        String host = "localhost";
        int port = 2223;
        
        try {                        
            IManterGrupo mg = new stubManterGrupo(host,port);
            Sessao user = new Sessao();
            Grupo group = new Grupo();
            System.out.println("USER -> "+request.getParameter("user"));
            System.out.println("GRUPO -> "+request.getParameter("grupo"));
            user.setNome(request.getParameter("user"));
            group.setNome(request.getParameter("grupo"));
            System.out.println("ADD "+user.getNome()+" TO "+group.getNome()+" -> "+mg.adicionar(group, user));
            
        } catch (Exception e) {
        }        
    }
}
