package entidades.monstros;

import entidades.Personagem;
import itens.armas.Arma;
import itens.PocaoCura; // Import simples
import acoes.AtaqueFisico;
import acoes.HabilidadeDeFogo;
import java.util.ArrayList;
import java.util.List;

public class Goblin extends Monstro {
    
    public Goblin() {
        super("Goblin", 8, 30, null, 25, new ArrayList<>());
        
        // Definindo loot básico
        setLootBasico(new PocaoCura(15));
        
        // Definindo lista de ações no construtor
        adicionarAcao(new AtaqueFisico());
        adicionarAcao(new HabilidadeDeFogo());
    }
    
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
    
    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " usa habilidade especial!");
        return true;
    }
}