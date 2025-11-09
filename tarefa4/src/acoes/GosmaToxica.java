package acoes;

import interfaces.combate.AcaoDeCombate;
import interfaces.combate.Combatente;

public class GosmaToxica implements AcaoDeCombate {
    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        System.out.println(usuario.getNome() + " cuspiu uma gosma toxica!");
        alvo.receberDano(12);
    }
}