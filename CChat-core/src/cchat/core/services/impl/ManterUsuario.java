/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.services.impl;

import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterUsuario;
import cchat.core.DAO.IGrupoDAO;
import cchat.core.DAO.IMensagemDAO;
import cchat.core.DAO.ISessaoDAO;
import cchat.core.DAO.impl.GrupoDAO;
import cchat.core.DAO.impl.SessaoDAO;
import cchat.core.util.exception.PersistenciaException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class ManterUsuario implements IManterUsuario {

    @Override
    public synchronized boolean Logar(Sessao user) {
        try {
            ISessaoDAO sessaoDAO = new SessaoDAO();
            IGrupoDAO grupoDAO = new GrupoDAO();
            Grupo geral = grupoDAO.consultarPorNome("GERAL");
            geral.getDestinos().add(user);
            grupoDAO.atualizar(geral);
            return sessaoDAO.inserir(user) != null;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public synchronized boolean upToDate(Sessao user) {
        try {
            ISessaoDAO sessaoDAO = new SessaoDAO();
            Sessao novo = sessaoDAO.consultarPorNome(user.getNome());
            if(novo != null){
                novo.setLastAccess(new Date());
                return sessaoDAO.atualizar(novo);
            }else{
                return false;
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(ManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<String> listarUsuarios() {
        ISessaoDAO sessaoDAO = new SessaoDAO();
        try {
            ArrayList<String> lista = new ArrayList<>();
            for(Sessao user : sessaoDAO.listarTodos()){
                lista.add(user.getNome());
            }
            return lista;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}

