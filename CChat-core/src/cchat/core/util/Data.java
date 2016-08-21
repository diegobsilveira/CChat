/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.util;

import cchat.common.model.domain.impl.Sessao;
import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Mensagem;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Aluno
 */
public class Data {

    private static ArrayList<Sessao> users = new ArrayList<>();
    private static ArrayList<Grupo> groups = new ArrayList<>();
    private static ArrayList<ArrayList<Mensagem>> msgs = new ArrayList<>();

    private Data() {

    }

    public static synchronized boolean addUsers(Sessao user) {
        boolean encontrado = false;
        Iterator itr = users.iterator();
        while (itr.hasNext()) {
            if (((Sessao) itr.next()).equals(user)) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            users.add(user);
            return true;
        } else {
            return false;
        }
    }

    public static synchronized boolean addGroups(Grupo group) {
        boolean encontrado = false;
        Iterator itr = groups.iterator();
        while (itr.hasNext()) {
            if (((Grupo) itr.next()).equals(group)) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            groups.add(group);
            return true;
        } else {
            return false;
        }
    }

    public static synchronized boolean addToGroup(Grupo group) {
        Grupo grupo = null;
        Iterator itr = groups.iterator();
        while (itr.hasNext()) {
            if (((Grupo) itr.next()).equals(group)) {
                grupo = (Grupo) itr;
            }
        }
        if (grupo != null) {
            itr = group.getDestinos().iterator();
            while (itr.hasNext()) {
                itr.next();
                grupo.getDestinos().add((Sessao) itr);
            }
            return true;
        } else {
            return false;
        }
    }

    public static synchronized boolean removeFromGroups(Grupo group) {
        Grupo grupo = null;
        Iterator itr = groups.iterator();
        while (itr.hasNext()) {
            if (((Grupo) itr.next()).equals(group)) {
                grupo = (Grupo) itr;
            }
        }
        if (grupo != null) {
            grupo.getDestinos().remove(group.getDestinos().get(0));
            return true;
        } else {
            return false;
        }
    }

    public static synchronized boolean removeUsers(Sessao user) {
        Sessao usuario = null;
        Iterator itr = users.iterator();
        while (itr.hasNext()) {
            if (((Sessao) itr.next()).equals(user)) {
                usuario = (Sessao) itr;
            }
        }
        if (usuario != null) {
            users.remove(usuario);
            return true;
        } else {
            return false;
        }
    }

    public static synchronized void addMsgs(Mensagem msg) {
        ArrayList<Mensagem> mensagem = null;
        Iterator itr = msgs.iterator();
        while (itr.hasNext()) {
            if (((ArrayList<Mensagem>) itr.next()).get(0).getDestino().equals(msg.getDestino())) {
                mensagem = (ArrayList<Mensagem>) itr;
            }
        }
        if (mensagem != null) {
            mensagem.add(msg);
        } else {
            if (msg.getDestino() instanceof Sessao) {
                boolean encontrado = false;
                Iterator iterator = users.iterator();
                while (iterator.hasNext()) {
                    if (((Sessao) iterator.next()).equals((Sessao) msg.getDestino())) {
                        encontrado = true;
                    }
                }
                if (encontrado) {
                    ArrayList<Mensagem> novoArray = new ArrayList<Mensagem>();
                    novoArray.add(msg);
                    msgs.add(novoArray);
                }
            } else {
            }
        }
    }

    public static synchronized Mensagem removeMsg(Sessao user) {
        ArrayList<Mensagem> mensagem = null;
        Iterator itr = msgs.iterator();
        while (itr.hasNext()) {
            if (((Sessao) ((ArrayList<Mensagem>) itr.next()).get(0).getDestino()).equals(user)) {
                mensagem = (ArrayList<Mensagem>) itr;
            }
        }
        if (mensagem != null) {
            Mensagem temp = mensagem.get(0);
            mensagem.remove(0);
            return temp;
        }else{
            return null;
        }
    }
    
    public static synchronized boolean updateUser(Sessao user) {
        Sessao usuario = null;
        Iterator itr = users.iterator();
        while (itr.hasNext()) {
            if (((Sessao) itr.next()).equals(user)) {
                usuario = (Sessao) itr;
            }
        }
        if (usuario != null) {
            usuario.setLastAccess(new Date());
            return true;
        } else {
            return false;
        }
    }
    
    public static synchronized void refreshUserList(Sessao user) {
        Sessao usuario = null;
        Iterator itr = users.iterator();
        while (itr.hasNext()) {
            if (((Sessao) itr.next()).equals(user)) {
                usuario = (Sessao) itr;
            }
        }
    }
}
