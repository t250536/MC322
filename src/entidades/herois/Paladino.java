package entidades.herois;
import entidades.Personagem;

public class Paladino extends Heroi {
    private int cura;
    
    // construtor
    public Paladino(String nome, int forca, int vida, int experiencia,int cura) {
        super(nome, forca, vida, experiencia); // Chama construtor do Heroi
        this.cura = cura;
    }
    // Gets e Sets
    public int getcura() {
        return cura;
    }
    // metodos
    @Override
    public void atacar(Personagem alvo) {
        int dano = getForca();
        System.out.println(getNome() + " atacou com a forca divina! Dano: " + dano);
        alvo.receberDano(dano);
        cura += 4; // Aumenta a fúria ao atacar
    }

    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        if (cura > 6) {
        System.out.println(getNome() + " ativou a CURA DIVINA! e teve a vida curada em 55+!");
        setVida(getVida() + 55);
        cura -= 3;
        return true;
        }
        return false;
    }
}
