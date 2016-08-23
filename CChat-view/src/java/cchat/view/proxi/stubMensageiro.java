/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.view.proxi;

import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IMensageiro;
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
public class stubMensageiro implements IMensageiro{

    private Socket socket;
    private String host = "localhost";
    private int port = 2223;

    public stubMensageiro(String host, int port) {
        this.host = host;
        this.port = port;
    }
    @Override
    public void send(Mensagem msg) {
        try {
            socket = new Socket(host, port);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.ENVIAR_MENSAGEM);
            out.writeObject(msg);
            out.flush();
        } catch (IOException ex) {
            
        }
    }

    @Override
    public Mensagem get(Sessao user) {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.RECEBER_MENSAGEM);
            out.writeObject(user);
            out.flush();
            return (Mensagem) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

}
