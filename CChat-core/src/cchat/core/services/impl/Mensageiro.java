/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.services.impl;

import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Usuario;
import cchat.common.services.IMensageiro;
import cchat.core.util.Data;

/**
 *
 * @author Nome
 */
public class Mensageiro implements IMensageiro{

    @Override
    public synchronized void send(Mensagem msg) {
        Data.addMsgs(msg);
    }

    @Override
    public synchronized Mensagem get(Usuario user) {
        return Data.removeMsg(user);
    }
    
}
