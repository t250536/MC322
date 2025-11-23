package com.rpg.itens.armas; //caminho do pacote

public abstract class Arma {
    private int dano, minNivel;
    private String nome;

    //construtor
    public Arma(int dano, int minNivel,String nome){
        this.dano = dano;
        this.minNivel = minNivel;
        this.nome = nome;
    }

    //sets e gets
    public String getNome(){
        return nome;
    }
    public int getDano(){
        return dano;
    }

    public void setDano(int dano){
        this.dano = dano;
    }

    public int getminNivel(){
        return minNivel;
    }

    public void setminNivel(int minNivel){
        this.minNivel = minNivel;
    }

}