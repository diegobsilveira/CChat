/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.common.model.domain.impl;

import cchat.common.model.domain.IDestinatario;

/**
 *
 * @author Aluno
 */
public class Usuario implements IDestinatario {

    private String ip;
    private String nome;

    public Usuario() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
