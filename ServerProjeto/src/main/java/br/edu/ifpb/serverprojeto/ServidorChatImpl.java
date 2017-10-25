package br.edu.ifpb.serverprojeto;

import br.edu.ifpb.Shareprojeto01.IMensagem;
import br.edu.ifpb.Shareprojeto01.INotificacao;
import br.edu.ifpb.Shareprojeto01.Sala;
import br.edu.ifpb.Shareprojeto01.IServidorChat;
import br.edu.ifpb.Shareprojeto01.ISala;
import br.edu.ifpb.Shareprojeto01.IUsuario;
import br.edu.ifpb.Shareprojeto01.Mensagem;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author laerton
 */

public class ServidorChatImpl extends java.rmi.server.UnicastRemoteObject implements IServidorChat {

    
    
    private ArrayList<ISala> salas = new ArrayList<>();
    private ArrayList<IUsuario> cadastrados = new ArrayList<>();
    private ArrayList<IUsuario> logados = new ArrayList<>();

    public ServidorChatImpl() throws RemoteException {
        super();
        salas.add(new Sala("sala1", 1));
        salas.add(new Sala("sala2", 2));
        salas.add(new Sala("sala3", 3));
       
    }

    /**
     * *
     * retorna uma sala para o usario conectado.
     *
     * @param idSala id da sala
     * @param user usuario a ser conectado
     * @return - Sala
     * @throws RemoteException Verifica se o usuario esta inscrito na sala.
     */
    @Override
    public void entraSala(int idSala, IUsuario user) throws RemoteException {
        if(!salas.get(idSala-1).getUsuariosInscritos().contains(user)){
            throw new RemoteException("Sala nao localizada ou Usuario nao esta inscrito na sala.");
        }
        salas.get(idSala-1).conecta(user);
        salas.get(idSala-1).enviaMensagem(new Mensagem(user, user.getNome() +" entrou na sala.", idSala, Calendar.getInstance().getTime()));
        
    }

    /***
     * Increve um usuario em uma sala
     * @param idSala id da sala a ser incrito
     * @param user usaurio a ser inscrito
     * @return mensagem de confirma'cao
     */
    @Override
    public String inscreverUser(int idSala, IUsuario user) throws RemoteException{
       if((idSala -1) > salas.size() && (idSala -1) < 0 ){
           return "Usuario nao inscrito";
       }else{
           salas.get(idSala-1).inscreverSala(user);
           return "Usuario conectado";
       }
    }
    
    @Override
    public void publicaMensagem(int idSala, IMensagem men) throws RemoteException{
        if (men.getMensagem().equals("exit")){
            men.setMensagem("Saiu da sala");
            salas.get(idSala-1).sairSala(men.getUser());
        }
        salas.get(idSala-1).enviaMensagem(men);
        
    }
    
    @Override
    public ArrayList<IMensagem> listaMensagem(int idSala) throws RemoteException{
        return salas.get(idSala-1).lerMensagem();
    }
    
    @Override
    public ArrayList<IUsuario> getUserInscritos(int idSala) throws RemoteException{
        return salas.get(idSala-1).getUsuariosInscritos();
    }
    
    @Override
    public ArrayList<IUsuario> getUserDesconectados(int idSala) throws RemoteException{
        return salas.get(idSala-1).getUserDesconectados();
    }
    @Override
    public IUsuario login(String email, String senha) throws RemoteException{
        for (IUsuario usuario : this.cadastrados) {
            if(usuario.getEmail().equals(email)&& usuario.getSenha().equals(senha)){
                logados.add(usuario);
                return usuario;
            }
        }
        throw new RemoteException("Usuairo nao cadastrado.");
    }
    
    @Override  
    public String cadastrarUser(IUsuario user) throws RemoteException{
        cadastrados.add(user);
        return "Usuario cadastrado com sucesso!";
    }
    
    @Override
    public String logout (IUsuario user) throws RemoteException{
        logados.remove(user);
        return "Usaurio deslogado com sucesso!";
    }

    @Override
    public ArrayList<IMensagem> getNotificacaos(IUsuario user,int idSala) throws RemoteException {
        return this.salas.get(idSala).getNotificacaos(user);
        
    }
    
    @Override
    public ArrayList<INotificacao> getAllNotificacaos(int idSala) throws RemoteException {
        return this.salas.get(idSala).getAllNotificacao();
    }
}
