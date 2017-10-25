/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.rmi.Remote;

/**
 *
 * @author laerton
 */
@XStreamAlias("usuario")
public interface IUsuario extends Remote{

    String getSenha();
    void setSenha(String senha);
    
    String getEmail();

    //int getId();

    String getNome();

    void setEmail(String email);

    void setNome(String nome);
}
