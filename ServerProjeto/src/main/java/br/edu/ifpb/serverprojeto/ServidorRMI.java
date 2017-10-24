package br.edu.ifpb.serverprojeto;


import br.edu.ifpb.Shareprojeto01.IServidorChat;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laerton
 */
public class ServidorRMI {

    public ServidorRMI() throws MalformedURLException {
       try {
        Registry registry = LocateRegistry.createRegistry(1098);
        IServidorChat server = new ServidorChatImpl();
        Naming.rebind("rmi://localhost:1098/ServidorChat",server);
        } catch (RemoteException e){
            System.out.println("Trouble: "+e);
        }
    }
        
    public static void main (String args[]) throws MalformedURLException{
        new ServidorRMI();
    }
}
    

