package ambientacao.interfaces;

import entidades.herois.Heroi;

public interface Fase {
    void iniciar(Heroi heroi);
    boolean isConcluida();
    TipoCenario getTipoDeCenario();
}