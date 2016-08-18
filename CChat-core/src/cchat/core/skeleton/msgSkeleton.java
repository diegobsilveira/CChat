/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.skeleton;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Usuario;
import cchat.common.services.IMensageiro;
import cchat.common.util.AbstractInOut;
import cchat.common.util.Request;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class msgSkeleton implements Runnable{

    private Socket socket;
    IMensageiro mensageiro;
    
    public msgSkeleton(Socket socket) {
        
    }
    
    private Socket getSocket() {
        return this.socket;        
    }    
    

    public void process() throws ClassNotFoundException {
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {

            writer = AbstractInOut.getObjectWriter(this.getSocket());
            reader = AbstractInOut.getObjectReader(this.getSocket());            
            
            Request command;
            command = (Request)reader.readObject();
            
            Usuario user;
            Grupo group;
            Mensagem msg;
            switch(command) {
                case LOGAR:
                    user = (Usuario)reader.readObject();
                    break;                    
                case CRIAR_GRUPO:
                    group = (Grupo)reader.readObject();
                    break;
                case CONVIDAR_PARA_GRUPO:
                    group = (Grupo)reader.readObject();
                    break;
                case REMOVER_DO_GRUPO:
                    group = (Grupo)reader.readObject();
                    break;   
                case ENVIAR_MENSAGEM: 
                    msg = (Mensagem)reader.readObject();
                    
                    break;
            }
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(msgSkeleton.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    @Override
    public void run() {
        try {
            this.process();
            this.getSocket().close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(msgSkeleton.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
    
}
