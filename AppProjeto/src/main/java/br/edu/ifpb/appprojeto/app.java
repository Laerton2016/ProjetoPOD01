/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.appprojeto;

import br.edu.ifpb.Shareprojeto01.IArquivoService;
import com.dropbox.core.DbxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 *
 * @author laerton
 */
public class app {
   

    public static void main(String args[]) throws DbxException, FileNotFoundException, IOException {
    
         try {
        Registry registry = LocateRegistry.createRegistry(1097);
        IArquivoService server = new ServiceArquivo();
        Naming.rebind("rmi://localhost:1097/Servico", server);
        } catch (RemoteException e){
            System.out.println("Trouble: "+e);
        }
        /*
        ServiceArquivo service = new ServiceArquivo();
        FileDropBox fdb = new FileDropBox();
        
        try {
            // Grupo g2 = service.lerArquivoGrupoXml("grupo2.xml");
            //g2.publicMensagem(new Mensagem((Usuario) g2.getUsuarios().get(0), "Outra mensagem"));
            //service.gravaArquivoGrupoXml(g2);
            //fdb.uparFile("teste.xml");
            FileGoogleDrive2 fgd2 = FileGoogleDrive2.getInstance();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        */
                 
        
      
        
       
    }
    
    /*
    public static void main(String[] args) throws DbxException, IOException {
        /*Grupo grupo = new Grupo(1, "teste");
        grupo.addUsuario(new Usuario(1, "Laerton", "laerton240311@gmail.com "));
        Usuario user = (Usuario) grupo.getUsuarios().get(0);
        grupo.publicMensagem(new Mensagem(user, "Este 'e um teste."));
        ServiceArquivo service = new ServiceArquivo();
        service.gravaArquivoGrupoXml(grupo);
        Grupo g2 = service.lerArquivoGrupoXml(grupo.getNome() + ".xml");
        System.out.println(g2.getNome());*/
        
            //ativeDropBox();
            
            /*DbxClient client;
            String token = "Ubte-SVRALAAAAAAAAAAGo1HvERnAVvtTnoElOXPW-8";

            DbxRequestConfig config = new DbxRequestConfig("chatpod", 
                                      Locale.getDefault().toString());
            client = new DbxClient(config, token);
        
           // System.out.println(e.getMessage());
           //upando arquivo
            String fileToUpload = "teste.xml";
            File f = new File(fileToUpload);
            FileInputStream inputStream = new FileInputStream(f);

            String pathDest = "/Aplicativos/chatpod";

            try {
             DbxEntry.File uploadedFile = client.uploadFile(pathDest, 
                                                            DbxWriteMode.add(), 
                                                            f.length(), 
                                                            inputStream);

             System.out.println("Uploaded: " + uploadedFile.toString());
            } finally {
             inputStream.close();
            }
            
   
    }*/
    
    
    /*
    private static void ativeDropBox() throws DbxException, IOException{
         // Get your app key and secret from the Dropbox developers website.
        final String APP_KEY = "o2fectsiq2upilj";
        final String APP_SECRET = "xb5dwnudg400fn3";

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
            Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

        // Have the user sign in and authorize your app.
        String authorizeUrl = webAuth.start();
        System.out.println("1. Go to: " + authorizeUrl);
        System.out.println("2. Click \"Allow\" (you might have to log in first)");
        System.out.println("3. Copy the authorization code.");
        String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

        // This will fail if the user enters an invalid authorization code.
        DbxAuthFinish authFinish = webAuth.finish(code);
        String accessToken = authFinish.accessToken;

        DbxClient client = new DbxClient(config, accessToken);

        System.out.println("Linked account: " + client.getAccountInfo().displayName);

        /*
        File inputFile = new File("working-draft.txt");
        FileInputStream inputStream = new FileInputStream(inputFile);
        try {
            DbxEntry.File uploadedFile = client.uploadFile("/magnum-opus.txt",
                DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        } finally {
            inputStream.close();
        }
        

        DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
        System.out.println("Files in the root path:");
        for (DbxEntry child : listing.children) {
            System.out.println("	" + child.name + ": " + child.toString());
        }

        
        FileOutputStream outputStream = new FileOutputStream("magnum-opus.txt");
        try {
            DbxEntry.File downloadedFile = client.getFile("/magnum-opus.txt", null,
                outputStream);
            System.out.println("Metadata: " + downloadedFile.toString());
        } finally {
            outputStream.close();
        }
        
        
        
    }*/
}
