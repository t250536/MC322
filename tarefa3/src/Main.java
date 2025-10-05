import ambientacao.cenarios.ConstrutorDeCenarioFixo;
import entidades.herois.Guerreiro;
import entidades.monstros.Monstro;
import interfaces.combate.Combatente;
import interfaces.mundoCenario.Fase;
import interfaces.mundoCenario.GeradorDefases;
import interfaces.recompensa.Lootavel;
import itens.armas.Arma;
import java.util.List;

public class Main {
    
    // Método auxiliar para fazer pausas
    private static void aguardar(int segundos) {
        try {
            Thread.sleep(segundos/2 * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== INICIANDO JOGO RPG ===");
        System.out.println();
        aguardar(1);
        
        // 1. Criar instância do GeradorDeFases
        GeradorDefases gerador = new ConstrutorDeCenarioFixo();
        
        // 2. Gerar a lista de fases do jogo
        List<Fase> fases = gerador.gerar(3);
        
        // 3. Criar instância do Herói
        Guerreiro heroi = new Guerreiro("Aragorn");
        System.out.println("Herói criado: " + heroi.getNome());
        System.out.println();
        aguardar(2);
        
        // 4. Laço principal que passa por cada fase
        for (int i = 0; i < fases.size(); i++) {
            Fase fase = fases.get(i);
            
            // Iniciar a fase
            fase.iniciar(heroi);
            aguardar(2);
            
            // Obter a lista de monstros da fase (precisamos fazer cast para FaseDeCombate)
            if (fase instanceof ambientacao.fases.FaseDeCombate) {
                ambientacao.fases.FaseDeCombate faseCombate = (ambientacao.fases.FaseDeCombate) fase;
                List<Monstro> monstros = faseCombate.getMonstros();
                
                // Laço de combate para cada monstro da fase
                for (int j = 0; j < monstros.size(); j++) {
                    Monstro monstro = monstros.get(j);
                    
                    System.out.println("--- COMBATE CONTRA: " + monstro.getNome() + " ---");
                    aguardar(2);
                    
                    // Laço while que continua enquanto ambos estiverem vivos
                    while (heroi.estaVivo() && monstro.estaVivo()) {
                        System.out.println("----- NOVO TURNO -----");
                        
                        // Turno do herói
                        heroi.escolherAcao(monstro);
                        aguardar(2);
                        
                        // Se o monstro morreu, sair do loop
                        if (!monstro.estaVivo()) {
                            break;
                        }
                        
                        // Turno do monstro
                        monstro.escolherAcao(heroi);
                        aguardar(2);
                        
                        System.out.println("----- FIM DO TURNO -----");
                        System.out.println();
                        aguardar(2);
                    }
                    
                    // Verificar resultado do combate
                    if (!monstro.estaVivo()) {
                        System.out.println("🎉 " + monstro.getNome() + " foi derrotado!");
                        aguardar(2);
                        
                        // Verificar se o monstro é Lootavel e dropar loot
                        if (monstro instanceof Lootavel) {
                            Lootavel monstroLootavel = (Lootavel) monstro;
                            monstroLootavel.droparLoot();
                            aguardar(2);
                        }
                        
                        // Ganhar experiência
                        heroi.ganharExperiencia(monstro.getXpConcedido());
                        aguardar(2);
                        
                    } else if (!heroi.estaVivo()) {
                        System.out.println("💀 " + heroi.getNome() + " foi derrotado por " + monstro.getNome() + "!");
                        System.out.println("=== FIM DE JOGO ===");
                        return; // Termina o jogo
                    }
                    
                    System.out.println();
                    aguardar(2);
                }
            }
            
            // Verificar se a fase foi concluída
            if (fase.isConcluida()) {
                System.out.println("✅ Fase " + (i + 1) + " concluída com sucesso!");
                aguardar(2);
            } else {
                System.out.println("❌ Fase " + (i + 1) + " não foi concluída!");
                break;
            }
            
            System.out.println();
            aguardar(2);
        }
        
        // 5. Se o herói completou todas as fases
        if (heroi.estaVivo()) {
            System.out.println("🏆 🏆 🏆 PARABÉNS! 🏆 🏆 🏆");
            aguardar(2);
            System.out.println(heroi.getNome() + " completou todas as fases e venceu o jogo!");
            aguardar(2);
            System.out.println("=== FIM DE JOGO - VITÓRIA ===");
        }
    }
}