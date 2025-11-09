package acoes;

import interfaces.combate.AcaoDeCombate;
import interfaces.combate.Combatente;

public class FlechaFamejante implements AcaoDeCombate {
    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        System.out.println(usuario.getNome() + " ataca com flechas flamejantes!");
        alvo.receberDano(18);
    }
}