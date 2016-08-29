/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.DAO;

import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
import java.util.ArrayList;

/**
 *
 * @author Nome
 */
public interface IMensagemDAO extends IGenericDAO<Mensagem>{
    ArrayList<Mensagem> mensagensPorDestinatario(Sessao destino);
}
