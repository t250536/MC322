package entidades.herois;
import acoes.AtaqueFisico;
import acoes.FlechaFamejante;
import entidades.Personagem;
import itens.armas.Arma;

public class Arqueiro extends Heroi {
    
    // construtor
    public Arqueiro(String nome, int forca, int vida, Arma arma, int nivel, int experiencia, int experienciaParaProximoNivel, int sorte) {
        super(nome, forca, vida, arma, nivel, experiencia, experienciaParaProximoNivel, sorte);
        
        // CORREÇÃO: Usar adicionarAcao em vez de getAcoes().add()
        this.adicionarAcao(new AtaqueFisico());
        this.adicionarAcao(new FlechaFamejante());
    }
    
    @Override
    public void atacar(Personagem alvo) {
        // CORREÇÃO: getDanoTotal() em vez de getDanototal()
        int dano = getDanoTotal();
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
    
    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " usa habilidade especial - Chuva de Flechas!");
        // Causa dano múltiplo
        // CORREÇÃO: getDanoTotal() em vez de getDanototal()
        int danoMultiplo = getDanoTotal() / 2;
        for (int i = 0; i < 3; i++) {
            alvo.receberDano(danoMultiplo);
            System.out.println("Flecha " + (i + 1) + " causa " + danoMultiplo + " de dano!");
        }
        return true;
    }
}