/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.view.proxi;

import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IMensageiro;

/**
 *
 * @author Nome
 */
public class stubMensageiro implements IMensageiro, Runnable{

    private String serverAddress;
    private int serverPort;
    
    public stubMensageiro(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;        
    }
    
    @Override
    public void send(Mensagem msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Mensagem get(Sessao user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
