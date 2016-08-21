/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.view.proxi;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterUsuario;
import cchat.common.util.AbstractInOut;
import cchat.common.util.Request;
import cchat.common.util.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nome
 */
public class stubManterUsuario implements IManterUsuario, Runnable{
    
    public stubManterUsuario(String serverAddress, int serverPort) {
    }

    @Override
    public boolean Logar(Sessao user) {
        try {
            ObjectInputStream in = AbstractInOut.getObjectReader(Connection.connect());
            ObjectOutputStream out = AbstractInOut.getObjectWriter(Connection.connect());
            out.writeObject(Request.LOGAR);
            out.flush();
            out.writeObject(user);
            out.flush();
            switch((Response)in.readObject()){
                case SUCCESS:
                    return true;
                case FAILURE:
                    return false;
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(stubManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean upToDate(Sessao user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
