/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.common.services;

import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Nome
 */
public interface IMensageiro extends Remote{
    public void send(Mensagem msg) throws RemoteException;
    public ArrayList<Mensagem> get(Sessao user) throws RemoteException;
}
