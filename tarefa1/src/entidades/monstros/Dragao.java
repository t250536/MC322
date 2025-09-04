package entidades.monstros;
import entidades.Personagem;

public class Dragao extends Monstro {
    private int bafoDeFogo;
    
    // construtor
    public Dragao(String nome, int forca, int vida, int xpConcedido,int bafoDeFogo) {
        super(nome, forca, vida, xpConcedido); // Chama construtor do Monstro
        this.bafoDeFogo = bafoDeFogo;
    }
    // Gets e Sets
    public int getBafodeFogo() {
        return bafoDeFogo;
    }
    // metodos
    @Override
    public void atacar(Personagem alvo) {
        int dano = getForca();
        System.out.println(getNome() + " atacou com a calda! Dano: " + dano);
        alvo.receberDano(dano);
        bafoDeFogo += 3;
    }

    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        if (bafoDeFogo > 9) {
            System.out.println(getNome() + " cuspiu FOGO! Teve um Ataque somado a +40!");
            System.out.println(" Dano total: " + (getForca() + 40));
            alvo.receberDano(getForca() + 40);
            bafoDeFogo -= 4;
             return true;
        }
        return false;
    }
}