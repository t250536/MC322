/* 

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
} */


/*
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
    
    // Implementação CORRETA do método da interface
    @Override
    public List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade) {
        List<Fase> fases = new ArrayList<>();
        
        System.out.println("🎮 Gerando " + quantidadeDeFases + " fases com dificuldade: " + dificuldade);
        
        // Fase 1: Floresta - Monstros mais fracos
        FaseDeCombate fase1 = new FaseDeCombate("FLORESTA");
        fase1.adicionarMonstro(criarMonstroAjustado("Goblin Fraco", 30, 15, 20, dificuldade));
        fases.add(fase1);
        
        // Fase 2: Caverna - Monstros médios
        if (quantidadeDeFases >= 2) {
            FaseDeCombate fase2 = new FaseDeCombate("CAVERNA");
            fase2.adicionarMonstro(criarMonstroAjustado("Goblin", 40, 20, 25, dificuldade));
            fase2.adicionarMonstro(criarMonstroAjustado("Goblin", 40, 20, 25, dificuldade));
            fases.add(fase2);
        }
        
        // Fase 3: Castelo - Monstros fortes + chefe
        if (quantidadeDeFases >= 3) {
            FaseDeCombate fase3 = new FaseDeCombate("CASTELO");
            fase3.adicionarMonstro(criarMonstroAjustado("Orc Guerreiro", 60, 30, 40, dificuldade));
            fase3.adicionarMonstro(criarMonstroAjustado("Orc Arqueiro", 50, 25, 35, dificuldade));
            fase3.adicionarMonstro(criarMonstroAjustado("Dragão Ancião", 100, 50, 100, dificuldade));
            fases.add(fase3);
        }
        
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

*/

package com.rpg.ambientacao.cenarios;

import com.rpg.entidades.monstros.Monstro;
import com.rpg.entidades.monstros.Goblin;
import com.rpg.entidades.monstros.Orc;
import com.rpg.entidades.monstros.Dragao;
import com.rpg.interfaces.mundoCenario.Fase;
import com.rpg.interfaces.mundoCenario.GeradorDeFases;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rpg.ambientacao.Dificuldade;
import com.rpg.ambientacao.fases.FaseDeCombate;

public class ConstrutorDeCenarioFixo implements GeradorDeFases {
    
    private Random random = new Random();
    
    @Override
    public List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade) {
        List<Fase> fases = new ArrayList<>();
        
        System.out.println("🎮 Gerando " + quantidadeDeFases + " fases ALEATÓRIAS");
        
        for (int i = 1; i <= quantidadeDeFases; i++) {
            FaseDeCombate fase = criarFaseAleatoria(i, dificuldade);
            fases.add(fase);
        }
        
        System.out.println("✅ " + fases.size() + " fases geradas com sucesso!");
        return fases;
    }
    
    private FaseDeCombate criarFaseAleatoria(int numeroFase, Dificuldade dificuldade) {
        String[] ambientes = {"FLORESTA", "CAVERNA", "CASTELO", "PÂNTANO", "RUÍNAS", "MONTANHA"};
        String ambiente = ambientes[random.nextInt(ambientes.length)];
        FaseDeCombate fase = new FaseDeCombate(ambiente);
        
        // 1-4 monstros por fase
        int quantidadeMonstros = 1 + random.nextInt(4);
        
        for (int i = 0; i < quantidadeMonstros; i++) {
            Monstro monstro = criarMonstroAleatorio(numeroFase, dificuldade);
            fase.adicionarMonstro(monstro);
        }
        
        return fase;
    }
    
    private Monstro criarMonstroAleatorio(int numeroFase, Dificuldade dificuldade) {
        int tipo = random.nextInt(3); // 0=Goblin, 1=Orc, 2=Dragão
        
        int vidaBase = 30 + (numeroFase * 10);
        int forcaBase = 8 + (numeroFase * 3);
        int xpBase = 20 + (numeroFase * 5);
        
        int vida = (int)(vidaBase * dificuldade.getMultiplicadorVida());
        int forca = (int)(forcaBase * dificuldade.getMultiplicadorForca());
        int xp = xpBase;
        
        if (tipo == 0) {
            return new Goblin("Goblin", forca, vida, null, xp, new ArrayList<>());
        } else if (tipo == 1) {
            return new Orc("Orc", forca, vida, null, xp, new ArrayList<>());
        } else {
            return new Dragao("Dragão", forca, vida, null, xp, new ArrayList<>());
        }
    }
}