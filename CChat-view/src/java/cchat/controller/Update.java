/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.controller;

import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterUsuario;
import cchat.view.proxi.stubManterUsuario;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aluno
 */
class Update {

    public static void execute(HttpServletRequest request) {
        try {
            Sessao user = (Sessao)request.getSession().getAttribute("user");
            IManterUsuario manter = new stubManterUsuario();
            manter.upToDate(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
