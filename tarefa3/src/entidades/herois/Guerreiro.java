package entidades.herois;

import entidades.Personagem;
import itens.armas.Arma;

public class Guerreiro extends Heroi {
    
    public Guerreiro(String nome) {
        super(nome, 15, 100, null, 1, 0, 100, 0);
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
        receberCura(20);
        return true;
    }
    
    @Override
    public void escolherAcao(Combatente alvo) {
        // Simples: sempre ataca
        atacar((Personagem) alvo);
    }
}

//ok