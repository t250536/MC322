package entidades.monstros;

import java.util.List;

import acoes.AtaqueFisico;
import acoes.Furia;
import entidades.Personagem;
import itens.PocaoCura;
import itens.armas.Arma;

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
        int dano = getDanoTotal();
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
    
    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " usa habilidade especial - Golpe Esmagador!");
        // Causa dano massivo ignorando parte da defesa
        int danoMassivo = getDanoTotal() + 15;
        alvo.receberDano(danoMassivo);
        System.out.println("Golpe Esmagador causa " + danoMassivo + " de dano devastador!");
        return true;
    }
}