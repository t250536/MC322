package ambientacao.cenarios;

import entidades.monstros.Goblin;
import fases.ambientacao.FaseDeCombate;
import ambientacao.Interfaces_de_Mundo_e_Cenario.Fase;
import ambientacao.Interfaces_de_Mundo_e_Cenario.GeradorDeFases;
import java.util.ArrayList;
import java.util.List;

public class ConstrutorDeCenarioFixo implements GeradorDeFases {
    
    @Override
    public List<Fase> gerar(int quantidadeDeFases) {
        List<Fase> fases = new ArrayList<>();
        
        System.out.println("=== CONSTRUINDO CENÁRIO DA CAMPANHA ===");
        
        // Fase 1: Floresta
        FaseDeCombate fase1 = new FaseDeCombate("FLORESTA");
        fase1.adicionarMonstro(new Goblin());
        fase1.adicionarMonstro(new Goblin());
        fases.add(fase1);
        System.out.println("Fase 1 criada: FLORESTA com 2 Goblins");
        
        // Fase 2: Caverna
        FaseDeCombate fase2 = new FaseDeCombate("CAVERNA");
        fase2.adicionarMonstro(new Goblin());
        fase2.adicionarMonstro(new Goblin());
        fase2.adicionarMonstro(new Goblin());
        fases.add(fase2);
        System.out.println("Fase 2 criada: CAVERNA com 3 Goblins");
        
        // Fase 3: Castelo
        FaseDeCombate fase3 = new FaseDeCombate("CASTELO");
        fase3.adicionarMonstro(new Goblin());
        fase3.adicionarMonstro(new Goblin());
        fase3.adicionarMonstro(new Goblin());
        fase3.adicionarMonstro(new Goblin());
        fases.add(fase3);
        System.out.println("Fase 3 criada: CASTELO com 4 Goblins");
        
        System.out.println("Total de fases geradas: " + fases.size());
        
        return fases;
    }
}

//ok