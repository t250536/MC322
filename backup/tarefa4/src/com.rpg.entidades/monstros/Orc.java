package com.rpg.entidades.monstros;

import java.util.List;

import com.rpg.acoes.AtaqueFisico;
import com.rpg.acoes.Furia;
import com.rpg.entidades.Personagem;

import com.rpg.entidades.monstros.Monstro;
import com.rpg.itens.PocaoCura;
import com.rpg.itens.armas.Arma;

public class Orc extends Monstro {
    public Orc(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList) {
        super(nome, forca, vida, arma, xpConcedido, dropsList);
        
        // Definindo loot básico
        setLootBasico(new PocaoCura(15));
        
        // Definindo lista de ações no construtor
        adicionarAcao(new AtaqueFisico());
        adicionarAcao(new Furia());
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