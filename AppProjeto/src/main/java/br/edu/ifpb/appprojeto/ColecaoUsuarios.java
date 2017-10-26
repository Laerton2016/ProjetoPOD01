/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.appprojeto;

import br.edu.ifpb.Shareprojeto01.IUsuario;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;

/**
 *
 * @author laerton
 */
@XStreamAlias("colecaoUsuarios")
public class ColecaoUsuarios {
    private ArrayList<IUsuario> usuarios = new ArrayList<>();

    public ColecaoUsuarios() {
    }

    public ArrayList<IUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<IUsuario> usuarios) {
        this.usuarios = usuarios;
    }
    
}
