/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.common.services;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Sessao;

/**
 *
 * @author Aluno
 */
public interface IManterUsuario {

    public boolean Logar(Sessao user);
    
    public boolean upToDate(Sessao user); 
}
