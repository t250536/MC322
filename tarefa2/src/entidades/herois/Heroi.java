package entidades.herois;//caminho do pacote
import entidades.Personagem;

public abstract class Heroi extends Personagem {
  private int experiencia;

  //construtor
  public Heroi(String nome, int forca, int vida, int experiencia) {
    super(nome, forca, vida);//chamar o construtor da superclasse Personagem
    this.experiencia = experiencia;
  }

  //gets e sets
  //metodos
  public void ganharExperiencia(int pt_exp) {
    experiencia += pt_exp;
  }
  @Override
  public void status() {
    super.status(); // Chama o método da superclasse Personagem
    System.out.println("Experiência: " + experiencia);

  }
  @Override
  public abstract boolean HabilidadeEspecial(Personagem alvo);//metodo abstrato para habilidade especial
}
