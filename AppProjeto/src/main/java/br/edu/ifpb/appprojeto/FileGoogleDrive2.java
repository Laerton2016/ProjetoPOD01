/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.appprojeto;

//import br.usp.poli.lta.cereda.macro.model.exceptions.TextRetrievalException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author laerton
 */
public class FileGoogleDrive2
{
    // define uma única instância do controle
    private static final FileGoogleDrive2 instance = new FileGoogleDrive2();
    
    // serviço do Google Drive
    private Drive service;
    
    // exceção a ser lançada no escopo da instância (definida
    // para evitar tratamento no singleton)
    private static Exception exception;
    private static final String ID = "503030267584-knthpo6ugqv7de4p5qtdj91tlafm5707.apps.googleusercontent.com";
    private static final String SECRET = "BSSigt8oW8GZ4PM-DVeQ_Gds";
    
    /**
     * Construtor.
     */
    private FileGoogleDrive2()  {

        // originalmente, o serviço é inválido
        service = null;
        
        // tenta obter a configuração do Google Drive a partir de um arquivo de
        // propriedades localizado no diretório de usuário
        File config = new File(System.getProperty("/home/laerton/IFPB2017/POD/Projeto01/ProjetoPOD01/AppProjeto/").
               concat(File.separator).concat("client_id.json"));
        //File config = new File("client_id.json");
        
        // se a configuração não existe, lançar exceção
        if (false) {
           exception = new Exception(
                    "Não encontrei o arquivo de configuração do Google Drive ('me-driveconfig.properties') no diretório do usuário."
            );
        }
        else {
            
            // a configuração existe, carrega o mapa de chaves e valores e
            // faz a autenticação no Google Drive
          //  Properties properties = new Properties();
            try {
                
                // obtém o arquivo de propriedades e verifica se as chaves são
                // válidas
                //properties.load(new FileReader(config));
                if (true) {
                    
                    // cria as chaves de acesso de acordo com as informações
                    // contidas no arquivo de propriedades
                    GoogleClientSecrets secrets = new GoogleClientSecrets();
                    GoogleClientSecrets.Details details = 
                            new GoogleClientSecrets.Details();
                    details.setClientId(ID);
                    details.setClientSecret(SECRET);
                    secrets.setInstalled(details);
                    
                    // cria um novo transporte HTTP e a factory do JSON para
                    // parsing da API do Google Drive
                    HttpTransport transport = GoogleNetHttpTransport.
                            newTrustedTransport();
                    JsonFactory factory = JacksonFactory.getDefaultInstance();
                    
                    // define o diretório do usuário como o local para
                    // armazenamento das credenciais de autenticação do
                    // Google Drive
                    FileDataStoreFactory store =
                            new FileDataStoreFactory(
                                    new File("client_id.Json")
                            );
                    
                    // cria um fluxo de autorização do Google a partir de todas
                    // as informações coletadas anteriormente
                    GoogleAuthorizationCodeFlow flow =
                            new GoogleAuthorizationCodeFlow.Builder(
                                    transport,
                                    factory,
                                    secrets,
                                    Collections.singleton(
                                            DriveScopes.DRIVE_FILE
                                    )
                            ).setDataStoreFactory(store).build();
                    
                    // cria uma nova credencial a partir da autorização
                    Credential credential =
                            new AuthorizationCodeInstalledApp(
                                    flow,
                                    new LocalServerReceiver()
                            ).authorize("user");
                    
                    // cria efetivamente um novo serviço do Google Drive
                    // utilizando as informações coletadas anteriormente
                    service = new Drive.Builder(
                            transport,
                            factory,
                            credential
                    ).setApplicationName("macro-expander/1.0").build();
                }
                else {
                    // as chaves são inválidas, configura uma nova exceção
                    exception = new Exception(
                            "O arquivo de configuração do Google Drive ('me-driveconfig.properties') não possui as chaves 'id' e 'secret'."
                    );
                }
            } catch (IOException ex) {
                // erro de leitura do arquivo de configuração
                exception = new Exception(
                        "Não consegui ler o arquivo de configuração do Google Drive ('me-driveconfig.properties') no diretório do usuário."
                );
            } catch (GeneralSecurityException ex) {
                // erro de conexão
                exception = new Exception(
                        "Não foi possível estabelecer uma conexão segura."
                );
            } catch (Exception ex) {
                Logger.getLogger(FileGoogleDrive2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    /**
     * Obtém a instância do controlador do Google Drive.
     * @return A instância do controlador do Google Drive.
     * @throws TextRetrievalException Ocorreu um erro na recuperação do texto.
     */
    public static FileGoogleDrive2 getInstance()
            throws Exception {
        if (exception == null) {
            return instance;
        }
        else {
            throw exception;
        }
    }

    /**
     * Obtém o serviço do Google Drive.
     * @return Serviço do Google Drive.
     */
    public Drive getService() {
        return service;
    }
}
