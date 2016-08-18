/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.skeleton;

import cchat.core.services.IMensageiro;
import java.net.Socket;


public class msgSkeleton implements Runnable{

    private Socket socket;
    IMensageiro mensageiro;
    
    public msgSkeleton(Socket socket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
