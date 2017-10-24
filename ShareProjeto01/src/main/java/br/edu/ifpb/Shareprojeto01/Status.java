/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.Shareprojeto01;

/**
 *
 * @author laerton
 */
public enum Status {
    PENDETE(0), ENVIADO(1), CONFIRMADO(2);
    private int value;

    private Status(int value) {
        this.value = value;
    }
    
}
