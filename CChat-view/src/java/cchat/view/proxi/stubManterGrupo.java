/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.view.proxi;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.services.IManterGrupo;
import cchat.common.util.AbstractInOut;
import cchat.common.util.Request;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
    public boolean convidar(Grupo group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean sairGrupo(Grupo group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
