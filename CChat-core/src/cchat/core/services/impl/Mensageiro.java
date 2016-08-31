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
import cchat.core.DAO.IMensagemDAO;
import cchat.core.DAO.impl.MensagemDAO;
import cchat.core.util.exception.PersistenciaException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nome
 */
public class Mensageiro implements IMensageiro{

    @Override
    public synchronized void send(Mensagem msg) {
        try{
            IMensagemDAO mensagemDAO = new MensagemDAO();
            if(msg.getDestino() instanceof Grupo){
                for(Sessao atual : ((Grupo)msg.getDestino()).getDestinos()){
                    
                    Grupo novo = new Grupo();
                    Mensagem clone = new Mensagem();
                    ArrayList<Sessao> destino = new ArrayList<>();
                    
                    destino.add(atual);
                    
                    novo.setNome(msg.getDestino().getNome());
                    novo.setDestinos(destino);
                    
                    clone.setOrigem(msg.getOrigem());
                    clone.setMensagem(msg.getMensagem());
                    clone.setDestino(novo);
                    
                    mensagemDAO.inserir(clone);
                }
            }else{
                mensagemDAO.inserir(msg);
            }
        }catch(PersistenciaException ex){
            Logger.getLogger(Mensageiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized ArrayList<Mensagem> get(Sessao user) {
        IMensagemDAO mensagemDAO = new MensagemDAO();
        return mensagemDAO.mensagensPorDestinatario(user);
    }
    
}
