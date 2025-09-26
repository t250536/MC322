package entidades;//camionho do pacote
import itens.armas.Arma;

//classe abstrata Personagem
public abstract class Personagem implements Combatente {
  private String nome;
  private int forca;
  private int vida;
  private Arma arma;

  //construtor
  public Personagem(String nome, int forca, int vida, Arma arma) {
    this.nome = nome;
    this.forca = forca;
    this.vida = vida;
    this.arma = arma;
  }

  //sets e gets
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public String getNome() {
    return nome;
  }

  public void setForca(int forca) {
    this.forca = forca;
  }

  public int getForca() {
    return forca;
  }

  public void setVida(int vida) {
    this.vida = vida;
  }

  public int getVida() {
    return vida;
  }
  public void setArma(Arma arma) {
    this.arma = arma;
  }
  public Arma getArma() {
    return arma;
  }

  //metodos
  public void receberDano(int dano) {
    vida -= dano;
    if (vida < 0) {
      vida = 0;
    }
  }
  public int getDanototal(){
    if(arma != null){
      return forca + arma.getDano();
    } else {
      return forca;
    }
  }
  public void status() {
    System.out.println("Nome: " + nome);
    System.out.println("Força: " + forca);
    System.out.println("Pontos de Vida: " + vida);
    if (arma != null) {
      System.out.println("Arma equipada: " + arma.getNome() + " (Dano: " + arma.getDano() + ", Nível Mínimo: " + arma.getminNivel() + ")");
    } else {
      System.out.println("Nenhuma arma equipada.");
    }
  }public boolean estaVivo() {
    return vida > 0;
  }

  //metodo abstrato para atacar outro personagem
  public abstract void atacar(Personagem alvo);
  public abstract boolean HabilidadeEspecial(Personagem alvo);
  public abstract void escolherAcao(Combatente alvo);
}
