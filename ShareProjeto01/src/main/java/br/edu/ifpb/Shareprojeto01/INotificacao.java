/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author laerton
 */
@XStreamAlias("notificacao")
public interface INotificacao extends Remote{

    void addMensagem(IMensagem mensagem) throws RemoteException;

    ArrayList<IMensagem> getMensagensG1() throws RemoteException;

    ArrayList<IMensagem> getMenG1() throws RemoteException;
    
    Integer getTotalMensagens()throws RemoteException;

    IUsuario getUser() throws RemoteException;
    
}
