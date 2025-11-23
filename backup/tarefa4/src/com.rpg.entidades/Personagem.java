package com.rpg.entidades;

import com.rpg.itens.armas.Arma;
import com.rpg.entidades.Personagem;
import com.rpg.interfaces.combate.Combatente;

//classe abstrata Personagem agora implementa Combatente
public abstract class Personagem implements Combatente {
    private String nome;
    private int forca;
    private int vida;
    private Arma arma;

    //construtor
    public Personagem(String nome, int forca, int vida, Arma arma) {
        this.nome = nome;
        this.forca = forca;
        this.vida = vida;
        this.arma = arma;
    }

    // Implementação dos métodos da interface Combatente
    
    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public boolean estaVivo() {
        return vida > 0;
    }

    @Override
    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) {
            vida = 0;
        }
        System.out.println(nome + " recebeu " + dano + " de dano! Vida: " + vida);
    }

    @Override
    public void receberCura(int cura) {
        vida += cura;
        System.out.println(nome + " recebeu " + cura + " de cura! Vida: " + vida);
    }

    // Método abstrato da interface Combatente - deve ser implementado pelas subclasses
    public abstract void escolherAcao(Combatente alvo);

    // Mantendo os métodos existentes da sua classe
    
    //sets e gets
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getForca() {
        return forca;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }
    
    public void setArma(Arma arma) {
        this.arma = arma;
    }
    
    public Arma getArma() {
        return arma;
    }

    //metodos
    public int getDanototal(){
        if(arma != null){
            return forca + arma.getDano();
        } else {
            return forca;
        }
    }
    
    public void status() {
        System.out.println("Nome: " + nome);
        System.out.println("Força: " + forca);
        System.out.println("Pontos de Vida: " + vida);
        if (arma != null) {
            System.out.println("Arma equipada: " + arma.getNome() + " (Dano: " + arma.getDano() + ", Nível Mínimo: " + arma.getminNivel() + ")");
        } else {
            System.out.println("Nenhuma arma equipada.");
        }
    }

    //mantendo os métodos abstratos existentes
    public abstract void atacar(Personagem alvo);
    public abstract boolean HabilidadeEspecial(Personagem alvo);
}