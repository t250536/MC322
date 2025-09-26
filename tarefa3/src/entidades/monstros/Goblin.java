package entidades.monstros;

import java.util.List;

import entidades.Personagem;
import itens.armas.Arma;

public class Goblin extends Monstro {
    private int gosmatoxica;

    // construtor
    public Goblin(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList, int gosmatoxica) {
        super(nome, forca, vida, arma, xpConcedido, dropsList);
        this.gosmatoxica = gosmatoxica;
    }

    // Gets e Sets
    public int getGosmatoxica() {
        return gosmatoxica;
    }

    // metodos
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " atacou! Dano: " + dano);
        alvo.receberDano(dano);
        gosmatoxica += 3;
    }

    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        if (gosmatoxica > 5) {
            System.out.println(getNome() + " ativou a GOSMA TOXICA! Teve um Ataque somado a +30!");
            System.out.println("Dano total: " + (getDanototal() + 30));
            alvo.receberDano(getDanototal() + 30);
            gosmatoxica -= 2;
            return true;
        }
        return false;
        // Lógica de habilidade especial aqui
        // condicional para verificar se a habilidadade é suficiente
    }
}