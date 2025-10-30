package entidades.herois;
import entidades.Personagem;
import itens.armas.Arma;

public class Paladino extends Heroi {
    private int cura;
    
    // construtor
    public Paladino(String nome, int forca, int vida, Arma arma, int nivel, int experiencia, int experienciaParaProximoNivel, int sorte, int cura) {
    super(nome, forca, vida, arma, nivel, experiencia, experienciaParaProximoNivel, sorte);
    this.cura = cura;
}
    // Gets e Sets
    public int getcura() {
        return cura;
    }
    // metodos
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " atacou! Dano: " + dano);
        alvo.receberDano(dano);
        cura += 4; // Aumenta a fúria ao atacar
    }

    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        if (cura > 6) {
        System.out.println(getNome() + " ativou a CURA DIVINA! e teve a vida curada em 45+!");
        setVida(getVida() + 45);
        cura -= 3;
        return true;
        }
        return false;
    }
}
