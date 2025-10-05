package entidades.herois;

import entidades.Personagem;
import itens.armas.Arma;
import acoes.AtaqueFisico;
import acoes.HabilidadeDeFogo;

public class Guerreiro extends Heroi {
    
    public Guerreiro(String nome) {
        super(nome, 15, 100, null, 1, 0, 100, 0);
        
        // Definindo lista de ações no construtor
        adicionarAcao(new AtaqueFisico());
        adicionarAcao(new HabilidadeDeFogo());
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