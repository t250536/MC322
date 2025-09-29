package entidades.herois;

import entidades.Personagem;
import itens.armas.Arma;
import entidades.acoes.AcaoDeCombate;
import entidades.acoes.AtaqueComArma;
import entidades.acoes.HabilidadeDeFogo;
import entidades.acoes.Curar;

public class Guerreiro extends Heroi {
    private int furia;
    
    // construtor simplificado - valores padrão para nível/experiência
    public Guerreiro(String nome, int forca, int vida, Arma arma) {
        super(nome, forca, vida, arma, 1, 0, 150, 0);
        this.furia = 0;
        inicializarAcoes();
    }
    
    // construtor completo
    public Guerreiro(String nome, int forca, int vida, Arma arma, int nivel, 
                    int experiencia, int experienciaParaProximoNivel, int sorte, int furia) {
        super(nome, forca, vida, arma, nivel, experiencia, experienciaParaProximoNivel, sorte);
        this.furia = furia;
        inicializarAcoes();
    }
    
    // Gets e Sets
    public int getFuria() {
        return furia;
    }
    
    public void setFuria(int furia) {
        this.furia = Math.max(0, furia); // Fúria não pode ser negativa
    }
    
    public void aumentarFuria(int quantidade) {
        this.furia += quantidade;
        System.out.println(getNome() + " ganhou " + quantidade + " de fúria! Total: " + furia);
    }
    
    public void reduzirFuria(int quantidade) {
        this.furia = Math.max(0, this.furia - quantidade);
        System.out.println(getNome() + " perdeu " + quantidade + " de fúria! Total: " + furia);
    }

    // Inicializa as ações do Guerreiro
    private void inicializarAcoes() {
        adicionarAcao(new AtaqueBasico());
        adicionarAcao(new GolpePoderoso());
        adicionarAcao(new FuriaBerserk());
        adicionarAcao(new SegundoFolego());
    }

    // Ação 1: Ataque Básico (gera fúria)
    private class AtaqueBasico implements AcaoDeCombate {
        @Override
        public void executar(entidades.interfaces.Combatente usuario, entidades.interfaces.Combatente alvo) {
            int dano = getDanototal();
            System.out.println("⚔️ " + getNome() + " ataca com " + getArma().getNome() + "!");
            System.out.println("💥 Causa " + dano + " de dano!");
            alvo.receberDano(dano);
            aumentarFuria(3); // Gera fúria ao atacar
        }
        
        @Override
        public String toString() {
            return "Ataque Básico";
        }
    }

    // Ação 2: Golpe Poderoso (mais dano, gera mais fúria)
    private class GolpePoderoso implements AcaoDeCombate {
        @Override
        public void executar(entidades.interfaces.Combatente usuario, entidades.interfaces.Combatente alvo) {
            int dano = getDanototal() + 10; // Dano extra
            System.out.println("💢 " + getNome() + " desfere um Golpe Poderoso!");
            System.out.println("💥💥 Causa " + dano + " de dano!");
            alvo.receberDano(dano);
            aumentarFuria(5); // Gera mais fúria
        }
        
        @Override
        public String toString() {
            return "Golpe Poderoso";
        }
    }

    // Ação 3: Fúria Berserk (usa fúria acumulada)
    private class FuriaBerserk implements AcaoDeCombate {
        @Override
        public void executar(entidades.interfaces.Combatente usuario, entidades.interfaces.Combatente alvo) {
            if (furia >= 7) {
                System.out.println("😠 " + getNome() + " ativou a FÚRIA BERSERK!");
                int dano = getDanototal() * 3;
                System.out.println("💥💥💥 Dano triplicado: " + dano + "!");
                alvo.receberDano(dano);
                reduzirFuria(7); // Consome fúria
            } else {
                System.out.println("😐 " + getNome() + " tentou usar Fúria Berserk mas não tem fúria suficiente!");
                System.out.println("Fúria necessária: 7, Fúria atual: " + furia);
                // Fallback: usa ataque básico se não tiver fúria
                int dano = getDanototal();
                System.out.println("⚔️ Usando ataque básico instead! Causa " + dano + " de dano!");
                alvo.receberDano(dano);
                aumentarFuria(2); // Gera um pouco de fúria mesmo falhando
            }
        }
        
        @Override
        public String toString() {
            return "Fúria Berserk";
        }
    }

    // Ação 4: Segundo Fôlego (cura usando fúria)
    private class SegundoFolego implements AcaoDeCombate {
        @Override
        public void executar(entidades.interfaces.Combatente usuario, entidades.interfaces.Combatente alvo) {
            if (furia >= 4) {
                int cura = 30 + (furia * 2); // Cura base + bônus de fúria
                System.out.println("✨ " + getNome() + " usa Segundo Fôlego!");
                System.out.println("❤️  Recupera " + cura + " pontos de vida!");
                receberCura(cura);
                reduzirFuria(4); // Consome fúria
            } else {
                System.out.println("😐 " + getNome() + " tentou usar Segundo Fôlego mas não tem fúria suficiente!");
                System.out.println("Fúria necessária: 4, Fúria atual: " + furia);
                // Fallback: ataque básico
                int dano = getDanototal();
                System.out.println("⚔️ Usando ataque básico instead! Causa " + dano + " de dano!");
                alvo.receberDano(dano);
                aumentarFuria(2);
            }
        }
        
        @Override
        public String toString() {
            return "Segundo Fôlego";
        }
    }

    @Override
    public void status() {
        super.status();
        System.out.println("Fúria: " + furia + "/10");
        System.out.println("=== Ações do Guerreiro ===");
        System.out.println("1. Ataque Básico - Dano normal +3 fúria");
        System.out.println("2. Golpe Poderoso - Dano alto +5 fúria");
        System.out.println("3. Fúria Berserk - Dano triplo (custa 7 fúria)");
        System.out.println("4. Segundo Fôlego - Cura (custa 4 fúria)");
    }
}