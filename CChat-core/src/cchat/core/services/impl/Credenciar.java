/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.services.impl;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Usuario;
import cchat.common.services.ICredenciar;
import java.util.Iterator;
import cchat.core.util.Data;

/**
 *
 * @author Aluno
 */
public class Credenciar implements ICredenciar {

    @Override
    public synchronized boolean Logar(Usuario user) {
        return Data.addUsers(user);
    }

    @Override
    public synchronized boolean criarGrupo(Grupo group) {
        return Data.addGroups(group);
    }

    @Override
    public synchronized boolean convidar(Grupo group) {
        return Data.addToGroup(group);
    }

    @Override
    public synchronized boolean sairGrupo(Grupo group) {
        return Data.removeFromGroups(group);
    }
    
}
