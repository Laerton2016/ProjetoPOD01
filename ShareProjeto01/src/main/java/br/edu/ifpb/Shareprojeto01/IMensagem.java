/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.rmi.Remote;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author laerton
 */
@XStreamAlias("mensagem")
public interface IMensagem extends Remote{

    Date getDtPublica();
    
    void setDtPublica(Date dtPublica);
    
    long getId();

    String getMensagem();

    Status getStatus();

    IUsuario getUser();

    void setId(long id);

    void setMensagem(String mensagem);

    void setStatus(Status status);

    void setUser(IUsuario user);

    String toString();
    
    void setGrupoIndex(int index);
    
    int getGrupoIndex();
    
}
