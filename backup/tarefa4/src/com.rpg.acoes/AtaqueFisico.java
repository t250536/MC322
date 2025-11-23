package com.rpg.acoes;

import com.rpg.interfaces.combate.AcaoDeCombate;
import com.rpg.interfaces.combate.Combatente;

public class AtaqueFisico implements AcaoDeCombate {
    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        System.out.println(usuario.getNome() + " ataca fisicamente!");
        alvo.receberDano(10);
    }
}