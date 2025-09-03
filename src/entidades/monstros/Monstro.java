package entidades.monstros;//caminho do pacote
import entidades.Personagem;

public abstract class Monstro extends Personagem {
  public int xpConcedido;

  //construtor
  public Monstro(String nome, int forca, int vida, int xpConcedido) {
    super(nome, forca, vida);//chamar o construtor da superclasse Personagem
    this.xpConcedido = xpConcedido;
  }

  //gets e sets
  public int getXpConcedido() {
    return xpConcedido;
  }
  //metodos
  @Override
  //monstros nao ganham experiencia
  public void ganharExperiencia(int xp) {
      return;
  }

  @Override
  public void status() {
    super.status(); // Chama o método da superclasse Personagem
    System.out.println("XP Concedido: " + xpConcedido);

  }
  @Override
  public abstract boolean HabilidadeEspecial(Personagem alvo);//metodo abstrato para habilidade especial
}
