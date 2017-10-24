/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;

import br.edu.ifpb.Shareprojeto01.IMensagem;
import br.edu.ifpb.Shareprojeto01.IUsuario;
import br.edu.ifpb.Shareprojeto01.Status;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;




/**
 *
 * @author laerton
 */

public class Mensagem implements IMensagem , Serializable {
    private long id;
    private IUsuario user;
    private String mensagem;
    private Status status = Status.PENDETE;
    private int grupoIndex = 0;
    private Date dtPublica;
    
    @Override   
    public IUsuario getUser() {
        return user;
    }

    public Mensagem() {
    }

    
    public Mensagem(IUsuario user, String mensagem, int grupoIndex, Date dtPublica) {
        this.user = user;
        this.mensagem = mensagem;
        this.grupoIndex = grupoIndex;
        this.dtPublica = dtPublica;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    
    @Override
    public void setUser(IUsuario user) {
        this.user = user;
    }

    @Override
    public String getMensagem() {
        return mensagem;
    }

    @Override
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return user.getNome().toUpperCase() +":" + mensagem ;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setGrupoIndex(int index) {
        this.grupoIndex =index;
    }

    @Override
    public int getGrupoIndex() {
        return grupoIndex;
    }

    @Override
    public Date getDtPublica() {
        return dtPublica;
    }

    @Override
    public void setDtPublica(Date dtPublica) {
        this.dtPublica = dtPublica;
    }
    
    
    
}
