package com.rpg.interfaces.mundoCenario;

import com.rpg.entidades.herois.Heroi;

public interface Fase {
    void iniciar(Heroi heroi);
    boolean isConcluida();
    String getTipoDeCenario();
}
