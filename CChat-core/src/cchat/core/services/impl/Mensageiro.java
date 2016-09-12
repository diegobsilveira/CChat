/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.services.impl;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IMensageiro;
import cchat.core.DAO.IGrupoDAO;
import cchat.core.DAO.IMensagemDAO;
import cchat.core.DAO.impl.GrupoDAO;
import cchat.core.DAO.impl.MensagemDAO;
import cchat.core.util.exception.PersistenciaException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nome
 */
public class Mensageiro implements IMensageiro {

    @Override
    public synchronized void send(Mensagem msg) {
        try {
            IMensagemDAO mensagemDAO = new MensagemDAO();
            IGrupoDAO grupoDAO = new GrupoDAO();
            if (msg.getDestino() instanceof Grupo) {
                Grupo groupDest = (Grupo) msg.getDestino();
                groupDest.setDestinos(
                        grupoDAO.consultarPorNome(groupDest.getNome()).getDestinos()
                );
                for (Sessao atual : groupDest.getDestinos()) {
                    Mensagem clone = new Mensagem();
                    clone.setOrigem(msg.getOrigem());
                    clone.setMensagem(msg.getMensagem());
                    clone.setDestino(atual);
                    clone.setEnvio(new Date());
                    mensagemDAO.inserir(clone);
                }
            } else {
                mensagemDAO.inserir(msg);
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(Mensageiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized ArrayList<Mensagem> get(Sessao user){
        try {
            IMensagemDAO mensagemDAO = new MensagemDAO();
            ArrayList<Mensagem> result = mensagemDAO.mensagensPorDestinatario(user);
            for(Mensagem msg : result){
                    mensagemDAO.excluir(msg);
            }
            return result;
        } catch (PersistenciaException ex) {
            return null;
        }
    }

}
