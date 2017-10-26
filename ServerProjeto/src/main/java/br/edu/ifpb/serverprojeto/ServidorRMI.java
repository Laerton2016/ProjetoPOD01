package br.edu.ifpb.serverprojeto;


import br.edu.ifpb.Shareprojeto01.IArquivoService;
import br.edu.ifpb.Shareprojeto01.IServidorChat;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private IServidorChat server;

    public ServidorRMI() throws MalformedURLException, NotBoundException {
       try {
        server = new ServidorChatImpl();
        IArquivoService app = (IArquivoService) Naming.lookup( "rmi://localhost:1097/Servico" );
        Registry registry = LocateRegistry.createRegistry(1098);
        ((ServidorChatImpl)server).setCadastrados(app.lerUsuariosCadastrados());
        server.setServico(app);
        Naming.rebind("rmi://localhost:1098/ServidorChat",server);
        } catch (RemoteException e){
            System.out.println("Trouble: "+e);
        }
    }
        
    public static void main (String args[]) throws MalformedURLException, NotBoundException{
        new ServidorRMI();
    }
    
    
}
    

