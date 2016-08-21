/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.services.impl;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.services.IManterGrupo;
import cchat.core.util.Data;

/**
 *
 * @author Nome
 */
public class ManterGrupo implements IManterGrupo{
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
