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
import cchat.core.util.exception.SemanticaException;
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
                boolean presente = false;
                Grupo groupDest = (Grupo) msg.getDestino();
                groupDest.setDestinos(
                        grupoDAO.consultarPorNome(groupDest.getNome()).getDestinos()
                );
                for (Sessao atual : groupDest.getDestinos()) {
                    if(atual.equals(msg.getOrigem())) presente = true;
                }
                if(!presente) throw new SemanticaException("Usuario não pode "
                        + "enviar mensagem para um grupo ao qual ele "
                        + "não pertence");
                for (Sessao atual : groupDest.getDestinos()) {
                    Grupo novo = new Grupo();
                    ArrayList<Sessao> lista = new ArrayList<>();
                    
                    lista.add(atual);
                    
                    novo.setNome(msg.getDestino().getNome());
                    novo.setDestinos(lista);
                    
                    Mensagem clone = new Mensagem();
                    clone.setOrigem(msg.getOrigem());
                    clone.setMensagem(msg.getMensagem());
                    clone.setDestino(novo);
                    clone.setEnvio(new Date());
                    mensagemDAO.inserir(clone);
                }
            } else {
                msg.setEnvio(new Date());
                mensagemDAO.inserir(msg);
            }
        } catch (SemanticaException | PersistenciaException ex) {
            Logger.getLogger(Mensageiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized ArrayList<Mensagem> get(Sessao user){
        try {
            IMensagemDAO mensagemDAO = new MensagemDAO();
            ArrayList<Mensagem> result = mensagemDAO.mensagensPorDestinatario(user);
            for(Mensagem msg : result){
                if(!msg.getOrigem().equals(user) || msg.getDestino() instanceof Grupo){
                    mensagemDAO.excluir(msg);
                }
            }
            return result;
        } catch (PersistenciaException ex) {
            return null;
        }
    }

}
