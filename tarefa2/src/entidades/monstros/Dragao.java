package entidades.monstros;

import java.util.List;

import com.rpg.itens.armas.Arma;

import entidades.Personagem;

public class Dragao extends Monstro {
    private int bafoDeFogo;

    // construtor
    public Dragao(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList, int bafoDeFogo) {
        super(nome, forca, vida, arma, xpConcedido, dropsList);
        this.bafoDeFogo = bafoDeFogo;
    }

    // Gets e Sets
    public int getBafodeFogo() {
        return bafoDeFogo;
    }

    // metodos
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " atacou! Dano: " + dano);
        alvo.receberDano(dano);
        bafoDeFogo += 3;
    }

    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        if (bafoDeFogo > 9) {
            System.out.println(getNome() + " cuspiu FOGO! Teve um Ataque somado a +40!");
            System.out.println(" Dano total: " + (getDanototal() + 40));
            alvo.receberDano(getDanototal() + 40);
            bafoDeFogo -= 4;
            return true;
        }
        return false;
    }
}