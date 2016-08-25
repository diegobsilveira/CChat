/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.skeleton;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterGrupo;
import cchat.common.services.IManterUsuario;
import cchat.common.services.IMensageiro;
import cchat.common.util.AbstractInOut;
import cchat.common.util.Request;
import cchat.core.services.impl.ManterGrupo;
import cchat.core.services.impl.ManterUsuario;
import cchat.core.services.impl.Mensageiro;
import cchat.core.util.Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class msgSkeleton implements Runnable {

    private Socket socket;

    public msgSkeleton(Socket socket) {
        this.socket = socket;
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
            Sessao user;
            Grupo group;
            Mensagem msg;
            IManterUsuario manterUsuario = new ManterUsuario();
            IManterGrupo manterGrupo = new ManterGrupo();
            IMensageiro mensageiro = new Mensageiro();
            switch (command) {
                case LOGAR:
                    user = (Sessao) reader.readObject();
                    user.setLastAccess(new Date());
                    writer.writeBoolean(manterUsuario.Logar(user));
                    writer.flush();
                    break;
                case CRIAR_GRUPO:
                    group = (Grupo) reader.readObject();
                    if (manterGrupo.criarGrupo(group)) {
                        writer.writeBoolean(true);
                    } else {
                        writer.writeBoolean(false);
                    }
                    writer.flush();
                    break;
                case CONVIDAR_PARA_GRUPO:
                    group = (Grupo) reader.readObject();
                    if (manterGrupo.convidar(group)) {
                        writer.writeBoolean(true);
                    } else {
                        writer.writeBoolean(false);
                    }
                    writer.flush();
                    break;
                case REMOVER_DO_GRUPO:
                    group = (Grupo) reader.readObject();
                    if (manterGrupo.sairGrupo(group)) {
                        writer.writeBoolean(true);
                    } else {
                        writer.writeBoolean(false);
                    }
                    writer.flush();
                    break;
                case ENVIAR_MENSAGEM:
                    msg = (Mensagem) reader.readObject();
                    mensageiro.send(msg);
                    break;
                case RECEBER_MENSAGEM:
                    user = (Sessao) reader.readObject();
                    writer.writeObject(mensageiro.get(user));
                    writer.flush();
                    break;
                case UPTODATE:
                    user = (Sessao) reader.readObject();
                    manterUsuario.upToDate(user);
                    break;
                case LISTAR_USUARIOS:
                    writer.writeObject(Data.getUserList());
                    writer.flush();
                    break;
                case LISTAR_GRUPOS:
                    writer.writeObject(Data.getGroupList());
                    writer.flush();
                    break;
            }
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
            
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(msgSkeleton.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

}
