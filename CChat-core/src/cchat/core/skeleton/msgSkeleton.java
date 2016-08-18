/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.skeleton;

import cchat.common.model.domain.IDestinatario;
import cchat.common.model.domain.IMensagem;
import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Usuario;
import cchat.common.services.ICredenciar;
import cchat.common.services.IMensageiro;
import cchat.common.util.AbstractInOut;
import cchat.common.util.Request;
import cchat.common.util.Response;
import cchat.core.services.impl.Credenciar;
import cchat.core.services.impl.Mensageiro;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class msgSkeleton implements Runnable {

    private Socket socket;

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
            command = (Request) reader.readObject();
            
            Usuario user;
            Grupo group;
            Mensagem msg;
            ICredenciar credenciar = new Credenciar();
            IMensageiro mensageiro = new Mensageiro();
            switch (command) {
                case LOGAR:
                    user = (Usuario) reader.readObject();
                    user.setSocket(this.getSocket());
                    if (credenciar.Logar(user)) {
                        writer.writeObject(Response.SUCCESS);
                    } else {
                        writer.writeObject(Response.FAILURE);
                    }
                    writer.flush();
                    break;
                case CRIAR_GRUPO:
                    group = (Grupo) reader.readObject();
                    if (credenciar.criarGrupo(group)) {
                        writer.writeObject(Response.SUCCESS);
                    } else {
                        writer.writeObject(Response.FAILURE);
                    }
                    writer.flush();
                    break;
                case CONVIDAR_PARA_GRUPO:
                    group = (Grupo) reader.readObject();
                    if (credenciar.convidar(group)) {
                        writer.writeObject(Response.SUCCESS);
                    } else {
                        writer.writeObject(Response.FAILURE);
                    }
                    writer.flush();
                    break;
                case REMOVER_DO_GRUPO:
                    group = (Grupo) reader.readObject();
                    if (credenciar.sairGrupo(group)) {
                        writer.writeObject(Response.SUCCESS);
                    } else {
                        writer.writeObject(Response.FAILURE);
                    }
                    writer.flush();
                    break;
                case ENVIAR_MENSAGEM:
                    msg = (Mensagem) reader.readObject();
                    mensageiro.send(msg);
                    break;
                case RECEBER_MENSAGEM:
                    user = (Usuario) reader.readObject();
                    writer.writeObject(mensageiro.get(user));
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
