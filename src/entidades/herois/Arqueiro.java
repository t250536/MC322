package entidades.herois;
import entidades.Personagem;

public class Arqueiro extends Heroi {
    private int flechaFlamejante;
    
    // construtor
    public Arqueiro(String nome, int forca, int vida, int experiencia,int flechaFlamejante) {
        super(nome, forca, vida, experiencia); // Chama construtor do Heroi
        this.flechaFlamejante = flechaFlamejante;
    }
    // Gets e Sets
    public int getflechaFlamejante() {
        return flechaFlamejante;
    }
    // metodos
    @Override
    public void atacar(Personagem alvo) {
        int dano = getForca();
        System.out.println(getNome() + " atirou uma flecha normal! Dano: " + dano);
        alvo.receberDano(dano);
        flechaFlamejante += 3; // Aumenta a flechaFlamejante ao atacar
    }

    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        if (flechaFlamejante > 5) {
            System.out.println(getNome() + " atirou uma FLECHA FLAMENJANTE! e teve um Ataque somado a +35!");
            System.out.println("Dano total: " + (getForca() + 35));
            alvo.receberDano(getForca() + 35);
            flechaFlamejante -= 2; // Reduz a flechaFlamejante ao usar a habilidade
            return true;
        }
        return false;
    }
}
