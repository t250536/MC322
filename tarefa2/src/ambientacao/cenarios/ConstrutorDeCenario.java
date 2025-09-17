package ambientacao.cenarios;

import java.util.List;

import ambientacao.fases.Fase;
import entidades.monstros.Monstro;
import entidades.monstros.Orc;
import itens.armas.melee.Machado;
import itens.armas.melee.Faca;
import itens.armas.Arma;
import itens.armas.melee.Espada;

import entidades.monstros.Dragao;
import entidades.monstros.Goblin;

public class ConstrutorDeCenario {

  // O método precisa ser PUBLIC e STATIC
  public static List<Fase> gerarFases(int nFases) {
    List<Fase> fases = new java.util.ArrayList<>();
    for (int i = 1; i <= nFases; i++) {
      // criar fase com dificuldade crescente
      List<Monstro> monstros = gerarMonstros(i); // Dificuldade baseada no nível
      int tipofase = (int) (Math.random() * 7); // 0: Floresta , 1: Caverna, 2: Castelo, 3: Deserto, 4: Montanha, 5:
                                                // Pântano, 6: Ruínas
      switch (tipofase) {
        case 0:
          fases.add(new Fase(i, "Floresta da Bruxa Desencantada", monstros));
          break;
        case 1:
          fases.add(new Fase(i, "Caverna Sombria do Bebe Chorao", monstros));
          break;
        case 2:
          fases.add(new Fase(i, "Castelo Assombrado do Lorde das Trevas", monstros));
          break;
        case 3:
          fases.add(new Fase(i, "Deserto Ardente do Dragao de Fogo", monstros));
          break;
        case 4:
          fases.add(new Fase(i, "Montanha Gelada dos Orcs Berserkers", monstros));
          break;
        case 5:
          fases.add(new Fase(i, "Pântano Misterioso do Sapo Gigante", monstros));
          break;
        case 6:
          fases.add(new Fase(i, "Ruínas Antigas do Goblin Ancião", monstros));
          break;
      }
    }
    return fases;
  }

  // gerador de drops
  private static List<Arma> geradrops(int lvl) {
    List<Arma> dropsList = new java.util.ArrayList<>();
    int numDrops = 3; // Gera 3 drops
    for (int i = 0; i < numDrops; i++) {
      int tipoArma = (int) (Math.random() * 10);
      switch (tipoArma) {
        case 0:
          dropsList.add(new Espada(20, lvl, "Espada Longa"));
          break;
        case 1:
          dropsList.add(new Machado(25, lvl, "Machado de Batalha"));
          break;
        case 2:
          dropsList.add(new Faca(15, lvl, "Faca de Arremesso"));
          break;
        case 3:
          dropsList.add(new Espada(30, lvl, "Espada de Fogo"));
          break;
        case 4:
          dropsList.add(new Machado(35, lvl, "Machado de Gelo"));
          break;
        case 5:
          dropsList.add(new Faca(20, lvl, "Faca Venenosa"));
          break;
        case 6:
          dropsList.add(new Espada(25, lvl, "Espada Curta"));
          break;
        case 7:
          dropsList.add(new Machado(20, lvl, "Machado Leve"));
          break;
        case 8:
          dropsList.add(new Faca(10, lvl, "Faca Pequena"));
          break;
        case 9:
          dropsList.add(new Espada(40, lvl, "Espada do Dragão"));
          break;
      }
    }
    return dropsList;
  }

  private static List<Monstro> gerarMonstros(int nivel) {
    List<Monstro> monstros = new java.util.ArrayList<>();

    // gerar monstros com base no nível
    int numMonstros = nivel + 2;
    for (int i = 0; i < numMonstros; i++) {
      int tipomonstro = (int) (Math.random() * 3); // 0: Orc, 1: Goblin, 2: Dragão
      List<Arma> dropsList = geradrops(nivel-1);//nivel minimo da arma dropada é nivel da fase -1
      switch (tipomonstro) {
        case 0:
          Orc orc = new Orc("Orc de nivel " + nivel, 10 + nivel * 10, 50 + nivel * 20,
              new Machado(15, 0, "Machado Rústico"), 20 + nivel * 10, dropsList, 0);
          monstros.add(orc);
          break;
        case 1:
          Goblin goblin = new Goblin("Goblin de nivel " + nivel, 8 + nivel * 15, 30 + nivel * 10,
              new Faca(10, 0, "Faca Ensanguentada"), 15 + nivel * 10, dropsList, 0);
          monstros.add(goblin);
          // monstros.add(new Goblin("Goblin de nivel " + nivel, 8 + nivel * 15, 30 +
          // nivel * 10,new Faca(10, 0, "Faca Ensanguentada"), 15 + nivel * 10, dropsList,
          // 0));
          break;
        case 2:
          Dragao dragao = new Dragao("Dragão de nivel " + nivel, 15 + nivel * 10, 100 + nivel * 15,
              new Machado(12, 0, "Espada de Obsidiana"), 30 + nivel * 15, dropsList, 0);
          monstros.add(dragao);
          // monstros.add(new Dragao("Dragão de nivel " + nivel, 15 + nivel * 10, 100 +
          // nivel * 15,new Faca(12, 0, "Faca de Obsidiana"), 30 + nivel * 15, dropsList,
          // 0));
          break;
      }
      // exibir o monstro criado para teste
      //System.out.println("Monstro criado: " + monstros.get(i).getNome());
    }
    return monstros;
  }
}
