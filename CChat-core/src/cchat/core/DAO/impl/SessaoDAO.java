/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.DAO.impl;

import cchat.common.model.domain.impl.Sessao;
import cchat.core.DAO.ISessaoDAO;
import cchat.core.util.connection.Data;
import cchat.core.util.exception.PersistenciaException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Aluno
 */
public class SessaoDAO implements ISessaoDAO {

    @Override
    public Long inserir(Sessao obj) throws PersistenciaException {
        Data dados = Data.getInstance();
        if(obj == null){
            throw new PersistenciaException("Nulo não pode ser inserido");
        } else if (consultarPorNome(obj.getNome()) != null) {
            throw new PersistenciaException("Já existe um usuario com o mesmo"
                    + " nome");
        } else if (obj.getNome().trim().equals("")) {
            throw new PersistenciaException("O nick é obrigatorio");
        }else {
            obj.setId(this.maxId());
            dados.getUsers().add(obj);
            return obj.getId();
        }
    }

    @Override
    public boolean atualizar(Sessao obj) throws PersistenciaException {
        Data dados = Data.getInstance();
        if (consultarPorNome(obj.getNome()) != null) {
            for (Sessao atual : dados.getUsers()) {
                if (atual.getNome().equals(obj.getNome())) {
                    atual.setLastAccess(obj.getLastAccess());
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public Sessao excluir(Sessao obj) throws PersistenciaException {
        Data dados = Data.getInstance();
        Sessao user = consultarPorNome(obj.getNome());
        if (user != null) {
            dados.getUsers().remove(obj);
        }
        return user;
    }

    @Override
    public ArrayList<Sessao> listarTodos() throws PersistenciaException {
        return (ArrayList<Sessao>) Data.getInstance().getUsers().clone();
    }

    @Override
    public Sessao consultarPorNome(String nome) throws PersistenciaException {
        for (Sessao atual : listarTodos()) {
            if (atual.getNome().equals(nome)) {
                return atual;
            }
        }
        return null;
    }

    @Override
    public Sessao consultarPorId(Long id) throws PersistenciaException {
        for (Sessao atual : listarTodos()) {
            if (atual.getId().equals(id)) {
                return atual;
            }
        }
        return null;
    }

    private Long maxId() throws PersistenciaException {
        Long max = new Long(0);
        for (Sessao atual : listarTodos()) {
            if (atual.getId() >= max) {
                max = atual.getId() + 1;
            }
        }
        return max;
    }
    
    public void refreshList() throws PersistenciaException {
        Data dados = Data.getInstance();
        for(Sessao atual : listarTodos()){
            if(atual.getLastAccess().getTime() < (new Date()).getTime()-30000){
                this.excluir(atual);
            }
        }
    }
}
