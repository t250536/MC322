package entidades.herois;
import acoes.AtaqueFisico;
import acoes.CuraDivina;
import entidades.Personagem;
import itens.armas.Arma;

public class Paladino extends Heroi {
    
    // construtor
    public Paladino(String nome, int forca, int vida, Arma arma, int nivel, int experiencia, int experienciaParaProximoNivel, int sorte) {
        super(nome, forca, vida, arma, nivel, experiencia, experienciaParaProximoNivel, sorte);
        
        // Definindo lista de ações no construtor
        this.getAcoes().add(new AtaqueFisico());
        this.getAcoes().add(new CuraDivina());
    }
    
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
    
    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " usa habilidade especial - Luz Sagrada!");
        // Cura o próprio paladino em 50 pontos de vida
        this.receberCura(50);
        return true;
    }
}