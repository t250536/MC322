package entidades.herois;

import entidades.monstros.Goblin;
import interfaces.combate.Combatente;

public class HeroiTest {
    private Guerreiro guerreiro;

    public void setUp() {
        guerreiro = new Guerreiro("Herói Teste", 15, 100, null, 1, 0, 100, 0);
    }

    public void testHeroiImplementaCombatente() {
        System.out.println("Teste: Herói implementa Combatente - " + (guerreiro instanceof Combatente));
    }

    public void testHeroiRecebeDano() {
        int vidaInicial = guerreiro.getVida();
        guerreiro.receberDano(20);
        System.out.println("Teste: Herói recebe dano - Vida inicial: " + vidaInicial + ", Vida final: " + guerreiro.getVida());
    }

    public void testHeroiAtacaMonstro() {
        Goblin goblin = new Goblin();
        int vidaInicialGoblin = goblin.getVida();
        guerreiro.atacar(goblin);
        System.out.println("Teste: Herói ataca monstro - Vida inicial goblin: " + vidaInicialGoblin + ", Vida final: " + goblin.getVida());
    }

    public void testHeroiEstaVivo() {
        System.out.println("Teste: Herói está vivo - " + guerreiro.estaVivo());
        guerreiro.receberDano(1000);
        System.out.println("Teste: Herói após dano fatal - " + guerreiro.estaVivo());
    }

    public static void main(String[] args) {
        HeroiTest test = new HeroiTest();
        test.setUp();
        test.testHeroiImplementaCombatente();
        test.testHeroiRecebeDano();
        test.testHeroiAtacaMonstro();
        test.testHeroiEstaVivo();
    }
}