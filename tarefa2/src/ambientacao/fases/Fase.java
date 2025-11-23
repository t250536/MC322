package ambientacao.fases;

import java.util.List;

import com.rpg.entidades.monstros.Monstro;

public class Fase {
  //atributos
  private int level;
  private String ambiente;
  private List<Monstro> monstros;
  //construtor
  public Fase(int level, String ambiente, List<Monstro> monstros) {
    this.level = level;
    this.ambiente = ambiente;
    this.monstros = monstros;
  }
  //metodos
  public int getLevel() {
    return level;
  }
  public String getAmbiente() {
    return ambiente;
  }
  public List<Monstro> getMonstros() {
    return monstros;
  }
  public String getNome() {
    return ambiente;
  }
}
