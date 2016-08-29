/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.DAO;

import cchat.core.util.exception.PersistenciaException;
import java.util.ArrayList;

/**
 *
 * @author Nome
 */
public interface IGenericDAO <Entidade>{
    Long inserir(Entidade obj) throws PersistenciaException;

    boolean atualizar(Entidade obj) throws PersistenciaException;

    Entidade excluir(Entidade obj) throws PersistenciaException;

    ArrayList<Entidade> listarTodos() throws PersistenciaException;
    
    Entidade consultarPorId(Long id) throws PersistenciaException;
}
