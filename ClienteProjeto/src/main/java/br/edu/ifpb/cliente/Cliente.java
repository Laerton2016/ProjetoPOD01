/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.cliente;

/**
 *
 * @author laerton
 */
import br.edu.ifpb.Shareprojeto01.ISala;
import br.edu.ifpb.Shareprojeto01.IServidorChat;
import br.edu.ifpb.Shareprojeto01.IUsuario;
import br.edu.ifpb.Shareprojeto01.Usuario;
import br.edu.ifpb.Shareprojeto01.Mensagem;

import java.rmi.*;
import java.util.Scanner;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente {
    public static void main( String args[] ) {
        try {
            final IServidorChat chat = (IServidorChat) Naming.lookup( "rmi://localhost:1098/ServidorChat" );
            final int grupoIndex;
            final IUsuario user;
            
            //Credenciais
            String nome;
            String msg = "";
            String email = "";
            boolean selecionado = true;
            int index =0;
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite seu nome:");
            nome = scanner.nextLine();
            System.out.println("Digite seu email:");
            email = scanner.nextLine();
            while (selecionado) 
            {                
                System.out.println("Digite entre 1 e 3 para inscrever  em uma sala:");
                try {
                    index = Integer.valueOf(scanner.nextLine());
                    
                    if (index>=1 && index <=3){
                        selecionado = false;
                    }else{
                        System.out.println("Sala invalida!");
                    }
                } catch (Exception e) {
                    System.out.println("Sala invalida!");
                }
                
            }
            
            grupoIndex = index;
            user = new Usuario(nome, email);
            
            //Inscrevendo usuario
            try {
                chat.entraSala(grupoIndex, user);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Adicionando usario a sala.");
                System.out.println(chat.inscreverUser(index, user));
                chat.entraSala(grupoIndex, user);
            }
            
            Thread thread = new Thread(new Runnable() {
                int cont = chat.listaMensagem(grupoIndex).size();
                @Override
                public void run() {
                        String texto = "";
                        boolean fica = true;
                        while(fica){
                            try {
                                if (chat.listaMensagem(grupoIndex).size() > cont){
                                    texto = (chat.listaMensagem(grupoIndex).get(
                                        chat.listaMensagem(grupoIndex).size()-1)
                                        .getUser().getNome() + ":" +
                                        chat.listaMensagem(grupoIndex).get(
                                        chat.listaMensagem(grupoIndex).size()-1)
                                        .getMensagem());
                                    
                                    System.out.println(texto);
                                    cont++;
                                }
                                if (texto.equals(user.getNome()+ ":" +"Saiu da sala")){
                                    fica = false;
                                }
                            } catch (RemoteException ex) {
                                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                }
            });
            thread.start();
            
            //Publicando as mensagens 
            System.out.println("Bem vido " + nome);
            while(!msg.equals("exit")){
                msg = scanner.nextLine();
                chat.publicaMensagem(grupoIndex, new Mensagem(user, msg, grupoIndex, Calendar.getInstance().getTime()));
            }
            
        }
        catch( Exception e ) {
           e.printStackTrace();
        }
    }
}