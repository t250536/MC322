package itens.armas; //caminho do pacote

public abstract class Arma {
    private int dano, minNivel;

    //construtor
    public Arma(int dano, int minNivel){
        this.dano = dano;
        this.minNivel = minNivel;
    }

    //sets e gets
    public int getDano(){
        return dano;
    }

    public void setDano(int dano){
        this.dano = dano;
    }

    public int getminNivel(){
        return minNivel;
    }

    public void setminNivel(int minNivel){
        this.minNivel = minNivel;
    }

}