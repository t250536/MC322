package itens.armas.projetil;

import itens.armas.Arma;

public class Arco extends Arma {
    private int alcance;
    
    //construtor
    public Arco(int dano, int minNivel, String nome, int alcance){
        super(dano, minNivel, nome);
        this.alcance = alcance;
    }
    
    //getter para alcance
    public int getAlcance() {
        return alcance;
    }
    
    @Override
    public String toString() {
        return getNome() + " (Dano: " + getDano() + ", Nível: " + getminNivel() + ", Alcance: " + alcance + ")";
    }
}