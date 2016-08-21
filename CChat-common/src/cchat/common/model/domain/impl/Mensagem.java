/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.common.model.domain.impl;

import cchat.common.model.domain.IDestinatario;
import cchat.common.model.domain.IMensagem;

/**
 *
 * @author Aluno
 */
public class Mensagem implements IMensagem {

    private Sessao origem;
    private IDestinatario destino;
    private String mensagem;

    public Mensagem() {
    }

    public Sessao getOrigem() {
        return origem;
    }

    public void setOrigem(Sessao origem) {
        this.origem = origem;
    }

    public IDestinatario getDestino() {
        return destino;
    }

    public void setDestino(IDestinatario destino) {
        this.destino = destino;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
