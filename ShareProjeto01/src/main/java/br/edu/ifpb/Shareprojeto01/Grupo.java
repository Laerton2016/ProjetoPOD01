/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author laerton
 */

public class Grupo implements IGrupo, Serializable{
    
    private int id;
    private String nome;
    private ArrayList<IUsuario> usuarios = new ArrayList<>();
    private ArrayList<IMensagem> mensagens = new ArrayList<>();
    private int nMensagem = 0;
    
    public Grupo() 
    {
        
    }
    
    @Override
    public void publicMensagem(IMensagem men){
        nMensagem++;
        men.setId(nMensagem);
        mensagens.add(men);
    }
    
    public Grupo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public void addUsuario (IUsuario user){
        usuarios.add(user);
    }
    
    @Override
    public void remUsuario (IUsuario user){
        usuarios.remove(user);
    }
    
    
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public ArrayList<IUsuario> getUsuarios() {
        return usuarios;
    }

    @Override
    public void setUsuarios(ArrayList<IUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public ArrayList<IMensagem> getMensagens() {
        return mensagens;
    }
    
    @Override
    public void setMensagens(ArrayList<IMensagem> mensagens) {
        this.mensagens = mensagens;
    }

    @Override
    public int getNMensagem() {
        return nMensagem;
    }

    @Override
    public void setNmensagem(int nMensagem) {
        this.nMensagem = nMensagem;
    }

    @Override
    public void addMensagem(IMensagem mensagem) {
        mensagens.add(mensagem);
    }
    
    
    
}
