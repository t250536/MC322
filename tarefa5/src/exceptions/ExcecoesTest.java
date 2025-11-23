package exceptions;

import entidades.herois.Guerreiro;
import itens.armas.melee.Espada;

public class ExcecoesTest {

    public void testNivelInsuficienteException() {
        Guerreiro guerreiro = new Guerreiro("Herói Teste", 15, 100, null, 1, 0, 100, 0);
        Espada espada = new Espada(20, 5, "Espada Épica"); // Nível mínimo 5

        try {
            guerreiro.equiparArmaComVerificacao(espada);
            System.out.println("ERRO: Deveria ter lançado NivelInsuficienteException");
        } catch (NivelInsuficienteException e) {
            System.out.println("SUCESSO: NivelInsuficienteException lançada corretamente - " + e.getMessage());
        }
    }

    public void testRecursoInsuficienteException() {
        Guerreiro guerreiro = new Guerreiro("Herói Teste", 15, 100, null, 1, 0, 100, 0);
        guerreiro.setMana(10); // Define mana baixa

        try {
            guerreiro.usarHabilidadeEspecial(guerreiro); // Custo de mana é 30
            System.out.println("ERRO: Deveria ter lançado RecursoInsuficienteException");
        } catch (RecursoInsuficienteException e) {
            System.out.println("SUCESSO: RecursoInsuficienteException lançada corretamente - " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ExcecoesTest test = new ExcecoesTest();
        test.testNivelInsuficienteException();
        test.testRecursoInsuficienteException();
    }
}