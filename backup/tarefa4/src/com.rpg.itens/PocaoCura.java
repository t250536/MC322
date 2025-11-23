package com.rpg.itens;  // Pacote direto, sem subpacote loot

import com.rpg.interfaces.recompensa.Item;

public class PocaoCura implements Item {
    private int cura;
    
    public PocaoCura(int cura) {
        this.cura = cura;
    }
    
    @Override
    public String getNome() {
        return "Poção de Cura (" + cura + " PV)";
    }
    
    public int getCura() {
        return cura;
    }
}