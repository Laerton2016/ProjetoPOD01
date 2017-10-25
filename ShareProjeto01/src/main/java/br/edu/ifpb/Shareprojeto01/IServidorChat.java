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
public interface IServidorChat extends Remote {

    
    
    /**
     * *
     * retorna uma sala para o usario conectado.
     *
     * @param idSala id da sala
     * @param user usuario a ser conectado
     * @return - Sala
     * @throws RemoteException Verifica se o usuario esta inscrito na sala.
     */
    void entraSala(int idSala, IUsuario user) throws RemoteException;
    
    /***
     * Inscreve um usario na sala.
     * @param idSala -id da sala 
     * @param user - Usuario a ser inscrito
     * @return Mensagem de confirmacao.
     */
    String inscreverUser(int idSala, IUsuario user) throws RemoteException ;

    /***
     * Publica uma mensagem do usuario repassado como paramentro para a sala do id informado
     * @param idSala - ide da sala 1,2 ou 3
     * @param men Mensagem do usuario
     * @throws RemoteException - Sai da sala com a mensagem exit
     */
    void publicaMensagem(int idSala, IMensagem men) throws RemoteException;
    /***
     * Retorna a lista de mensagens de uma sala repassada por id
     * @param idSala id da sala - 1 , 2 ou 3
     * @return
     * @throws RemoteException 
     */
    ArrayList<IMensagem> listaMensagem(int idSala) throws RemoteException;
    
    /***
     * Lista de usuarios inscritos na sala do id  repassado
     * @param idSala - id da sala 1,2 ou 3
     * @return
     * @throws RemoteException 
     */
    ArrayList<IUsuario> getUserInscritos(int idSala) throws RemoteException;
    /***
     * Lista de usuarios desconectadas da sala cujo id foi repassado
     * @param idSala id da sala - 1, 2 u 3
     * @return
     * @throws RemoteException 
     */
    ArrayList<IUsuario> getUserDesconectados(int idSala) throws RemoteException;
    /***
     * Loga o usuario no chat
     * @param email - Email do  usuairo para logar 
     * @param senha - Senha para logar
     * @return Informacao de logado
     * @throws RemoteException 
     */
    IUsuario login(String email, String senha) throws RemoteException;
    
    /***
     * Cadastra o usuario no servidor
     * @param user- Usuario a ser cadastrado
     * @return - Mensagem de informaccao
     * @throws RemoteException 
     */
    String cadastrarUser(IUsuario user) throws RemoteException;
    
    /***
     * Desloga o usuairo do Chat.
     * @param user - Usuario a ser deslogado
     * @return
     * @throws RemoteException 
     */
    String logout (IUsuario user) throws RemoteException;
    /***
     * Retorna todas as notificacoes para um usuario.
     * @param user- Usuario que sera notificado
     * @return
     * @throws RemoteException 
     */
    ArrayList<IMensagem> getNotificacaos(IUsuario user,int idSala) throws RemoteException;
    
    ArrayList<INotificacao> getAllNotificacaos(int idSala) throws RemoteException;
}
