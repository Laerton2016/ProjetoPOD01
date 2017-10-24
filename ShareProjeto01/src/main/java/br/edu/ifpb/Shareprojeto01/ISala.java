/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author laerton
 */
public interface ISala extends Remote{

    /***
     * envia uma mensagem para a sala.
     * @param mensagem
     * @throws RemoteException
     */
    void enviaMensagem(IMensagem mensagem) throws RemoteException;

    /***
     * Lista todos os usarios inscritos na sala
     * @return
     */
    ArrayList<IUsuario> getUsuariosInscritos() throws RemoteException;

    /***
     * Inscreve o usario na sala.
     * @param user
     * @return
     * @throws RemoteException
     */
    String inscreverSala(IUsuario user) throws RemoteException ;

    /***
     * Retorna todas as mensagens da sala.
     * @param user
     * @return
     * @throws RemoteException
     */
    ArrayList<IMensagem> lerMensagem() throws RemoteException ;

    /***
     * remove a inscricao do usuario da sala
     * @param user
     * @return
     */
    String sairSala(IUsuario user) throws RemoteException; 
    
    /**
     * Conecta usuario 
     * @param user 
     */
    void conecta(IUsuario user) throws RemoteException;
    /**
     * Desconecta Usuario
     * @param user 
     */
    void desconectar(IUsuario user) throws RemoteException;
    /***
     * Lista de usuarios nao conectados
     * @return 
     */
    ArrayList<IUsuario> getUserDesconectados() throws RemoteException;
}
