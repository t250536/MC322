package interfaces.mundoCenario;

import entidades.herois.Heroi;

public interface Evento {
    boolean verificarGatilho(Heroi heroi);
    void executar(Heroi heroi);
}
