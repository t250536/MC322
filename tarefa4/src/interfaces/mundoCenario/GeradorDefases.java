package interfaces.mundoCenario;

import ambientacao.Dificuldade;
import java.util.List;

public interface GeradorDeFases { // Note o "F" maiúsculo
    List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade);
}