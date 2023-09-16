package com.example.preservacao;

import java.io.Serializable;

public class Preservador implements Serializable {

    private String idPreservador;
    private String usuario;
    private String profissao;
    private String senha;

    public Preservador() {
    }

    public Preservador(String idPreservador, String usuario, String profissao, String senha) {
        this.idPreservador = idPreservador;
        this.usuario = usuario;
        this.profissao = profissao;
        this.senha = senha;
    }

    public String getIdPreservador() {
        return idPreservador;
    }

    public void setIdPreservador(String idPreservador) {
        this.idPreservador = idPreservador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
