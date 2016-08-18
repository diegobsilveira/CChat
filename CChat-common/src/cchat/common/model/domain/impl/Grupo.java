/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.common.model.domain.impl;

import cchat.common.model.domain.IDestinatario;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class Grupo implements IDestinatario {

    private ArrayList<Usuario> destinos;
    private String nome;

    public Grupo() {
    }

    public ArrayList<Usuario> getDestinos() {
        return destinos;
    }

    public void setDestinos(ArrayList<Usuario> destinos) {
        this.destinos = destinos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
