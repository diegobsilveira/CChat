/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core;

import cchat.core.DAO.impl.SessaoDAO;
import cchat.core.skeleton.msgSkeleton;
import cchat.core.util.exception.PersistenciaException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class CChatCore {

    public static void main(String args[]) throws IOException {

        ServerSocket server = null;
        try {

            Thread m = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            SessaoDAO sessaoDAO = new SessaoDAO();
                            sessaoDAO.refreshList();
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
    }
}
