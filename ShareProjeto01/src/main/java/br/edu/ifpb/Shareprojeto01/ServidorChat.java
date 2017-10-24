package br.edu.ifpb.Shareprojeto01;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laerton
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServidorChat extends Remote {

    public void enviarMensagem(String mensagem) throws RemoteException;
    public ArrayList<String> lerMensagem() throws RemoteException;
    public void enviaMensagem(IMensagem mensagem)throws RemoteException;
    public ArrayList<IMensagem> lerMensagem(int indexGrupo, IUsuario user) throws RemoteException;
    public String inscreverGrupo(int indexGrup, IUsuario user) throws RemoteException;
    public void conectar(IUsuario user);
    public void desconectar(IUsuario user);
    public ArrayList<IUsuario> getAllUserConectados();
    
}

