/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;



import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laerton
 */
@XStreamAlias("grupo")
public interface IGrupo extends Remote {

    void addUsuario(IUsuario user);

    int getId();

    String getNome();

    ArrayList<IUsuario> getUsuarios();

    void publicMensagem(IMensagem men);

    void remUsuario(IUsuario user);

    void setId(int id);

    void setNome(String nome);

    void setUsuarios(ArrayList<IUsuario> usuarios);
    
    ArrayList<IMensagem> getMensagens() ;
    
    void setMensagens(ArrayList<IMensagem> mensagens) ;
    int getNMensagem();
    void setNmensagem(int nMensagem);
    void addMensagem(IMensagem mensagem);
    
}
