package entidades.herois;
import com.rpg.itens.armas.Arma;

import entidades.Personagem;

public class Arqueiro extends Heroi {
    private int flechaFlamejante;
    
    // construtor
    public Arqueiro(String nome, int forca, int vida, Arma arma, int nivel, int experiencia, int experienciaParaProximoNivel, int sorte, int flechaFlamejante) {
    super(nome, forca, vida, arma, nivel, experiencia, experienciaParaProximoNivel, sorte);
        this.flechaFlamejante = flechaFlamejante;
    }
    // Gets e Sets
    public int getflechaFlamejante() {
        return flechaFlamejante;
    }
    // metodos
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " atacou! Dano: " + dano);
        alvo.receberDano(dano);
        flechaFlamejante += 3; // Aumenta a flechaFlamejante ao atacar
    }

    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        if (flechaFlamejante > 5) {
            System.out.println(getNome() + " atirou uma FLECHA FLAMENJANTE! e teve um Ataque somado a +35!");
            System.out.println("Dano total: " + (getDanototal() + 35));
            alvo.receberDano(getDanototal() + 35);
            flechaFlamejante -= 2; // Reduz a flechaFlamejante ao usar a habilidade
            return true;
        }
        return false;
    }
}
