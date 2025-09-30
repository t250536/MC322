package entidades.monstros;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entidades.Personagem;
import itens.armas.Arma;

// Importações necessárias para as novas funcionalidades
import entidades.interface_de_combate.AcaoDeCombate;
import itens.Interface_de_recompensa.Lootavel;
import itens.Interface_de_recompensa.Item;

public abstract class Monstro extends Personagem implements Lootavel {
    public List<Arma> dropList;
    public int xpConcedido;
    private List<AcaoDeCombate> acoes;
    private Random random;
    private Item lootBasico; // Item básico que o monstro sempre dropa

    // construtor
    public Monstro(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList) {
        super(nome, forca, vida, arma);// chamar o construtor da superclasse Personagem
        this.xpConcedido = xpConcedido;
        this.dropList = dropsList;
        this.acoes = new ArrayList<>();
        this.random = new Random();
        this.lootBasico = null; // Pode ser definido nas subclasses
    }

    // Novo construtor sobrecarregado para incluir loot básico
    public Monstro(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList, Item lootBasico) {
        this(nome, forca, vida, arma, xpConcedido, dropsList);
        this.lootBasico = lootBasico;
    }

    // Implementação do método escolherAcao da interface Combatente
    @Override
    public void escolherAcao(Combatente alvo) {
        if (acoes.isEmpty()) {
            System.out.println(getNome() + " não tem ações disponíveis! Usando ataque básico.");
            // Fallback para o ataque normal se não houver ações
            atacar((Personagem) alvo);
            return;
        }
        
        // IA do monstro: escolhe ação baseada em probabilidades simples
        // Estratégia: 70% de chance de usar ação normal, 30% de usar habilidade especial
        double chance = random.nextDouble();
        
        if (chance < 0.3 && HabilidadeEspecial((Personagem) alvo)) {
            // 30% de chance de tentar usar habilidade especial
            System.out.println(getNome() + " usa uma habilidade especial!");
        } else {
            // 70% de chance de usar uma ação normal da lista
            int indiceAcao = random.nextInt(acoes.size());
            AcaoDeCombate acaoEscolhida = acoes.get(indiceAcao);
            
            System.out.println(getNome() + " escolheu: " + acaoEscolhida.getClass().getSimpleName());
            acaoEscolhida.executar(this, alvo);
        }
    }

    // Implementação do método droparLoot da interface Lootavel
    @Override
    public Item droparLoot() {
        // Primeiro tenta dropar uma arma da dropList
        Arma armaDropada = droparArma();
        if (armaDropada != null) {
            System.out.println(getNome() + " dropou a arma: " + armaDropada.getNome());
            return armaDropada;
        }
        
        // Se não dropou arma, retorna o loot básico (se existir)
        if (lootBasico != null) {
            System.out.println(getNome() + " dropou: " + lootBasico.getNome());
            return lootBasico;
        }
        
        System.out.println(getNome() + " não dropou nenhum item.");
        return null;
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

    // metodos
    public Arma droparArma() {
        if (dropList.isEmpty()) {
            return null; // Retorna null se a lista estiver vazia
        }
        int aleatorio = (int) (Math.random() * dropList.size());
        return dropList.get(aleatorio);
    }

    @Override
    public void status() {
        super.status(); // Chama o método da superclasse Personagem
        System.out.println("XP Concedido: " + xpConcedido);
        System.out.println("Ações disponíveis: " + acoes.size());
        if (lootBasico != null) {
            System.out.println("Loot básico: " + lootBasico.getNome());
        }
    }

    @Override
    public abstract boolean HabilidadeEspecial(Personagem alvo);// metodo abstrato para habilidade especial
}

//ok