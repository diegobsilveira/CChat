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
        try {
            IMensageiro mensageiro = new stubMensageiro();
            Mensagem msg = new Mensagem();
            IDestinatario destino = null;
            String rawMsg = request.getParameter("msg").trim();
            switch(rawMsg.split("\"")[0]){
                case "\\g " :
                    destino = new Grupo();
                    ((Grupo)destino).setNome(rawMsg.split("\"")[1]);
                    rawMsg = rawMsg.substring(rawMsg.indexOf("\"")+1);
                    rawMsg = rawMsg.substring(rawMsg.indexOf("\"")+1);
                    break;
                case "\\pm " :
                    destino = new Sessao();
                    ((Sessao)destino).setNome(rawMsg.split("\"")[1]);
                    rawMsg = rawMsg.substring(rawMsg.indexOf("\"")+1);
                    rawMsg = rawMsg.substring(rawMsg.indexOf("\"")+1);
                    break;
                default:
                    destino = new Grupo();
                    ((Grupo)destino).setNome("GERAL");
                    break;
            }
            
            msg.setMensagem(rawMsg);
            msg.setOrigem((Sessao) request.getSession().getAttribute("user"));
            msg.setDestino(destino);
            mensageiro.send(msg);
        } catch (Exception e) {
        }        
    }
}
