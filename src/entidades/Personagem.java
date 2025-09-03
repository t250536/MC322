package entidades;//camionho do pacote

//classe abstrata Personagem
public abstract class Personagem {
  private String nome;
  private int forca;
  private int vida;

  //construtor
  public Personagem(String nome, int forca, int vida) {
    this.nome = nome;
    this.forca = forca;
    this.vida = vida;
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

  //metodos
  public void receberDano(int dano) {
    vida -= dano;
    if (vida < 0) {
      vida = 0;
    }
  }
  public void status() {
    System.out.println("Nome: " + nome);
    System.out.println("Força: " + forca);
    System.out.println("Pontos de Vida: " + vida);
  }

  //metodo abstrato para atacar outro personagem
  public abstract void atacar(Personagem alvo);
  public abstract boolean HabilidadeEspecial(Personagem alvo);
  public abstract void ganharExperiencia(int xp);
  public abstract int getXpConcedido();
}
