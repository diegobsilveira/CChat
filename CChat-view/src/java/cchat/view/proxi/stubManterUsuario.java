/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.view.proxi;

import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterUsuario;
import cchat.common.util.AbstractInOut;
import cchat.common.util.Request;
import cchat.common.util.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nome
 */
public class stubManterUsuario implements IManterUsuario, Runnable {

    private Sessao user;
    private Socket socket;
    private String host = "localhost";
    private int port = 2223;

    public stubManterUsuario(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Response Logar(Sessao user) {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.LOGAR);
            out.writeObject(user);
            out.flush();
            Response resposta = (Response) in.readObject();
            if(resposta == Response.SUCCESS)
                this.user = user;
            return resposta;
        } catch (IOException | ClassNotFoundException ex) {
            return Response.FAILURE;
        }
    }

    @Override
    public Response upToDate(Sessao user) {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.UPTODATE);
            out.writeObject(user);
            out.flush();
            return (Response) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return Response.FAILURE;
        }
    }
    
    @Override
    public ArrayList<String> listarUsuarios() {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.LISTAR_USUARIOS);
            out.flush();
            ArrayList<String> resposta = (ArrayList<String>) in.readObject();
            return resposta;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    public void run() {
        try {
            if(user != null){
                while(true){
                    this.upToDate(user);
                    Thread.sleep(3000);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(stubManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}