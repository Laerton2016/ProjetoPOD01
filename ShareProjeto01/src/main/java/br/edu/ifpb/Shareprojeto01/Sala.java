/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author laerton
 */
public class Sala implements ISala,Serializable {
    
    private IGrupo grupo;
    private ArrayList<IUsuario> conectados = new ArrayList<>();
    private ArrayList<INotificacao> allNotificacao = new ArrayList<>();
    private Hashtable<String, INotificacao> notas = new Hashtable<>();
    
    public Sala(String nome, int id) {
        this.grupo = new Grupo(id, nome);
    }
    
    /***
     * Retorna todas as mensagens da sala.
     * @param user
     * @return
     * @throws RemoteException 
     */
    @Override
    public ArrayList<IMensagem> lerMensagem() throws RemoteException{
           return grupo.getMensagens();
    }
    /***
     * envia uma mensagem para a sala.
     * @param mensagem
     * @throws RemoteException 
     */
    @Override
    public void enviaMensagem(IMensagem mensagem) throws RemoteException {
        if (mensagem.getMensagem().equals("exit")){
            desconectar(mensagem.getUser());
            mensagem.setMensagem("Saiu da sala!");
        }
        gerarNotificacao(mensagem);
        this.grupo.addMensagem(mensagem);
    }
    
    /***
     * Inscreve o usario na sala.
     * @param user
     * @return
     * @throws RemoteException 
     */
    @Override
    public String inscreverSala(IUsuario user) throws RemoteException {
        grupo.addUsuario(user);
        return "Usuario inscrito";
    }
    
    /***
     * remove a inscricao do usuario da sala
     * @param user
     * @return 
     */
    @Override
    public String sairSala(IUsuario user) throws RemoteException{
        grupo.remUsuario(user);
        return "Usuario removido";
    }
    
    /***
     * Lista todos os usarios inscritos na sala
     * @return 
     */
    @Override
    public ArrayList<IUsuario> getUsuariosInscritos() throws RemoteException{
        return  grupo.getUsuarios();
    }

    @Override
    public void conecta(IUsuario user) throws RemoteException {
        this.conectados.add(user);
    }

    @Override
    public void desconectar(IUsuario user) throws RemoteException {
        this.conectados.remove(user);
    }

    @Override
    public ArrayList<IUsuario> getUserDesconectados() throws RemoteException {
        ArrayList<IUsuario> desconectados = new ArrayList<>();
        for (IUsuario usuario : grupo.getUsuarios()) {
            if(!conectados.contains(usuario)){
                desconectados.add(usuario);
            }
        }
        return desconectados;
    }

    private void gerarNotificacao(IMensagem mensagem) throws RemoteException {
        IUsuario user = mensagem.getUser();
        /*for (IUsuario usuario : grupo.getUsuarios()) {
            //if(!conectados.contains(usuario)){
                for (INotificacao iNotificacao : allNotificacao) {
                    if (iNotificacao.getUser().getEmail().equals(user.getEmail()) && 
                    iNotificacao.getUser().getSenha().equals(user.getSenha()) && 
                    iNotificacao.getUser().getNome().equals(user.getNome())){
                        iNotificacao.addMensagem(mensagem);
                        break;
                    }
                }
                Notificacao note = new Notificacao(user);
                note.setIdSala(this.grupo.getId());
                note.addMensagem(mensagem);
                allNotificacao.add(note);
            //}
        
        }*/
        for (IUsuario userDesconectado : getUserDesconectados()) {
            if(notas.containsKey(userDesconectado.getEmail())){
                notas.get(userDesconectado.getEmail()).addMensagem(mensagem);
            }else{
                INotificacao nota = new Notificacao(userDesconectado);
                nota.addMensagem(mensagem);
                nota.setIdSala(grupo.getId());
                notas.put(userDesconectado.getEmail(), nota);
            }
        }
    }
    
    @Override
    public ArrayList<IMensagem> getNotificacaos(IUsuario user ) throws RemoteException{
        ArrayList<IMensagem> retorno = new ArrayList<>();
        /*for (INotificacao iNotificacao : allNotificacao) {
            if (iNotificacao.getUser().getEmail().equals(user.getEmail()) && 
                    iNotificacao.getUser().getSenha().equals(user.getSenha()) && 
                    iNotificacao.getUser().getNome().equals(user.getNome())){
                retorno = iNotificacao.getMensagensG1();
                break;
            }
        }*/
        return notas.get(user.getEmail()).getMensagensG1();
    }

    @Override
    public IGrupo getGrupo() {
        return grupo;
    }

    @Override
    public void setGrupo(IGrupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public ArrayList<IUsuario> getConectados() {
        return conectados;
    }

    @Override
    public void setConectados(ArrayList<IUsuario> conectados) {
        this.conectados = conectados;
    }

    @Override
    public Hashtable<String, INotificacao> getAllNotificacao() {
        return notas;
    }

    @Override
    public void setAllNotificacao(Hashtable<String, INotificacao> allNotificacao) {
        this.notas = allNotificacao;
    }
    
    
    
    
    
}
