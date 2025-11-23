package com.rpg.acoes;

import com.rpg.interfaces.combate.AcaoDeCombate;
import com.rpg.interfaces.combate.Combatente;

public class Furia implements AcaoDeCombate {
    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        System.out.println(usuario.getNome() + " entrou em furia!");
        alvo.receberDano(25);
    }
}