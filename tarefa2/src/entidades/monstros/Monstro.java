package entidades.monstros;

import java.util.ArrayList;
import java.util.List;

import entidades.Personagem;
import itens.armas.Arma;

public abstract class Monstro extends Personagem {
  public List<Arma> dropList;
  public int xpConcedido;

  // construtor
  public Monstro(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList) {
    super(nome, forca, vida, arma);// chamar o construtor da superclasse Personagem
    this.xpConcedido = xpConcedido;
    this.dropList = dropsList;
  }

  // gets e sets
  public int getXpConcedido() {
    return xpConcedido;
  }

  // metodos
  public Arma droparArma() {
    if (dropList.isEmpty()) {
      return null; // Retorna null se a lista estiver vazia
    }
    int aleatorio = (int) (Math.random() * dropList.size());
    return dropList.get(aleatorio);
  }

  @Override
  public void status() {
    super.status(); // Chama o método da superclasse Personagem
    System.out.println("XP Concedido: " + xpConcedido);

  }

  @Override
  public abstract boolean HabilidadeEspecial(Personagem alvo);// metodo abstrato para habilidade especial
}
