package entidades.monstros;
import com.rpg.entidades.Personagem;
import com.rpg.entidades.monstros.Monstro;

public class Globin extends Monstro {
    private int gosmatoxica;
    
    // construtor
    public Globin(String nome, int forca, int vida, int xpConcedido,int gosmatoxica) {
        super(nome, forca, vida, xpConcedido); // Chama construtor do Monstro
        this.gosmatoxica = gosmatoxica;
    }
    // Gets e Sets
    public int getGosmatoxica() {
        return gosmatoxica;
    }
    // metodos
    @Override
    public void atacar(Personagem alvo) {
        int dano = getForca();
        System.out.println(getNome() + " atacou com um galho! Dano: " + dano);
        alvo.receberDano(dano);
        gosmatoxica += 3;
    }

    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        if (gosmatoxica > 5) {
            System.out.println(getNome() + " ativou a GOSMA TOXICA! Teve um Ataque somado a +30!");
            System.out.println("Dano total: " + (getForca() + 30));
            alvo.receberDano(getForca() + 30);
            gosmatoxica -= 2;
            return true;
        }
        return false;
        // Lógica de habilidade especial aqui
        //condicional para verificar se a habilidadade é suficiente
    }
}