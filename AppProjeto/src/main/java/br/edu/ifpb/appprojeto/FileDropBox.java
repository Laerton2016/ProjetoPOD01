/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.appprojeto;


import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author laerton
 */
public class FileDropBox {
   private static final String ACCESS_TOKEN = "Ubte-SVRALAAAAAAAAAAE-V_SaDtiL1post07TWXPtOvZB-qAeqDwBr73UBFm8Dc";
   
   /***
    * envia um arquivo para conta do dropbox
    * @param arquivo - Endere'co com o nome do arquivo.
    * @return booleano de confirma'cao.
    */
   public boolean uparFile(String arquivo){
       DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
       DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
       try (InputStream in = new FileInputStream(arquivo)) {
            FileMetadata metadata = (FileMetadata) client.files().delete("/" + arquivo);
            metadata = client.files().uploadBuilder("/"+arquivo).uploadAndFinish(in);
       } catch (Exception ex) {
            return false;
       } 
       return true;
   }
   
}
