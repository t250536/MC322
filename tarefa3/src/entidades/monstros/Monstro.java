package entidades.monstros;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entidades.Personagem;
import itens.armas.Arma;
import entidades.interfaces.Combatente;
import entidades.interfaces.Lootavel;
import entidades.acoes.AcaoDeCombate;
import itens.Item;

public abstract class Monstro extends Personagem implements Combatente, Lootavel {
    private List<Arma> dropList;
    private int xpConcedido;
    private List<AcaoDeCombate> acoes;
    private Random random;

    // construtor
    public Monstro(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList) {
        super(nome, forca, vida, arma);
        this.xpConcedido = xpConcedido;
        this.dropList = dropsList != null ? new ArrayList<>(dropsList) : new ArrayList<>();
        this.acoes = new ArrayList<>();
        this.random = new Random();
        // REMOVIDO: escolherAcao() do construtor - agora é responsabilidade das subclasses
    }

    // gets e sets
    public int getXpConcedido() {
        return xpConcedido;
    }
    
    public void setXpConcedido(int xpConcedido) {
        this.xpConcedido = xpConcedido;
    }
    
    public List<Arma> getDropList() {
        return new ArrayList<>(dropList); // Retorna cópia para proteger a lista original
    }
    
    public void setDropList(List<Arma> dropList) {
        this.dropList = new ArrayList<>(dropList);
    }

    // métodos
    public Arma droparArma() {
        if (dropList.isEmpty()) {
            return null;
        }
        int aleatorio = random.nextInt(dropList.size());
        return dropList.get(aleatorio);
    }

    // IMPLEMENTAÇÃO da interface Lootavel
    @Override
    public Item droparLoot() {
        // Primeiro tenta dropar uma arma
        Arma armaDropada = droparArma();
        if (armaDropada != null) {
            System.out.println("🎁 " + getNome() + " dropou: " + armaDropada.getNome());
            return armaDropada;
        }
        
        // Se não dropou arma, pode adicionar lógica para outros itens aqui
        // Por exemplo, poções, ouro, etc.
        
        return null;
    }

    // IMPLEMENTAÇÃO CORRETA do método da interface Combatente
    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo) {
        // IA simples: escolhe ação aleatória
        if (acoes.isEmpty()) {
            return null;
        }
        
        AcaoDeCombate acaoEscolhida = acoes.get(random.nextInt(acoes.size()));
        System.out.println(getNome() + " prepara: " + acaoEscolhida.getClass().getSimpleName());
        return acaoEscolhida;
    }

    // Método para adicionar ações (usado pelas subclasses)
    public void adicionarAcao(AcaoDeCombate acao) {
        acoes.add(acao);
    }
    
    public List<AcaoDeCombate> getAcoes() {
        return new ArrayList<>(acoes); // Retorna cópia para proteger a lista original
    }

    @Override
    public void status() {
        super.status();
        System.out.println("XP Concedido: " + xpConcedido);
        System.out.println("Itens no drop: " + dropList.size());
        System.out.println("Ações disponíveis: " + acoes.size());
    }

    // REMOVIDO: método abstrato HabilidadeEspecial - substituído pelo sistema de ações
    // @Override
    // public abstract boolean HabilidadeEspecial(Personagem alvo);
}