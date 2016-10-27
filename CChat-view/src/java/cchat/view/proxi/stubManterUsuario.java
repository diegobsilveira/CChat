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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 *
 * @author Nome
 */
public class stubManterUsuario implements IManterUsuario {

    Registry registry;
    IManterUsuario usuario;

    public stubManterUsuario() {
        try{
            registry = LocateRegistry.getRegistry("localhost",2345);
            usuario = (IManterUsuario) registry.lookup("usuario");
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean Logar(Sessao user) throws RemoteException {
        return usuario.Logar(user);
    }

    @Override
    public boolean upToDate(Sessao user) throws RemoteException {
        return usuario.upToDate(user);
    }
    
    @Override
    public ArrayList<String> listarUsuarios() throws RemoteException {
        return usuario.listarUsuarios();
    }

}