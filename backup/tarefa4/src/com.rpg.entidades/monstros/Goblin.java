package com.rpg.entidades.monstros;

import com.rpg.itens.armas.Arma;
import com.rpg.itens.PocaoCura;

import java.util.ArrayList;
import java.util.List;

import com.rpg.acoes.AtaqueFisico;
import com.rpg.acoes.GosmaToxica;
import com.rpg.entidades.Personagem;

import entidades.monstros.Monstro;

public class Goblin extends Monstro {
    //construtor sem parametros
    public Goblin() {
        super("Goblin", 8, 30, null, 20, new ArrayList<>());
        
        // Definindo loot básico
        setLootBasico(new PocaoCura(15));
        
        // Definindo lista de ações no construtor
        adicionarAcao(new AtaqueFisico());
        adicionarAcao(new GosmaToxica());
    }
    //construtor com parametros
    public Goblin(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList) {
        super(nome, forca, vida, arma, xpConcedido, dropsList);
        
        // Definindo loot básico
        setLootBasico(new PocaoCura(15));
        
        // Definindo lista de ações no construtor
        adicionarAcao(new AtaqueFisico());
        adicionarAcao(new GosmaToxica());
    }
    
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
    
    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " usa habilidade especial!");
        return true;
    }
}