package ambientacao.fases;

import entidades.herois.Heroi;
import entidades.monstros.Monstro;
import interfaces.mundoCenario.Fase;
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
    
    public void iniciar(Heroi heroi) {
        System.out.println("=== INICIANDO FASE: " + tipoDeCenario + " ===");
        System.out.println("Monstros nesta fase: " + monstros.size());
        for (Monstro monstro : monstros) {
            // CORREÇÃO: Remover getNivel() pois Monstro não tem esse método
            System.out.println(" - " + monstro.getNome());
        }
        concluida = false;
    }
    
    public boolean isConcluida() {
        if (concluida) {
            return true;
        }
        
        for (Monstro monstro : monstros) {
            if (monstro.estaVivo()) {
                return false;
            }
        }
        
        concluida = true;
        System.out.println("=== FASE " + tipoDeCenario + " CONCLUÍDA ===");
        return true;
    }
    
    public String getTipoDeCenario() {
        return tipoDeCenario;
    }
    
    public List<Monstro> getMonstros() {
        return monstros;
    }
}