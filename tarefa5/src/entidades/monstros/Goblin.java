package entidades.monstros;

import entidades.Personagem;
import itens.armas.Arma;
import itens.PocaoCura;
import acoes.AtaqueFisico;
import acoes.GosmaToxica;
import java.util.ArrayList;
import java.util.List;

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
        int dano = getDanoTotal();
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
    
    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " usa habilidade especial - Ataque Sorrateiro!");
        // Causa dano extra e reduz a força do alvo temporariamente
        int danoExtra = getDanoTotal() + 5;
        alvo.receberDano(danoExtra);
        System.out.println("Ataque Sorrateiro causa " + danoExtra + " de dano!");
        return true;
    }
}