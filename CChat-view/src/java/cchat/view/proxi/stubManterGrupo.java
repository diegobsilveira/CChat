/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.view.proxi;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterGrupo;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 *
 * @author Nome
 */
public class stubManterGrupo implements IManterGrupo{
    
    Registry registry;
    IManterGrupo grupo;
    
    public stubManterGrupo(){
        try{
            registry = LocateRegistry.getRegistry("localhost",2345);
            grupo = (IManterGrupo) registry.lookup("grupo");
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public boolean criarGrupo(Grupo group) throws RemoteException{
        return grupo.criarGrupo(group);
    }

    @Override
    public boolean adicionar(Grupo group, Sessao user) throws RemoteException{
        return grupo.adicionar(group, user);
    }

    @Override
    public boolean sairGrupo(Grupo group) throws RemoteException{
        return grupo.sairGrupo(group);
    }

    @Override
    public ArrayList<String> listarGrupos() throws RemoteException{
        return grupo.listarGrupos();
    }

    @Override
    public ArrayList<String> listarGruposDoUsuario(Sessao user) throws RemoteException{
        return grupo.listarGruposDoUsuario(user);
    }

}
