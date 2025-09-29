package entidades.monstros;

import java.util.List;
import itens.armas.Arma;
import entidades.acoes.AcaoDeCombate;

public class Goblin extends Monstro {
    private int gosmatoxica;

    // construtor completo
    public Goblin(String nome, int forca, int vida, Arma arma, int xpConcedido, List<Arma> dropsList, int gosmatoxica) {
        super(nome, forca, vida, arma, xpConcedido, dropsList);
        this.gosmatoxica = gosmatoxica;
        inicializarAcoes();
    }

    // construtor simplificado
    public Goblin() {
        super("Goblin", 8, 40, null, 50, null);
        this.gosmatoxica = 0;
        inicializarAcoes();
    }

    // Gets e Sets
    public int getGosmatoxica() {
        return gosmatoxica;
    }

    public void setGosmatoxica(int gosmatoxica) {
        this.gosmatoxica = gosmatoxica;
    }
    
    public void aumentarGosmatoxica(int quantidade) {
        this.gosmatoxica += quantidade;
    }
    
    public void reduzirGosmatoxica(int quantidade) {
        this.gosmatoxica = Math.max(0, this.gosmatoxica - quantidade);
    }

    // Inicializa as ações do Goblin
    private void inicializarAcoes() {
        adicionarAcao(new AtaqueBasico());
        adicionarAcao(new Mordida());
        adicionarAcao(new GosmaToxica());
        adicionarAcao(new Arranhao());
    }

    // Ação 1: Ataque Básico (gera gosma tóxica)
    private class AtaqueBasico implements AcaoDeCombate {
        @Override
        public void executar(entidades.interfaces.Combatente usuario, entidades.interfaces.Combatente alvo) {
            int dano = getDanototal();
            String armaNome = (getArma() != null) ? getArma().getNome() : "garras";
            System.out.println("👹 " + getNome() + " ataca com " + armaNome + "!");
            System.out.println("💥 Causa " + dano + " de dano!");
            alvo.receberDano(dano);
            aumentarGosmatoxica(3);
        }
        
        @Override
        public String toString() {
            return "Ataque Básico";
        }
    }

    // Ação 2: Mordida (dano físico direto)
    private class Mordida implements AcaoDeCombate {
        @Override
        public void executar(entidades.interfaces.Combatente usuario, entidades.interfaces.Combatente alvo) {
            int dano = getForca() + 5; // Mordida ignora arma, usa força + bônus
            System.out.println("🦷 " + getNome() + " dá uma mordida venenosa!");
            System.out.println("💉 Causa " + dano + " de dano!");
            alvo.receberDano(dano);
            aumentarGosmatoxica(2);
            
            // 30% de chance de envenenar
            if (Math.random() < 0.3) {
                System.out.println("☠️  O alvo foi envenenado!");
            }
        }
        
        @Override
        public String toString() {
            return "Mordida";
        }
    }

    // Ação 3: Gosma Tóxica (habilidade especial)
    private class GosmaToxica implements AcaoDeCombate {
        @Override
        public void executar(entidades.interfaces.Combatente usuario, entidades.interfaces.Combatente alvo) {
            if (gosmatoxica >= 6) {
                int danoExtra = 30;
                int danoTotal = getDanototal() + danoExtra;
                System.out.println("💚 " + getNome() + " cospe GOSMA TÓXICA!");
                System.out.println("☠️  Dano extra +" + danoExtra + "! Total: " + danoTotal + "!");
                alvo.receberDano(danoTotal);
                reduzirGosmatoxica(6);
                
                // Efeito adicional: reduz defesa do alvo
                System.out.println("🛡️  A gosma reduz a defesa do alvo!");
            } else {
                System.out.println("💔 " + getNome() + " tentou cuspir gosma mas não tem toxina suficiente!");
                System.out.println("Gosma necessária: 6, Gosma atual: " + gosmatoxica);
                
                // Fallback: usa mordida
                int dano = getForca() + 3;
                System.out.println("🦷 Usando mordida instead! Causa " + dano + " de dano!");
                alvo.receberDano(dano);
                aumentarGosmatoxica(1);
            }
        }
        
        @Override
        public String toString() {
            return "Gosma Tóxica";
        }
    }

    // Ação 4: Arranhão (ataque rápido)
    private class Arranhao implements AcaoDeCombate {
        @Override
        public void executar(entidades.interfaces.Combatente usuario, entidades.interfaces.Combatente alvo) {
            int dano = getForca() + 2; // Dano baixo mas consistente
            System.out.println("✋ " + getNome() + " arranha rapidamente!");
            System.out.println("💢 Causa " + dano + " de dano!");
            alvo.receberDano(dano);
            aumentarGosmatoxica(4); // Gera mais gosma
            
            // Arranhão tem alta chance de gerar gosma
            if (Math.random() < 0.5) {
                aumentarGosmatoxica(2);
                System.out.println("💚 O arranhão gerou gosma extra!");
            }
        }
        
        @Override
        public String toString() {
            return "Arranhão";
        }
    }

    @Override
    public void status() {
        super.status();
        System.out.println("Gosma Tóxica: " + gosmatoxica + "/10");
        System.out.println("=== Ações do Goblin ===");
        System.out.println("1. Ataque Básico - Dano normal +3 gosma");
        System.out.println("2. Mordida - Dano por força +2 gosma (pode envenenar)");
        System.out.println("3. Gosma Tóxica - Dano +30 (custa 6 gosma, reduz defesa)");
        System.out.println("4. Arranhão - Dano baixo +4 gosma (alta chance de gosma extra)");
    }
}