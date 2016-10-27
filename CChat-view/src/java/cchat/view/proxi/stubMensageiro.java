/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.view.proxi;

import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IMensageiro;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 *
 * @author Nome
 */
public class stubMensageiro implements IMensageiro{

    Registry registry;
    IMensageiro mensageiro;

    public stubMensageiro() {
        try{
            registry = LocateRegistry.getRegistry("localhost",2345);
            mensageiro = (IMensageiro) registry.lookup("mensageiro");
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    @Override
    public void send(Mensagem msg) throws RemoteException {
        mensageiro.send(msg);
    }
    
    @Override
    public ArrayList<Mensagem> get(Sessao user) throws RemoteException {
        return mensageiro.get(user);
    }
}
