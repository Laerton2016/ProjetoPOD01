/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.appprojeto;


import br.edu.ifpb.Shareprojeto01.IArquivoService;
import br.edu.ifpb.Shareprojeto01.Grupo;
import br.edu.ifpb.Shareprojeto01.IGrupo;
import br.edu.ifpb.Shareprojeto01.IUsuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class ServiceArquivo extends java.rmi.server.UnicastRemoteObject implements IArquivoService{
    private IGrupo grupo;
    

    public ServiceArquivo() throws RemoteException
    {
        
    }
    
    
    /***
     * Cria e grava o arquivo grupo
     * @param grupo grupo para criar o xml
     */
    @Override
    public void gravaArquivoGrupoXml(IGrupo grupo)throws RemoteException
    {
        XStream xStream = new XStream();
        xStream.alias("grupo", IGrupo.class);
        File arquivo = new File(grupo.getNome()+".xml");
        FileOutputStream gravar;
        try {
            gravar = new FileOutputStream(arquivo);
            gravar.write(xStream.toXML(grupo).getBytes());
            gravar.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public Grupo lerArquivoGrupoXml(String arquivo)throws RemoteException{
        Grupo grupo =null;
        try {
            XStream xStream = new XStream();
            xStream.addPermission (NoTypePermission.NONE);
            xStream.addPermission (AnyTypePermission.ANY);    
            xStream.allowTypeHierarchy (IGrupo.class);
            xStream.alias("grupo", IGrupo.class);
            FileInputStream file = new FileInputStream(new File(arquivo));
            grupo = (Grupo) xStream.fromXML(file);
        } catch (Exception ex) {
            Logger.getLogger(ServiceArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return grupo;
    }
    
    @Override
    public void gravaUsuariosCadastrados(ArrayList usuarios)throws RemoteException{
        
        ColecaoUsuarios users = new ColecaoUsuarios();
        users.setUsuarios(usuarios);
        XStream xStream = new XStream();
        xStream.alias("colecaoUsuarios", ColecaoUsuarios.class);
        File arquivo = new File("usuarioscadastrados.xml");
        FileOutputStream gravar;
        try {
            gravar = new FileOutputStream(arquivo);
            gravar.write(xStream.toXML(users).getBytes());
            gravar.close();
            gravarArquivoNoDropBox("usuarioscadastrados.xml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<IUsuario> lerUsuariosCadastrados() throws RemoteException{
        //donwArquivoDropBox("usuarioscadastrados.xml");
        ColecaoUsuarios colecaoUsuarios = new ColecaoUsuarios();
        try {
            XStream xStream = new XStream();
            xStream.addPermission (NoTypePermission.NONE);
            xStream.addPermission (AnyTypePermission.ANY);    
            xStream.allowTypeHierarchy (ColecaoUsuarios.class);
            xStream.alias("colecaoUsuarios", ColecaoUsuarios.class);
            FileInputStream file = new FileInputStream(new File("usuarioscadastrados.xml"));
            colecaoUsuarios = (ColecaoUsuarios) xStream.fromXML(file);
        } catch (Exception ex) {
            Logger.getLogger(ServiceArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return colecaoUsuarios.getUsuarios();
    }
    
    private void gravarArquivoNoDropBox(String nomeArquivo){
        FileDropBox fdb = new FileDropBox();
        try {
            fdb.uparFile(nomeArquivo);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void donwArquivoDropBox(String nomeArquivo){
        FileDropBox fdb = new FileDropBox();
        try {
           // fdb.donwFile(nomeArquivo);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
