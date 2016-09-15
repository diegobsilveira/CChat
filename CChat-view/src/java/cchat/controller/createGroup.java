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
import javax.persistence.PersistenceException;
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
            System.out.println(request.getParameter("grupo"));
            if(!request.getParameter("grupo").matches("\"")){
                IManterGrupo manter = new stubManterGrupo(host,port);
                ArrayList<Sessao> destinos = new ArrayList<>();
                destinos.add((Sessao) request.getSession().getAttribute("user"));
                Grupo grupo = new Grupo();
                grupo.setDestinos(destinos);
                grupo.setNome(request.getParameter("grupo"));
                if(!manter.criarGrupo(grupo)){
                    throw new PersistenceException("Não foi possivel criar esse grupo");
                }
                Sessao user = (Sessao) request.getSession().getAttribute("user");   
                System.out.println("ADD "+user.getNome()+" TO "+grupo.getNome()+" -> "+manter.adicionar(grupo, user));
            }  
            else{
                throw new PersistenceException("Não é permitido criar um grupo com o caractere \"");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return null;        
    }
}
