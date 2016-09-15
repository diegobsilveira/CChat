/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.view.proxi;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterGrupo;
import cchat.common.util.AbstractInOut;
import cchat.common.util.Request;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Nome
 */
public class stubManterGrupo implements IManterGrupo{
    
    private Socket socket;
    private String host = "localhost";
    private int port = 2223;

    public stubManterGrupo() {
    }
    
    public stubManterGrupo(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    @Override
    public boolean criarGrupo(Grupo group) {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.CRIAR_GRUPO);
            out.writeObject(group);
            out.flush();
            return in.readBoolean();
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean adicionar(Grupo group, Sessao user) {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.CONVIDAR_PARA_GRUPO);
            out.writeObject(group);
            out.writeObject(user);
            out.flush();
            return in.readBoolean();
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean sairGrupo(Grupo group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> listarGrupos() {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.LISTAR_GRUPOS);
            out.flush();
            ArrayList<String> resposta = (ArrayList<String>) in.readObject();
            return resposta;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

}
