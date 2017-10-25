/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.cliente;

import br.edu.ifpb.Shareprojeto01.Grupo;
import br.edu.ifpb.Shareprojeto01.IGrupo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class ServicoArquivo {
    
    public void gravaArquivoCredenciaisXml(Credencias credenciais)
    {
        XStream xStream = new XStream();
        xStream.alias("credenciais", Credencias.class);
        File arquivo = new File(credenciais.getUser().getNome() +".xml");
        FileOutputStream gravar;
        try {
            gravar = new FileOutputStream(arquivo);
            gravar.write(xStream.toXML(credenciais).getBytes());
            gravar.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Credencias lerArquivoCredenciaisXml(String arquivo) throws FileNotFoundException {
        
        Credencias credencias =null;
            XStream xStream = new XStream();
            xStream.addPermission (NoTypePermission.NONE);
            xStream.addPermission (AnyTypePermission.ANY);    
            xStream.allowTypeHierarchy (Credencias.class);
            xStream.alias("credenciais", Credencias.class);
            FileInputStream file = new FileInputStream(new File(arquivo));
            credencias = (Credencias) xStream.fromXML(file);
        
        return credencias;
    }
}
