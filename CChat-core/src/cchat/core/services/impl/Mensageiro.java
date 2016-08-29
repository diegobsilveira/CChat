/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.services.impl;

import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IMensageiro;
import cchat.core.DAO.IMensagemDAO;
import cchat.core.DAO.impl.MensagemDAO;
import cchat.core.util.exception.PersistenciaException;
import java.util.ArrayList;

/**
 *
 * @author Nome
 */
public class Mensageiro implements IMensageiro{

    @Override
    public synchronized void send(Mensagem msg) {
        try{
            IMensagemDAO mensagemDAO = new MensagemDAO();
            mensagemDAO.inserir(msg);
        }catch(PersistenciaException e){
        }
    }

    @Override
    public synchronized ArrayList<Mensagem> get(Sessao user) {
        IMensagemDAO mensagemDAO = new MensagemDAO();
        return mensagemDAO.mensagensPorDestinatario(user);
    }
    
}
