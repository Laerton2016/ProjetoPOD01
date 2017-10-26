/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;

import br.edu.ifpb.Shareprojeto01.Grupo;
import br.edu.ifpb.Shareprojeto01.IGrupo;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author laerton
 */
public interface IArquivoService extends Remote{

    /***
     * Cria e grava o arquivo grupo
     * @param grupo grupo para criar o xml
     */
    void gravaArquivoGrupoXml(IGrupo grupo) throws RemoteException;

    void gravaUsuariosCadastrados(ArrayList usuarios) throws RemoteException;

    Grupo lerArquivoGrupoXml(String arquivo) throws RemoteException;
    
    ArrayList<IUsuario> lerUsuariosCadastrados() throws RemoteException;
    
}
