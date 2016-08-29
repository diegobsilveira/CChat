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
public interface IGenericNamedDAO<Entidade> extends IGenericDAO<Entidade> {

    Entidade consultarPorNome(String nome) throws PersistenciaException;
}
