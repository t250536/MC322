package ambientacao.cenarios;

import ambientacao.Dificuldade;
import ambientacao.fases.FaseDeCombate;
import entidades.monstros.Monstro;
import entidades.monstros.Goblin;
import entidades.monstros.Orc;
import entidades.monstros.Dragao;
import interfaces.mundoCenario.Fase;
import interfaces.mundoCenario.GeradorDeFases;
import java.util.ArrayList;
import java.util.List;

public class ConstrutorDeCenarioFixo implements GeradorDeFases {
    
    @Override
    public List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade) {
        List<Fase> fases = new ArrayList<>();
        
        System.out.println("🎮 Gerando " + quantidadeDeFases + " fases com dificuldade: " + dificuldade);
        
        // Fase 1: Floresta - Monstros mais fracos
        FaseDeCombate fase1 = new FaseDeCombate("FLORESTA");
        fase1.adicionarMonstro(criarMonstroAjustado("Goblin Fraco", 30, 15, 20, dificuldade));
        fases.add(fase1);
        
        // Fase 2: Caverna - Monstros médios
        FaseDeCombate fase2 = new FaseDeCombate("CAVERNA");
        fase2.adicionarMonstro(criarMonstroAjustado("Goblin", 40, 20, 25, dificuldade));
        fase2.adicionarMonstro(criarMonstroAjustado("Goblin", 40, 20, 25, dificuldade));
        fases.add(fase2);
        
        // Fase 3: Castelo - Monstros fortes + chefe
        FaseDeCombate fase3 = new FaseDeCombate("CASTELO");
        fase3.adicionarMonstro(criarMonstroAjustado("Orc Guerreiro", 60, 30, 40, dificuldade));
        fase3.adicionarMonstro(criarMonstroAjustado("Orc Arqueiro", 50, 25, 35, dificuldade));
        fase3.adicionarMonstro(criarMonstroAjustado("Dragão Ancião", 100, 50, 100, dificuldade));
        fases.add(fase3);
        
        System.out.println("✅ " + fases.size() + " fases geradas com sucesso!");
        return fases;
    }
    
    private Monstro criarMonstroAjustado(String nome, int vidaBase, int forcaBase, int xpBase, Dificuldade dificuldade) {
        int vidaAjustada = (int)(vidaBase * dificuldade.getMultiplicadorVida());
        int forcaAjustada = (int)(forcaBase * dificuldade.getMultiplicadorForca());
        int xpAjustado = (int)(xpBase * dificuldade.getMultiplicadorVida());
        
        System.out.println("🐲 Criando " + nome + " | Vida: " + vidaAjustada + " | Força: " + forcaAjustada + " | XP: " + xpAjustado);
        
        if (nome.contains("Orc")) {
            return new Orc(nome, forcaAjustada, vidaAjustada, null, xpAjustado, new ArrayList<>());
        } else if (nome.contains("Dragão")) {
            return new Dragao(nome, forcaAjustada, vidaAjustada, null, xpAjustado, new ArrayList<>());
        } else {
            return new Goblin(nome, forcaAjustada, vidaAjustada, null, xpAjustado, new ArrayList<>());
        }
    }
}