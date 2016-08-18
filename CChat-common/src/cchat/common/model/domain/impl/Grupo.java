/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.common.model.domain.impl;

import cchat.common.model.domain.IDestinatario;
import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grupo other = (Grupo) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

}
