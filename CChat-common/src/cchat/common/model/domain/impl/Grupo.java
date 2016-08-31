/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.common.model.domain.impl;

import cchat.common.model.domain.IDestinatario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Aluno
 */
public class Grupo implements IDestinatario, Serializable, Cloneable {

    private ArrayList<Sessao> destinos;
    private String nome;
    private Long id;

    public Grupo() {
        this.destinos = new ArrayList<>();
    }

    public ArrayList<Sessao> getDestinos() {
        return destinos;
    }

    public void setDestinos(ArrayList<Sessao> destinos) {
        this.destinos = destinos;
    }
    @Override
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
