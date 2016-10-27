/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core;

import cchat.common.services.IManterGrupo;
import cchat.common.services.IManterUsuario;
import cchat.common.services.IMensageiro;
import cchat.core.DAO.impl.MensagemDAO;
import cchat.core.DAO.impl.SessaoDAO;
import cchat.core.services.impl.ManterGrupo;
import cchat.core.services.impl.ManterUsuario;
import cchat.core.services.impl.Mensageiro;
import cchat.core.skeleton.msgSkeleton;
import cchat.core.util.exception.PersistenciaException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author aluno
 */
public class CChatCore {

    public static void main(String args[]) throws IOException {
        
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());
        
        Thread m = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        SessaoDAO sessaoDAO = new SessaoDAO();
                        MensagemDAO msgDAO = new MensagemDAO();
                        sessaoDAO.refreshList();
                        msgDAO.refreshList();
                        Thread.sleep(30000);
                    }
                } catch (InterruptedException | PersistenciaException ex) {
                    Logger.getLogger(CChatCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        m.start();
        
        IManterGrupo grupo = new ManterGrupo ();
        IManterGrupo grupoStub=(IManterGrupo)UnicastRemoteObject.exportObject(grupo, 0);
        IManterUsuario usuario = new ManterUsuario();
        IManterUsuario usuarioStub=(IManterUsuario)UnicastRemoteObject.exportObject(usuario, 0);
        IMensageiro mensageiro = new Mensageiro();
        IMensageiro mensageiroStub=(IMensageiro)UnicastRemoteObject.exportObject(mensageiro, 0);
        Registry registry = LocateRegistry.createRegistry(2345);
        registry.rebind ("grupo", grupoStub);
        registry.rebind ("usuario", usuarioStub);
        registry.rebind ("mensageiro", mensageiroStub);
        
        /*
        ServerSocket server = null;
        try {

            Thread m = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            SessaoDAO sessaoDAO = new SessaoDAO();
                            MensagemDAO msgDAO = new MensagemDAO();
                            sessaoDAO.refreshList();
                            msgDAO.refreshList();
                            Thread.sleep(30000);
                        }
                    } catch (InterruptedException | PersistenciaException ex) {
                        Logger.getLogger(CChatCore.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            m.start();
            server = new ServerSocket(2223);

            while (true) {
                Socket socket = server.accept();
                msgSkeleton mensageiro = new msgSkeleton(socket);
                Thread t = new Thread(mensageiro);
                t.start();
            }
        } catch (Exception e) {
            if (server != null) {
                server.close();
            }
        }
    */
    }
}
