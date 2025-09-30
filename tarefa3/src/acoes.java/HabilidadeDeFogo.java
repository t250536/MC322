package acoes;

import entidades.interface_de_combate.AcaoDeCombate;

public class HabilidadeDeFogo implements AcaoDeCombate {
    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        int dano = 18;
        System.out.println(usuario.getNome() + " lança uma HABILIDADE DE FOGO em " + alvo.getNome() + "!");
        System.out.println("Causou " + dano + " de dano flamejante.");
        alvo.receberDano(dano);
        
        // Efeito adicional: chance de queimar o alvo
        if (Math.random() < 0.3) { // 30% de chance
            System.out.println("O alvo foi queimado!");
        }
    }
}

//ok