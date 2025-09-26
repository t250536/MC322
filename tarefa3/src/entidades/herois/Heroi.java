package entidades.herois;//caminho do pacote

import entidades.Personagem;
import itens.armas.Arma;
import entidades.interfaces.Combatente;
import entidades.acoes.AcaoDeCombate;
import java.util.ArrayList;

public abstract class Heroi extends Personagem implements Combatente {
  private int nivel;
  private int experiencia;
  private int experienciaParaProximoNivel;
  private int sorte;
  private List<AcaoDeCombate> acoesDeCombate;

  //construtor
  public Heroi(String nome, int forca, int vida, Arma arma, int nivel, int experiencia, 
             int experienciaParaProximoNivel, int sorte) {
    super(nome, forca, vida, arma);
    this.experiencia = experiencia;
    this.nivel = nivel;
    this.experienciaParaProximoNivel = experienciaParaProximoNivel;
    this.sorte = sorte;
    this.acoesDeCombate = new ArrayList<>();
    escolherAcao();
}

  //gets e sets
  public int getNivel() {
    return nivel;
  }
  //seta a sorte como um valor aleatório entre 0 e 1
  private void setSorte() {
    this.sorte= (int) (Math.random() * 2);
  }
  public int getSorte() {
    this.setSorte();
    return sorte;
  } 
  //metodos
  public void ganharExperiencia(int pt_exp) {
    experiencia += pt_exp;
    nivelUp();
  }
  public void nivelUp() {
    if (experiencia >= experienciaParaProximoNivel) {
      nivel+=1;//subir de nivel
      experienciaParaProximoNivel += 150; //experiência necessária para o próximo nível
      System.out.println("================================");
      System.out.println(getNome() + " subiu para o nível " + nivel + "!");
      this.setVida(getVida()+30); // Aumenta a vida ao subir de nível
      this.setForca(getForca()+10); // Aumenta a força ao subir de nível
      System.out.println(getNome() + " ganhou +30 de vida e +10 de força!");
      System.out.println("================================");
    }
  }
  public void equiparArma(Arma novaArma) {
    setArma(novaArma);
    System.out.println(getNome() + " equipou a arma: " + novaArma.getNome());
  }
  public void escolherAcao() {
    acoesDeCombate.add(AcaoDeCombate.ATACAR);
    acoesDeCombate.add(AcaoDeCombate.USAR_HABILIDADE);
    acoesDeCombate.add(AcaoDeCombate.USAR_ITEM);
  }
  
  @Override
  public void status() {
    super.status(); // Chama o método da superclasse Personagem
    System.out.println("nivel: " + nivel);
    System.out.println("Exp total: " + experiencia + "/Exp prox nivel: " + experienciaParaProximoNivel);
    System.out.println("Sorte atual: " + getSorte());

  }
  @Override
  public abstract boolean HabilidadeEspecial(Personagem alvo);//metodo abstrato para habilidade especial
}
