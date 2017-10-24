/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author laerton
 */
public class Notificacao implements INotificacao, Serializable {
    private IUsuario user ;
    private ArrayList<IMensagem> menG1 = new ArrayList<>();
    
    private ArrayList<IMensagem> menG2 = new ArrayList<>();
    
    private ArrayList<IMensagem> menG3 = new ArrayList<>();

    
    public Notificacao(IUsuario user) {
        this.user = user;
    }
    
    
    
    @Override
    public void addMensagem(IMensagem mensagem){
        if (mensagem.getGrupoIndex() == 1){
            menG1.add(mensagem);
        }else if (mensagem.getGrupoIndex() == 2){
            menG2.add(mensagem);
        }else if (mensagem.getGrupoIndex() == 3){
            menG3.add(mensagem);
        }
    }
    
    @Override
    public Integer getTotalMensagens(){
        return menG1.size() +menG2.size() + menG3.size();
    }
    
    @Override
    public ArrayList<IMensagem> getMensagensG1(){
        ArrayList<IMensagem> copia = (ArrayList<IMensagem>) menG1.clone();
        menG1.clear();
        return copia;
    }
    
    @Override
    public ArrayList<IMensagem> getMensagensG2(){
        ArrayList<IMensagem> copia = (ArrayList<IMensagem>) menG2.clone();
        menG1.clear();
        return copia;
    }
    
    @Override
    public ArrayList<IMensagem> getMensagensG3(){
        ArrayList<IMensagem> copia = (ArrayList<IMensagem>) menG3.clone();
        menG1.clear();
        return copia;
    }
    
    @Override
    public IUsuario getUser(){
        return user;
    }
}
