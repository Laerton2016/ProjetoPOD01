/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;

/**
 *
 * @author laerton
 */
@XStreamAlias("notificacao")
public interface INotificacao {

    void addMensagem(IMensagem mensagem);

    ArrayList<IMensagem> getMensagensG1();

    ArrayList<IMensagem> getMensagensG2();

    ArrayList<IMensagem> getMensagensG3();

    Integer getTotalMensagens();

    IUsuario getUser();
    
}
