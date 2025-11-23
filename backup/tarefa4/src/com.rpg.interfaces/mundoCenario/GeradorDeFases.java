package com.rpg.interfaces.mundoCenario;

import java.util.List;

import com.rpg.ambientacao.Dificuldade;

public interface GeradorDeFases { // Note o "F" maiúsculo
    List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade);
}