/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.controller;

import cchat.common.model.domain.IDestinatario;
import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IMensageiro;
import cchat.view.proxi.stubMensageiro;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aluno
 */
public class mensagemOut {
    public static void execute(HttpServletRequest request) {
        String host = "localhost";
        int port = 2223;
        
        try {  
            IMensageiro mensageiro = new stubMensageiro(host,port);
            Mensagem msg = new Mensagem();
            IDestinatario atual = (IDestinatario)request.getSession().getAttribute("sala");
            msg.setMensagem(request.getParameter("msg"));
            msg.setOrigem((Sessao) request.getSession().getAttribute("user"));
            msg.setDestino(atual);
            mensageiro.send(msg);
        } catch (Exception e) {
        }        
    }
}
