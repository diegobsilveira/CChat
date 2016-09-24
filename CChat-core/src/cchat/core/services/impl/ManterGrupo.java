/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.services.impl;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterGrupo;
import cchat.core.DAO.IGrupoDAO;
import cchat.core.DAO.impl.GrupoDAO;
import cchat.core.util.exception.PersistenciaException;
import java.util.ArrayList;

/**
 *
 * @author Nome
 */
public class ManterGrupo implements IManterGrupo {

    @Override
    public synchronized boolean criarGrupo(Grupo group) {
        try {
            IGrupoDAO grupoDAO = new GrupoDAO();
            return grupoDAO.inserir(group) != null;
        } catch (PersistenciaException ex) {
            return false;
        }
    }

    @Override
    public synchronized boolean adicionar(Grupo group, Sessao user) {
        try {
            IGrupoDAO grupoDAO = new GrupoDAO();
            Grupo grupo = grupoDAO.consultarPorNome(group.getNome());
            for (Sessao entrada : group.getDestinos()) {
                boolean existe = false;
                for (Sessao atual : grupo.getDestinos()) {
                    if (atual.equals(entrada)) {
                        existe = true;
                    }
                }
                if (!existe) {
                    grupo.getDestinos().add(entrada);
                }
            }
            return grupoDAO.atualizar(grupo);
        } catch (PersistenciaException ex) {
            return false;
        }
    }

    @Override
    public synchronized boolean sairGrupo(Grupo group) {
        try {
            IGrupoDAO grupoDAO = new GrupoDAO();
            Grupo grupo = grupoDAO.consultarPorNome(group.getNome());
            for (Sessao entrada : group.getDestinos()) {
                boolean existe = false;
                for (Sessao atual : grupo.getDestinos()) {
                    if (atual.equals(entrada)) {
                        existe = true;
                    }
                }
                if (existe) {
                    grupo.getDestinos().remove(entrada);
                }
            }
            return grupoDAO.atualizar(grupo);
        } catch (PersistenciaException ex) {
            return false;
        }
    }

    @Override
    public ArrayList<String> listarGrupos() {
        try {
            IGrupoDAO grupoDAO = new GrupoDAO();
            ArrayList<String> retorno = new ArrayList<>();
            if (grupoDAO.listarTodos().size() >0) {
                for (Grupo atual : grupoDAO.listarTodos()) {
                    retorno.add(atual.getNome());
                }
            }
            return retorno;
        } catch (PersistenciaException ex) {
            return null;
        }
    }
    
    @Override
    public ArrayList<String> listarGruposDoUsuario(Sessao user) {
        try {
            IGrupoDAO grupoDAO = new GrupoDAO();
            ArrayList<String> retorno = new ArrayList<>();
            if (grupoDAO.listarTodos().size() >0) {
                for (Grupo atual : grupoDAO.listarTodos()) {
                    boolean presente = false;
                    for(Sessao u : atual.getDestinos()){
                        if(user.equals(u)){
                            presente = true;
                        }
                    }
                    if(presente) retorno.add(atual.getNome());
                }
            }
            return retorno;
        } catch (PersistenciaException ex) {
            return null;
        }
    }
}
