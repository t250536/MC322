package entidades.herois;
import com.rpg.itens.armas.Arma;

import entidades.Personagem;

public class Guerreiro extends Heroi {
    private int furia;
    
    // construtor
    public Guerreiro(String nome, int forca, int vida, Arma arma, int nivel, int experiencia, int experienciaParaProximoNivel, int sorte, int furia) {
    super(nome, forca, vida, arma, nivel, experiencia, experienciaParaProximoNivel, sorte);
        this.furia = furia;
    }
    // Gets e Sets
    public int getFuria() {
        return furia;
    }
    // metodos
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " atacou! Dano: " + dano);
        alvo.receberDano(dano);
        furia += 3; // Aumenta a fúria ao atacar
    }

    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        //condicional que verifica se a habilidade é suficiente
        if (furia > 6) {
            System.out.println(getNome() + " ativou a FÚRIA BERSERK! e teve um Ataque triplicado!");
            System.out.println("Dano total: " + (getDanototal() * 3));
            alvo.receberDano(getDanototal() * 3);
            furia -=2; // Reduz a fúria ao usar a habilidade
            return true;
        }
        return false;
    }
}
