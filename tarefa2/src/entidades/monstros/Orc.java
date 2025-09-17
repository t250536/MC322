package entidades.monstros;

import java.util.List;

import entidades.Personagem;
import itens.armas.Arma;

public class Orc extends Monstro {
    private int arrebentacao; // Atributo específico do Orc

    // Construtor
    public Orc(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList, int arrebentacao) {
        super(nome, forca, vida, arma, xpConcedido, dropsList);
        this.arrebentacao = arrebentacao;
    }

    // Gets e Sets
    public int getArrebentacao() {
        return arrebentacao;
    }

    // metodos
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " atacou! Dano: " + dano);
        alvo.receberDano(dano);
        arrebentacao += 3;
    }

    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        if (arrebentacao > 7) {
            alvo.receberDano(getDanototal() * 2);
            arrebentacao -= 2;
            System.out.println(getNome() + " ativou o combo de ARREBENTACAO! dano duplicado!");
            System.out.println("Dano total: " + (getDanototal() * 2));
            return true;
        }
        return false;
    }
}