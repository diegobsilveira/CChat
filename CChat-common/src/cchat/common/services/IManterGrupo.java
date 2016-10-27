/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.common.services;

import cchat.common.model.domain.impl.*;
import java.rmi.Remote;
import java.util.ArrayList;
import java.rmi.RemoteException;

/**
 *
 * @author Nome
 */
public interface IManterGrupo extends Remote{
    
    public boolean adicionar(Grupo group, Sessao user) throws RemoteException;

    public boolean criarGrupo(Grupo group) throws RemoteException;
    
    public boolean sairGrupo(Grupo group) throws RemoteException;
    
    public ArrayList<String> listarGrupos() throws RemoteException;
    public ArrayList<String> listarGruposDoUsuario(Sessao user) throws RemoteException;
}
