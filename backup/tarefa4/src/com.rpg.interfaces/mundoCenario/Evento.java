package com.rpg.interfaces.mundoCenario;

import com.rpg.entidades.herois.Heroi;

public interface Evento {
    boolean verificarGatilho(Heroi heroi);
    void executar(Heroi heroi);
}
