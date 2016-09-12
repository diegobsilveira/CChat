/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.DAO.impl;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
import cchat.core.DAO.IMensagemDAO;
import cchat.core.util.connection.Data;
import cchat.core.util.exception.PersistenciaException;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class MensagemDAO implements IMensagemDAO {

    @Override
    public Long inserir(Mensagem obj) throws PersistenciaException {
        Data dados = Data.getInstance();

        obj.setId(this.maxId());
        dados.getMsgs().add(obj);
        return obj.getId();
    }

    @Override
    public boolean atualizar(Mensagem obj) throws PersistenciaException {
        Data dados = Data.getInstance();
        if (consultarPorId(obj.getId()) != null) {
            for (Mensagem atual : dados.getMsgs()) {
                if (atual.getId().equals(obj.getId())) {
                    atual.setDestino(obj.getDestino());
                    atual.setMensagem(obj.getMensagem());
                    atual.setOrigem(obj.getOrigem());
                    atual.setEnvio(obj.getEnvio());
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public Mensagem excluir(Mensagem obj) throws PersistenciaException {
        Data dados = Data.getInstance();
        Mensagem msg = consultarPorId(obj.getId());
        if (msg != null) {
            dados.getMsgs().remove(obj);
        }
        return msg;
    }

    @Override
    public ArrayList<Mensagem> listarTodos() throws PersistenciaException {
        return (ArrayList<Mensagem>) Data.getInstance().getMsgs().clone();
    }

    @Override
    public Mensagem consultarPorId(Long id) throws PersistenciaException {
        for (Mensagem atual : listarTodos()) {
            if (atual.getId().equals(id)) {
                return atual;
            }
        }
        return null;
    }

    private Long maxId() throws PersistenciaException {
        Long max = new Long(0);
        for (Mensagem atual : listarTodos()) {
            if (atual.getId() >= max) {
                max = atual.getId() + 1;
            }
        }
        return max;
    }

    @Override
    public ArrayList<Mensagem> mensagensPorDestinatario(Sessao destino) {
        try {
            ArrayList<Mensagem> novo = new ArrayList<>();
            for (Mensagem atual : listarTodos()) {
                if(atual.getDestino() instanceof Grupo){
                    if(((Grupo)atual.getDestino()).getDestinos().get(0).getNome().equals(destino.getNome())){
                        novo.add(atual);
                    }
                }else{
                    if(atual.getDestino().getNome().equals(destino.getNome())){
                        novo.add(atual);
                    }
                    novo.add(atual);
                }
            }
            return novo;
        } catch (PersistenciaException ex) {
            return null;
        }
    }

}
