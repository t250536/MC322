package entidades.monstros;

import java.util.List;

import acoes.AtaqueFisico;
import acoes.HabilidadeDeFogo;
import entidades.Personagem;
import itens.PocaoCura;
import itens.armas.Arma;

public class Dragao extends Monstro {
    public Dragao(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList) {
        super(nome, forca, vida, arma, xpConcedido, dropsList);
        
        // Definindo loot básico
        setLootBasico(new PocaoCura(15));
        
        // Definindo lista de ações no construtor
        adicionarAcao(new AtaqueFisico());
        adicionarAcao(new HabilidadeDeFogo());
    }
    
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanoTotal();
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
    
    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " usa habilidade especial - Sopro de Fogo!");
        // Causa dano em área (simulado como dano duplo)
        int danoFogo = getDanoTotal() + 20;
        alvo.receberDano(danoFogo);
        System.out.println("Sopro de Fogo causa " + danoFogo + " de dano flamejante!");
        return true;
    }
}