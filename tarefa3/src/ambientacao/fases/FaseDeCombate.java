package ambientacao.fases;

import entidades.herois.Heroi;
import entidades.monstros.Monstro;
import ambientacao.interfaces_de_mundo_e_cenario.Fase;
import java.util.ArrayList;
import java.util.List;

public class FaseDeCombate implements Fase {
    private String tipoDeCenario;
    private List<Monstro> monstros;
    private boolean concluida;
    
    public FaseDeCombate(String tipoDeCenario) {
        this.tipoDeCenario = tipoDeCenario;
        this.monstros = new ArrayList<>();
        this.concluida = false;
    }
    
    public void adicionarMonstro(Monstro monstro) {
        monstros.add(monstro);
    }
    
    @Override
    public void iniciar(Heroi heroi) {
        System.out.println("=== INICIANDO FASE: " + tipoDeCenario + " ===");
        System.out.println("Monstros nesta fase: " + monstros.size());
        concluida = false;
    }
    
    @Override
    public boolean isConcluida() {
        // Retorna true apenas se todos os monstros foram derrotados
        if (concluida) {
            return true;
        }
        
        for (Monstro monstro : monstros) {
            if (monstro.estaVivo()) {
                return false; // Ainda há monstros vivos
            }
        }
        
        // Se chegou aqui, todos os monstros foram derrotados
        concluida = true;
        System.out.println("=== FASE " + tipoDeCenario + " CONCLUÍDA ===");
        return true;
    }
    
    @Override
    public String getTipoDeCenario() {
        return tipoDeCenario;
    }
    
    // Métodos auxiliares para acessar os monstros
    public List<Monstro> getMonstros() {
        return monstros;
    }
    
    public boolean temMonstrosVivos() {
        for (Monstro monstro : monstros) {
            if (monstro.estaVivo()) {
                return true;
            }
        }
        return false;
    }
    
    public Monstro getProximoMonstro() {
        for (Monstro monstro : monstros) {
            if (monstro.estaVivo()) {
                return monstro;
            }
        }
        return null;
    }
}

//ok