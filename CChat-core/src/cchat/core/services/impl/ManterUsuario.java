/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.services.impl;

import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterUsuario;
import cchat.core.util.Data;

/**
 *
 * @author Aluno
 */
public class ManterUsuario implements IManterUsuario {

    @Override
    public synchronized boolean Logar(Sessao user) {
        return Data.addUsers(user);
    }

    @Override
    public boolean upToDate(Sessao user) {
        return Data.updateUser(user);
    }

}
