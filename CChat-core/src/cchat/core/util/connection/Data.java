/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.util.connection;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
import java.util.ArrayList;

/**
 *
 * @author Nome
 */
public class Data {
    
    private static ArrayList<Sessao> users = new ArrayList<>();
    private static ArrayList<Grupo> groups = new ArrayList<>();
    private static ArrayList<Mensagem> msgs = new ArrayList<>();
    private static Data instance = null;
    
    private Data (){
        Grupo geral = new Grupo();
        geral.setNome("GERAL");
        groups.add(geral);
    }
    
    public static Data getInstance(){
        if(Data.instance == null){
            instance = new Data();
        }
        return instance;
    }

    public synchronized ArrayList<Sessao> getUsers() {
        return users;
    }

    public synchronized ArrayList<Grupo> getGroups() {
        return groups;
    }

    public synchronized ArrayList<Mensagem> getMsgs() {
        return msgs;
    }
    
}