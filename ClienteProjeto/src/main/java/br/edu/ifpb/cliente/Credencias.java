/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.cliente;

import br.edu.ifpb.Shareprojeto01.IMensagem;
import br.edu.ifpb.Shareprojeto01.IUsuario;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;

/**
 *
 * @author laerton
 */
@XStreamAlias("credenciais")
public class Credencias {
    private  ArrayList<IMensagem> menS1 = new ArrayList<>();
    private  ArrayList<IMensagem> menS2 = new ArrayList<>();
    private  ArrayList<IMensagem> menS3 = new ArrayList<>();
    private  boolean ativoS1 = false;
    private  boolean ativoS2 = false;
    private  boolean ativoS3 = false;
    private  IUsuario user;

    public Credencias() {
    }

    
    public Credencias(IUsuario user) {
        this.user = user;
    }

    
    
    public ArrayList<IMensagem> getMenS1() {
        return menS1;
    }

    public void setMenS1(ArrayList<IMensagem> menS1) {
        this.menS1 = menS1;
    }

    public ArrayList<IMensagem> getMenS2() {
        return menS2;
    }

    public void setMenS2(ArrayList<IMensagem> menS2) {
        this.menS2 = menS2;
    }

    public ArrayList<IMensagem> getMenS3() {
        return menS3;
    }

    public void setMenS3(ArrayList<IMensagem> menS3) {
        this.menS3 = menS3;
    }

    public boolean isAtivoS1() {
        return ativoS1;
    }

    public void setAtivoS1(boolean ativoS1) {
        this.ativoS1 = ativoS1;
    }

    public boolean isAtivoS2() {
        return ativoS2;
    }

    public void setAtivoS2(boolean ativoS2) {
        this.ativoS2 = ativoS2;
    }

    public boolean isAtivoS3() {
        return ativoS3;
    }

    public void setAtivoS3(boolean ativoS3) {
        this.ativoS3 = ativoS3;
    }

    public IUsuario getUser() {
        return user;
    }

    public void setUser(IUsuario user) {
        this.user = user;
    }
    
    public void addMensagem(IMensagem men,int idSala){
        if (idSala == 1){
            menS1.add(men);
        }else if (idSala ==2){
            menS2.add(men);
        }else if (idSala == 3){
            menS3.add(men);
        }
    }
    
}
