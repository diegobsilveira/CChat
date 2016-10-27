/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.common.services;

import cchat.common.model.domain.impl.Sessao;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public interface IManterUsuario extends Remote{

    public boolean Logar(Sessao user) throws RemoteException;
    
    public boolean upToDate(Sessao user) throws RemoteException; 
    
    public ArrayList<String> listarUsuarios() throws RemoteException; 
}
