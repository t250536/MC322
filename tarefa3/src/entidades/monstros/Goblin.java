package entidades.monstros;

import entidades.Personagem;
import itens.armas.Arma;
import itens.loot.PocaoCura; // Import adicionado
import itens.Interface_de_recompensa.Item;
import java.util.ArrayList;
import java.util.List;

public class Goblin extends Monstro {
    
    public Goblin() {
        super("Goblin", 8, 30, null, 25, new ArrayList<>());
    }
    
    @Override
    public void atacar(Personagem alvo) {
        int dano = getDanototal();
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
    
    @Override
    public boolean HabilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " tenta fugir!");
        return Math.random() < 0.5; // 50% de chance
    }
    
    @Override
    public void escolherAcao(Combatente alvo) {
        // IA simples: 80% ataque normal, 20% habilidade especial
        if (Math.random() < 0.8) {
            atacar((Personagem) alvo);
        } else {
            HabilidadeEspecial((Personagem) alvo);
        }
    }
    
    @Override
    public Item droparLoot() {
        // Agora funciona porque PocaoCura está importado
        return new PocaoCura();
    }
}

//ok