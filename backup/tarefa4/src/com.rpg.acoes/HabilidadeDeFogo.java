package com.rpg.acoes;

import com.rpg.interfaces.combate.AcaoDeCombate;
import com.rpg.interfaces.combate.Combatente;

public class HabilidadeDeFogo implements AcaoDeCombate {
    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        System.out.println(usuario.getNome() + " lança uma bola de fogo!");
        alvo.receberDano(15);
    }
}