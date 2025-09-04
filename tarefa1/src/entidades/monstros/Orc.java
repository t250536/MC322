package entidades.monstros;
import entidades.Personagem;

public class Orc extends Monstro {
    private int arrebentacao; // Atributo específico do Orc
    
    // construtor
    public Orc(String nome, int forca, int vida, int xpConcedido,int arrebentacao) {
        super(nome, forca, vida, xpConcedido); // Chama construtor do Monstro
        this.arrebentacao = arrebentacao;
    }
    // Gets e Sets
    public int getArrebentacao() {
        return arrebentacao;
    }
    // metodos
    @Override
    public void atacar(Personagem alvo) {
        int dano = getForca();
        System.out.println(getNome() + " atacou com um soco! Dano: " + dano);
        alvo.receberDano(dano);
        arrebentacao += 3;
    }

    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        if (arrebentacao > 7) {
            alvo.receberDano(getForca()*2);
            arrebentacao -= 2;
            System.out.println(getNome() + " ativou o combo de ARREBENTACAO! dano duplicado!");
            System.out.println("Dano total: " + (getForca()*2));
            return true;
        }
        return false;
    }
}