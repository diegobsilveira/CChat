/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.DAO.impl;

import cchat.common.model.domain.impl.Grupo;
import cchat.core.DAO.IGrupoDAO;
import cchat.core.util.connection.Data;
import cchat.core.util.exception.PersistenciaException;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class GrupoDAO implements IGrupoDAO {

    @Override
    public Long inserir(Grupo obj) throws PersistenciaException {
        Data dados = Data.getInstance();

        if (consultarPorNome(obj.getNome()) != null) {
            throw new PersistenciaException("Já existe um grupo com o mesmo nome");
        } else {
            obj.setId(this.maxId());
            dados.getGroups().add(obj);
            return obj.getId();
        }
    }

    @Override
    public boolean atualizar(Grupo obj) throws PersistenciaException {
        Data dados = Data.getInstance();
        if (consultarPorNome(obj.getNome()) != null) {
            for (Grupo atual : dados.getGroups()) {
                if (atual.getNome().equals(obj.getNome())) {
                    atual.setDestinos(obj.getDestinos());
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public Grupo excluir(Grupo obj) throws PersistenciaException {
        Data dados = Data.getInstance();
        Grupo group = consultarPorNome(obj.getNome());
        if (group != null) {
            dados.getGroups().remove(obj);

        }
        return group;
    }

    @Override
    public ArrayList<Grupo> listarTodos() throws PersistenciaException {
        return (ArrayList<Grupo>) Data.getInstance().getGroups().clone();
    }

    @Override
    public Grupo consultarPorNome(String nome) throws PersistenciaException {
        for (Grupo atual : listarTodos()) {
            if (atual.getNome().equals(nome)) {    
                return atual;
            }
        }
        return null;
    }

    @Override
    public Grupo consultarPorId(Long id) throws PersistenciaException {
        for (Grupo atual : listarTodos()) {
            if (atual.getId().equals(id)) {
                return atual;
            }
        }
        return null;
    }

    private Long maxId() throws PersistenciaException {        
        Long max = new Long(0);
        for (Grupo atual : listarTodos()) {
            if (atual.getId() >= max) {
                max = atual.getId() + 1;
            }
        }
        return max;
    }
}
