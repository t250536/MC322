package com.rpg.acoes;

import com.rpg.interfaces.combate.AcaoDeCombate;
import com.rpg.interfaces.combate.Combatente;

public class CuraDivina implements AcaoDeCombate {
    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        System.out.println(usuario.getNome() + " usou a habilidade de cura divina!");
        usuario.receberCura(35);
    }
}