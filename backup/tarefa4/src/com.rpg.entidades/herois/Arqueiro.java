package com.rpg.entidades.herois;
import com.rpg.acoes.AtaqueFisico;
import com.rpg.acoes.FlechaFamejante;
import com.rpg.entidades.Personagem;

import entidades.herois.Heroi;
import itens.armas.Arma;

public class Arqueiro extends Heroi {
    
    // construtor
    public Arqueiro(String nome, int forca, int vida, Arma arma, int nivel, int experiencia, int experienciaParaProximoNivel, int sorte) {
        super(nome, forca, vida, arma, nivel, experiencia, experienciaParaProximoNivel, sorte);
        
        // Definindo lista de ações no construtor
        this.getAcoes().add(new AtaqueFisico());
        this.getAcoes().add(new FlechaFamejante());
    }
    
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
    
    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " usa habilidade especial - Chuva de Flechas!");
        // Causa dano múltiplo
        int danoMultiplo = getDanototal() / 2;
        for (int i = 0; i < 3; i++) {
            alvo.receberDano(danoMultiplo);
            System.out.println("Flecha " + (i + 1) + " causa " + danoMultiplo + " de dano!");
        }
        return true;
    }
}