/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author laerton
 */
public class Notificacao implements INotificacao, Serializable {
    private IUsuario user ;
    private ArrayList<IMensagem> menG1 = new ArrayList<>();
   

    
    public Notificacao(IUsuario user) {
        this.user = user;
    }
    
    
    
    @Override
    public void addMensagem(IMensagem mensagem)throws RemoteException{
            menG1.add(mensagem);
       
    }
    
    @Override
    public Integer getTotalMensagens() throws RemoteException{
        return menG1.size();
    }
    
    @Override
    public ArrayList<IMensagem> getMensagensG1() throws RemoteException{
        ArrayList<IMensagem> copia = (ArrayList<IMensagem>) menG1.clone();
        menG1.clear();
        return copia;
    }
    
    @Override
    public ArrayList<IMensagem> getMenG1() throws RemoteException{
        return menG1;
    }

    public void setMenG1(ArrayList<IMensagem> menG1) throws RemoteException{
        this.menG1 = menG1;
    }
    
    
    @Override
    public IUsuario getUser() throws RemoteException{
        return user;
    }
}
