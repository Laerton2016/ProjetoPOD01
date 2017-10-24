/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.appprojeto;


import br.edu.ifpb.Shareprojeto01.Grupo;
import br.edu.ifpb.Shareprojeto01.IGrupo;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class ServiceArquivo {
    private IGrupo grupo;
    
    
    /***
     * Cria e grava o arquivo grupo
     * @param grupo grupo para criar o xml
     */
    public void gravaArquivoGrupoXml(IGrupo grupo)
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
    
    public Grupo lerArquivoGrupoXml(String arquivo){
        Grupo grupo =null;
        try {
            XStream xStream = new XStream();
            xStream.alias("grupo", IGrupo.class);
            FileInputStream file = new FileInputStream(new File(arquivo));
            grupo = (Grupo) xStream.fromXML(file);
        } catch (Exception ex) {
            Logger.getLogger(ServiceArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return grupo;
    }
    
    
    
}
