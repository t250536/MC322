package interfaces.mundoCenario;

import ambientacao.Dificuldade;
import java.util.List;

public interface GeradorDeFases {
    List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade);
}