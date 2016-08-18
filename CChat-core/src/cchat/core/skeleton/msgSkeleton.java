/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.skeleton;

import cchat.common.model.domain.IMensagem;
import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Usuario;
import cchat.common.services.ICredenciar;
import cchat.common.util.AbstractInOut;
import cchat.common.util.Request;
import cchat.core.services.impl.Credenciar;
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
            switch (command) {
                case LOGAR:
                    user = (Usuario) reader.readObject();
                    break;
                case CRIAR_GRUPO:
                    group = (Grupo) reader.readObject();
                    break;
                case CONVIDAR_PARA_GRUPO:
                    group = (Grupo) reader.readObject();
                    break;
                case REMOVER_DO_GRUPO:
                    group = (Grupo) reader.readObject();
                    break;
                case ENVIAR_MENSAGEM:
                    msg = (Mensagem) reader.readObject();
                    send(msg);
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

    private void send(IMensagem msg) throws IOException {
        Mensagem mensagem = (Mensagem) msg;
        Usuario user;
        ObjectOutputStream writer;

        if (mensagem.getDestino() instanceof Usuario) {
            user = (Usuario) mensagem.getDestino();
            writer = AbstractInOut.getObjectWriter(user.getSocket());

            writer.writeObject(msg);
            writer.flush();
            writer.flush();
        } else if (mensagem.getDestino() instanceof Grupo) {
            Grupo group;

            group = (Grupo) mensagem.getDestino();

            Iterator itr = group.getDestinos().iterator();
            while (itr.hasNext()) {
                user = (Usuario) itr.next();
                writer = AbstractInOut.getObjectWriter(user.getSocket());
                writer.writeObject(msg);
                writer.flush();
            }
        }

    }

}
