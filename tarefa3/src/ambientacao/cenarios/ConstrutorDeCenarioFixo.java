package ambientacao.cenarios;

import entidades.monstros.Goblin;
import ambientacao.fases.FaseDeCombate;
import interfaces.mundoCenario.Fase;
import interfaces.mundoCenario.GeradorDefases; // Note o nome correto da interface
import java.util.ArrayList;
import java.util.List;

public class ConstrutorDeCenarioFixo implements GeradorDefases { // Corrigido para GeradorDefases
    
    @Override
    public List<Fase> gerar(int quantidadeDeFases) {
        List<Fase> fases = new ArrayList<>();
        
        // Fase 1: Floresta
        FaseDeCombate fase1 = new FaseDeCombate("FLORESTA");
        fase1.adicionarMonstro(new Goblin());
        fases.add(fase1);
        
        // Fase 2: Caverna
        FaseDeCombate fase2 = new FaseDeCombate("CAVERNA");
        fase2.adicionarMonstro(new Goblin());
        fase2.adicionarMonstro(new Goblin());
        fases.add(fase2);
        
        // Fase 3: Castelo
        FaseDeCombate fase3 = new FaseDeCombate("CASTELO");
        fase3.adicionarMonstro(new Goblin());
        fase3.adicionarMonstro(new Goblin());
        fase3.adicionarMonstro(new Goblin());
        fases.add(fase3);
        
        return fases;
    }
}