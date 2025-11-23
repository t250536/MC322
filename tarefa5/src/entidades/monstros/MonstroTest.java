package entidades.monstros;

import entidades.herois.Guerreiro;
import interfaces.combate.Combatente;
import interfaces.recompensa.Lootavel;

public class MonstroTest {
    private Goblin goblin;

    public void setUp() {
        goblin = new Goblin();
    }

    public void testMonstroImplementaCombatente() {
        System.out.println("Teste: Monstro implementa Combatente - " + (goblin instanceof Combatente));
    }

    public void testMonstroImplementaLootavel() {
        System.out.println("Teste: Monstro implementa Lootavel - " + (goblin instanceof Lootavel));
    }

    public void testMonstroRecebeDano() {
        int vidaInicial = goblin.getVida();
        goblin.receberDano(10);
        System.out.println("Teste: Monstro recebe dano - Vida inicial: " + vidaInicial + ", Vida final: " + goblin.getVida());
    }

    public void testMonstroAtacaHeroi() {
        Guerreiro guerreiro = new Guerreiro("Herói Teste", 15, 100, null, 1, 0, 100, 0);
        int vidaInicialHeroi = guerreiro.getVida();
        goblin.atacar(guerreiro);
        System.out.println("Teste: Monstro ataca herói - Vida inicial herói: " + vidaInicialHeroi + ", Vida final: " + guerreiro.getVida());
    }

    public void testMonstroDroparLoot() {
        System.out.println("Teste: Monstro dropa loot - " + (goblin.droparLoot() != null));
    }

    public static void main(String[] args) {
        MonstroTest test = new MonstroTest();
        test.setUp();
        test.testMonstroImplementaCombatente();
        test.testMonstroImplementaLootavel();
        test.testMonstroRecebeDano();
        test.testMonstroAtacaHeroi();
        test.testMonstroDroparLoot();
    }
}