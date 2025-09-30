package itens;

import interfaces.Item;

public class PocaoCura implements Item {
    @Override
    public String getNome() {
        return "Poção de Cura";
    }
}