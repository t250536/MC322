package entidades.herois;

import entidades.Personagem;
import itens.armas.Arma;
import acoes.AtaqueFisico;
import acoes.Furia;

public class Guerreiro extends Heroi {
    
    public Guerreiro(String nome, int forca, int vida, Arma arma, int nivel, int experiencia, int experienciaParaProximoNivel, int sorte) {
        super(nome, forca, vida, arma, nivel, experiencia, experienciaParaProximoNivel, sorte);
        
        // Definindo lista de ações no construtor
        this.getAcoes().add(new AtaqueFisico());
        this.getAcoes().add(new Furia());
    }
    
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
    
    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " usa habilidade especial - Golpe Poderoso!");
        // Causa dano extra
        int danoExtra = getDanototal() + 15;
        alvo.receberDano(danoExtra);
        System.out.println("Golpe Poderoso causa " + danoExtra + " de dano!");
        return true;
    }
}