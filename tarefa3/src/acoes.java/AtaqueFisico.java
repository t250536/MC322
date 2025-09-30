package acoes;

import entidades.interface_de_combate.AcaoDeCombate;

public class AtaqueFisico implements AcaoDeCombate {
    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        int dano = 12;
        System.out.println(usuario.getNome() + " realiza um ATAQUE FÍSICO em " + alvo.getNome() + "!");
        System.out.println("Causou " + dano + " de dano.");
        alvo.receberDano(dano);
    }
}

//ok