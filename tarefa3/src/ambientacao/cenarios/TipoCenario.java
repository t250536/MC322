package ambientacao.cenarios;

import entidades.herois.Heroi;
public enum TipoCenario {
  //floresta aplica efeito de diminuir forca e perder vida
  FLORESTA("UMA FLORESTA DESNSA E SOMBRIA"){
    @Override
    public void aplicarEfeito(Heroi heroi) {
      System.out.println("O heroi se sente sugado pelas sombras.");
      heroi.diminuirForca(10);
      heroi.perderVida(25);
    }
  },
  //castelo aplica efeito de aumentar forca
  CASTELO("UM CASTELO ARRUINDO E ASSOMBRADO"){
    @Override
    public void aplicarEfeito(Heroi heroi) {
      System.out.println("O heroi sente arrepios ao entrar no castelo e fica mais corajoso.");
      heroi.aumentarForca(5);
    }
  },
  //deserto aplica efeito de perder vida
  DESERTO("UM DESERTO QUENTE E ARIDO"){
    @Override
    public void aplicarEfeito(Heroi heroi) {
      System.out.println("O heroi sente  um grande cansaço ao caminhar pelo no deserto.");
      //heroi.perderVida(15);
    }
  },
  //caverna aplica efeito de aumentar vida
  CAVERNA("UMA CAVERNA ESCURA E UMIDA"){
    @Override
    public void aplicarEfeito(Heroi heroi) {
      System.out.println("O heroi sente medo e fica mais alerta na caverna.");
      //heroi.aumentarvida(35);
    }
  };

}
