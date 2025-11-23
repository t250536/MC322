package entidades.monstros;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entidades.Personagem;
import itens.armas.Arma;
import interfaces.combate.AcaoDeCombate;
import interfaces.combate.Combatente;
import interfaces.recompensa.Lootavel;
import interfaces.recompensa.Item;

public abstract class Monstro extends Personagem implements Lootavel {
    public List<Arma> dropList;
    public int xpConcedido;
    private List<AcaoDeCombate> acoes;
    private Random random;
    private Item lootBasico;

    // construtor
    public Monstro(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList) {
        super(nome, forca, vida, arma);
        this.xpConcedido = xpConcedido;
        this.dropList = dropsList;
        this.acoes = new ArrayList<>();
        this.random = new Random();
        this.lootBasico = null;
    }

    // CORREÇÃO: Implementação explícita do método escolherAcao
    @Override
    public void escolherAcao(Combatente alvo) {
        if (acoes.isEmpty()) {
            System.out.println(getNome() + " não tem ações disponíveis! Usando ataque básico.");
            atacar((Personagem) alvo);
            return;
        }
        
        double chance = random.nextDouble();
        
        if (chance < 0.3 && HabilidadeEspecial((Personagem) alvo)) {
            System.out.println(getNome() + " usa uma habilidade especial!");
        } else {
            int indiceAcao = random.nextInt(acoes.size());
            AcaoDeCombate acaoEscolhida = acoes.get(indiceAcao);
            
            System.out.println(getNome() + " escolheu: " + acaoEscolhida.getClass().getSimpleName());
            acaoEscolhida.executar(this, alvo);
        }
    }

    // Implementação do método droparLoot da interface Lootavel
    @Override
    public Item droparLoot() {
        // Retorna apenas o loot básico (pois armas não são itens de loot)
        if (lootBasico != null) {
            System.out.println(getNome() + " dropou: " + lootBasico.getNome());
            return lootBasico;
        }
        
        System.out.println(getNome() + " não dropou nenhum item.");
        return null;
    }

    // Mantemos o método droparArma separado para uso futuro se necessário
    public Arma droparArma() {
        if (dropList.isEmpty()) {
            return null;
        }
        int aleatorio = (int) (Math.random() * dropList.size());
        return dropList.get(aleatorio);
    }

    // Método para adicionar ações à lista
    public void adicionarAcao(AcaoDeCombate acao) {
        acoes.add(acao);
    }
    
    // Método para obter a lista de ações
    public List<AcaoDeCombate> getAcoes() {
        return acoes;
    }
    
    // Método para definir o loot básico
    public void setLootBasico(Item lootBasico) {
        this.lootBasico = lootBasico;
    }

    // gets e sets
    public int getXpConcedido() {
        return xpConcedido;
    }

    @Override
    public void status() {
        super.status();
        System.out.println("XP Concedido: " + xpConcedido);
        System.out.println("Ações disponíveis: " + acoes.size());
        if (lootBasico != null) {
            System.out.println("Loot básico: " + lootBasico.getNome());
        }
    }

    @Override
    public abstract boolean HabilidadeEspecial(Personagem alvo);
}