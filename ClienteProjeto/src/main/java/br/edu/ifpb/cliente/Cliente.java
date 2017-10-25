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

import br.edu.ifpb.Shareprojeto01.IMensagem;
import br.edu.ifpb.Shareprojeto01.IServidorChat;
import br.edu.ifpb.Shareprojeto01.IUsuario;
import br.edu.ifpb.Shareprojeto01.Usuario;
import br.edu.ifpb.Shareprojeto01.Mensagem;


import java.rmi.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente {
    
    /*private static ArrayList<IMensagem> menS1 = new ArrayList<>();
    private static ArrayList<IMensagem> menS2 = new ArrayList<>();
    private static ArrayList<IMensagem> menS3 = new ArrayList<>();
    private static boolean ativoS1 = false;
    private static boolean ativoS2 = false;
    private static boolean ativoS3 = false;
    private static IUsuario user;*/
    private static Credencias credenciais =new Credencias();
    
    public static void main( String args[] ) {
        Scanner scanner = new Scanner(System.in);
            recuperaDados(scanner);
            try {
            final IServidorChat chat = (IServidorChat) Naming.lookup( "rmi://localhost:1098/ServidorChat" );
            if (credenciais.getUser() == null){
                menu1(scanner, chat);
            }else{
                enviarMensagensOffLine(chat);
                menu2(scanner, chat, credenciais.getUser());
            }
        } catch (Exception ex) {
            System.out.println("Conexao com servidor falhou!");
            menuOff1(scanner);
        }
        
        
        
    }
    
    /***
     * Menu principal da aplicacao
     * @param scanner
     * @param chat
     * @throws RemoteException 
     */
    private static void menu1(Scanner scanner, IServidorChat chat) throws RemoteException
    {
        int op = 0;
        String email, senha, nome;
        boolean abri = true;
        while (abri) {            
            System.err.println("Selecione uma das opcoes abaixo:");
            System.err.println("1 - Logar");
            System.err.println("2 - Cadastrar Usuario");
            System.err.println("3 - Sair");
            op = scanner.nextInt();
            scanner.nextLine();
            
            switch(op){
                case 1:
                    System.err.println("Informe e-mail:");
                    email = scanner.nextLine();
                    System.err.println("Informe senha");
                    senha = scanner.nextLine();
                    try {
                        credenciais.setUser(chat.login(email, senha));
                        verificaSalasInscritas(chat);
                        enviarMensagensOffLine(chat);
                        menu2(scanner, chat, credenciais.getUser());
                    } catch (Exception e) {
                        System.out.println("Usuario invalido");
                    } finally {
                        break;
                    }
                     
                case 2:
                    System.err.println("Informe nome:");
                    nome = scanner.nextLine();
                    System.err.println("Informe e-mail:");
                    email = scanner.nextLine();
                    System.err.println("Informe senha");
                    senha = scanner.nextLine();
                    credenciais.setUser( new Usuario(nome, email));
                    credenciais.getUser().setSenha(senha);
                    System.out.println(chat.cadastrarUser(credenciais.getUser()));
                    break;
                case 3:
                    gravaDados();
                    abri = false;
                    break;
                default:
                    System.err.println("Opcao invalida");
                    
            }
        }
                    
    }
    /***
     * Menu das salas de bate-papo
     * @param scanner
     * @param chat
     * @param user
     * @throws RemoteException 
     */
    private static void menu2(Scanner scanner, IServidorChat chat, IUsuario user) throws RemoteException {
        int op = 0;
        boolean abri = true;
        while (abri) {  
            System.err.println("Selecione uma das salas:");
            System.err.println("1 - Sala1");
            System.err.println("2 - Sala2");
            System.err.println("3 - Sala3");
            System.err.println("4 - Retornar");
            op = scanner.nextInt();
            scanner.nextLine();
            
            switch(op) {
                case 1:
                      if(!credenciais.isAtivoS1()){
                          //System.out.println("Usuario nao cadastrado!");
                          cadastrarUserSala(user,chat,op);
                          credenciais.setAtivoS1(true);
                          gravaDados();
                      }
                      iniciaChatSala(scanner, chat, user, 1);
                      break;
                case 2:
                    if(!credenciais.isAtivoS2()){
                          //System.out.println("Usuario nao cadastrado!");
                          cadastrarUserSala(user,chat,op);
                          credenciais.setAtivoS2(true);
                          gravaDados();
                      }
                    iniciaChatSala(scanner, chat, user, 2);
                    break;
                case 3:
                    if(!credenciais.isAtivoS3()){
                          //System.out.println("Usuario nao cadastrado!");
                          cadastrarUserSala(user,chat,op);
                          credenciais.setAtivoS3(true);
                          gravaDados();
                      }
                    iniciaChatSala(scanner, chat, user, 3);
                    break;
                case 4:
                    abri = false;
                    break;
                default:
                    System.err.println("Opcao invalida!");
                    break;
            }       
        }
    }

    
    private static void iniciaChatSala(Scanner scanner, IServidorChat chat, IUsuario user, int grupoIndex) throws RemoteException {
            String msg = "";
            
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
                                        cont)
                                        .getUser().getNome() + ":" +
                                        chat.listaMensagem(grupoIndex).get(
                                        cont)
                                        .getMensagem());
                                    
                                    System.out.println(texto);
                                    cont++;
                                    
                                    Thread.sleep(700);
            
                                }
                                if (texto.equals(user.getNome()+ ":" +"Saiu da sala")){
                                    fica = false;
                                }
                            } catch (Exception ex) {
                            
                            }
                        }
                }
            });
            thread.start();
            
            //Publicando as mensagens 
            //System.out.println("Bem vindo " + user.getNome());
            chat.publicaMensagem(grupoIndex, new Mensagem(user, "Entrou na sala", grupoIndex, Calendar.getInstance().getTime()));
            try {
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            while(!msg.equals("exit")){
                msg = scanner.nextLine();
                chat.publicaMensagem(grupoIndex, new Mensagem(user, msg, grupoIndex, Calendar.getInstance().getTime()));
            }
    }

    private static void menuOff1(Scanner scanner) {
        int op = 0;
        boolean abri = true;
        String men, nome, email, senha;
        IMensagem mensagem = null;
        while (abri) { 
            System.out.println("Selecione uma opcao:");
            System.out.println("1 - Tentar conectar novamente");
            System.out.println("2 - Enviar mensagem off-line para sala1");
            System.out.println("3 - Enviar mensagem off-line para sala2");
            System.out.println("4 - Enviar mensagem off-line para sala3");
            System.out.println("5 - Sair  da aplicacoa");
            op = scanner.nextInt();
            scanner.nextLine();
            switch(op) {
                case 1:
                    main(null);
                    break;
                case 2:
                    if (credenciais.isAtivoS1()){
                        System.err.println("Gerando mensagem off para sala 1"); 
                        System.err.println("Digite a mensagem:"); 
                        men = scanner.nextLine();
                        mensagem = new Mensagem(credenciais.getUser(), men, op, Calendar.getInstance().getTime());
                        credenciais.addMensagem(mensagem, 1);
                        gravaDados();
                        System.err.println("Mensagem adiciona, sera entregue assim que"
                                + " re-estabelecer a conexao com servidor e se os dados do usuario baterem com os cadastrados na sala.");
                    }else{
                        System.err.println("Usuario nao inscrito nesta sala.");
                    }
                    break;
                case 3:
                    if (credenciais.isAtivoS2()){
                        System.err.println("Gerando mensagem off para sala 2"); 
                        System.err.println("Digite a mensagem:"); 
                        men = scanner.nextLine();
                        mensagem = new Mensagem( credenciais.getUser(), men, op, Calendar.getInstance().getTime());
                        credenciais.addMensagem(mensagem, 2);
                        gravaDados();
                        System.err.println("Mensagem adiciona, sera entregue assim que"
                                + " re-estabelecer a conexao com servidor e se os dados do usuario baterem com os cadastrados na sala.");
                    }else{
                        System.err.println("Usuario nao inscrito nesta sala.");
                    }
                    break;
                case 4:
                    if (credenciais.isAtivoS3()){
                        System.err.println("Gerando mensagem off para sala 3"); 
                        System.err.println("Digite a mensagem:"); 
                        men = scanner.nextLine();
                        mensagem = new Mensagem(credenciais.getUser(), men, op, Calendar.getInstance().getTime());
                        credenciais.addMensagem(mensagem, 3);
                        gravaDados();
                        System.err.println("Mensagem adiciona, sera entregue assim que"
                                + " re-estabelecer a conexao com servidor e se os dados do usuario baterem com os cadastrados na sala.");
                    }else{
                        System.err.println("Usuario nao inscrito nesta sala.");
                    }
                    break;
                case 5:
                    abri = false;
                    gravaDados();
                    break;
                default:
                    System.out.println("opcao invalida");
            
        }
     }
    }

   /* private static boolean verificaUserCadastrado(IUsuario user, IServidorChat chat, int sala) throws RemoteException {
        return chat.getUserDesconectados(sala).contains(user);
    }*/

    private static void cadastrarUserSala(IUsuario user, IServidorChat chat, int op) throws RemoteException {
        System.out.println("Cadastrado usuario:");
        chat.inscreverUser(op, user);
    }

    private static void verificaSalasInscritas(IServidorChat chat) throws RemoteException {
        System.out.println(credenciais.getUser().equals(credenciais.getUser()));     
        credenciais.setAtivoS1(contemUser(chat.getUserInscritos(1)));
        credenciais.setAtivoS2(contemUser(chat.getUserInscritos(2)));
        credenciais.setAtivoS3(contemUser(chat.getUserInscritos(3)));
    }

    private static void enviarMensagensOffLine(IServidorChat chat) throws RemoteException {
        System.err.println("Verificando se ha mensagens off-line"); 
        for (IMensagem iMensagem : credenciais.getMenS1()) {
            iMensagem.setMensagem(iMensagem.getMensagem() + " (enviado off-line) ");
            chat.publicaMensagem(1, iMensagem);
        }
        
        for (IMensagem iMensagem : credenciais.getMenS2()) {
            
            iMensagem.setMensagem(iMensagem.getMensagem() + " (enviado off-line) ");
            chat.publicaMensagem(1, iMensagem);
        }
        
        for (IMensagem iMensagem : credenciais.getMenS3()) {
            
            iMensagem.setMensagem(iMensagem.getMensagem() + " (enviado off-line) ");
            chat.publicaMensagem(1, iMensagem);
        }
        credenciais.setMenS1(new ArrayList<IMensagem>());
        credenciais.setMenS2(new ArrayList<IMensagem>());
        credenciais.setMenS3(new ArrayList<IMensagem>());
        gravaDados();
    }
    
    
    private static void gravaDados(){
        if (credenciais != null){
            ServicoArquivo serve = new ServicoArquivo();
            serve.gravaArquivoCredenciaisXml(credenciais);
        }
    }
    
    private static void recuperaDados(Scanner scanner){
        try {
            System.err.println("Informe o nome do Usuario para procurar suas credencais locais.");
            ServicoArquivo serve = new ServicoArquivo();
            credenciais = serve.lerArquivoCredenciaisXml(scanner.nextLine().toString() + ".xml");
        } catch (Exception e) {
            System.err.println("Perfil nao localizado");
        }
    }
    
    private static boolean contemUser(ArrayList<IUsuario> lista){
        for (IUsuario iUsuario : lista) {
            if (iUsuario.equals(credenciais.getUser())) return true;
        }
        return false;
    }
    
    
}