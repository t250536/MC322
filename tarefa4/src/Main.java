import ambientacao.Dificuldade;
import ambientacao.cenarios.ConstrutorDeCenarioFixo;
import ambientacao.cenarios.TipoCenario;

import entidades.herois.Heroi;
import entidades.herois.Arqueiro;
import entidades.herois.Guerreiro;
import entidades.herois.Paladino;

import entidades.monstros.Monstro;
import entidades.monstros.Orc;
import entidades.monstros.Dragao;
import entidades.monstros.Goblin;

import interfaces.mundoCenario.Fase;
import interfaces.mundoCenario.GeradorDeFases;
import itens.armas.Arma;
import java.util.List;
import utilidades.InputManager;

public class Main {
    
    // Menu principal exatamente conforme especificação
    private static void exibirMenuPrincipal() {
        System.out.println("\nTERRAS SOMBRIAS – RPG");
        System.out.println("==========================================================");
        System.out.println("[1] Iniciar Novo Jogo");
        System.out.println("[2] Ver Informações das Classes de Heróis");
        System.out.println("[3] Ver Informações das Classes de Monstros");
        System.out.println("[4] Sair do Jogo");
        System.out.println("==========================================================");
    }

    // Menu pós-combate conforme especificação
    private static void exibirMenuPosCombate(boolean temLoot) {
        System.out.println("\n=== MENU PÓS-COMBATE ===");
        System.out.println("[1] Interagir com o Loot" + (temLoot ? "" : " (nenhum loot disponível)"));
        System.out.println("[2] Ver Informações do Personagem");
        System.out.println("[3] Desistir do Jogo");
        System.out.println("========================\n");
    }

    // Seleção de dificuldade
    private static Dificuldade selecionarDificuldade() {
        System.out.println("\n=== SELECIONE A DIFICULDADE ===");
        System.out.println("[1] Fácil");
        System.out.println("[2] Normal"); 
        System.out.println("[3] Difícil");
        System.out.println("==============================\n");
        
        int opcao = InputManager.lerInteiro("Digite sua opção (1-3): ", 1, 3);
        
        switch (opcao) {
            case 1: 
                System.out.println("Dificuldade Fácil selecionada!");
                return Dificuldade.FACIL;
            case 2: 
                System.out.println("Dificuldade Normal selecionada!");
                return Dificuldade.NORMAL;
            case 3: 
                System.out.println("Dificuldade Difícil selecionada!");
                return Dificuldade.DIFICIL;
            default: 
                return Dificuldade.NORMAL;
        }
    }

    // Simulação básica de interação com loot
    private static void interagirComLoot(Heroi heroi, Monstro monstroDerrotado) {
        System.out.println("\n=== INTERAGINDO COM LOOT ===");
        
        // Simular chance de drop baseada no monstro
        boolean temLoot = Math.random() > 0.3; // 70% de chance de ter loot
        
        if (temLoot) {
            System.out.println("Você encontrou os seguintes itens:");
            System.out.println("- 25 de ouro");
            System.out.println("- 1 Poção de Cura");
            System.out.println("- Fragmento de " + monstroDerrotado.getNome());
            
            boolean pegarItens = InputManager.lerSimNao("Deseja coletar os itens? (s/n)");
            if (pegarItens) {
                System.out.println("Itens coletados com sucesso!");
                // Aqui você pode adicionar a lógica real para adicionar itens ao inventário
            } else {
                System.out.println("Você deixou os itens para trás.");
            }
        } else {
            System.out.println("Nenhum item valioso foi encontrado...");
        }
        
        InputManager.esperarEnter("Pressione ENTER para continuar...");
    }

    // Processar menu pós-combate - CORRIGIDO
    private static boolean processarMenuPosCombate(Heroi heroi, Monstro monstroDerrotado) {
        while (true) {
            // Verificar se há loot disponível (simulação básica)
            boolean temLoot = Math.random() > 0.3;
            
            exibirMenuPosCombate(temLoot);
            int opcao = InputManager.lerInteiro("Escolha uma opção (1-3): ", 1, 3);
            
            switch (opcao) {
                case 1:
                    if (temLoot) {
                        interagirComLoot(heroi, monstroDerrotado);
                    } else {
                        System.out.println("Não há loot disponível para interagir.");
                        InputManager.esperarEnter("Pressione ENTER para continuar...");
                    }
                    return true; // Continuar jogo
                case 2:
                    System.out.println("\n=== INFORMAÇÕES DO PERSONAGEM ===");
                    heroi.status(); // CORREÇÃO: exibirStatus() → status()
                    InputManager.esperarEnter("Pressione ENTER para continuar...");
                    return true; // Continuar jogo
                case 3:
                    boolean confirmar = InputManager.lerSimNao("Tem certeza que deseja desistir? (s/n)");
                    if (confirmar) {
                        System.out.println("Desistindo do jogo...");
                        return false; // Sair do jogo
                    }
                    break; // Voltar ao menu se não confirmar
            }
        }
    }

    // Exibir informações dos heróis - CORRIGIDO
    private static void exibirInfosHerois() {
        Guerreiro guerreiroExemplo = new Guerreiro("Aragorn", 15, 100, null, 0, 0, 100, 0);
        Paladino paladinoExemplo = new Paladino("Uther", 12, 110, null, 0, 0, 100, 0);
        Arqueiro arqueiroExemplo = new Arqueiro("Legolas", 10, 90, null, 0, 0, 100, 0);

        System.out.println("\n=== INFORMAÇÕES DAS CLASSES DE HERÓIS ===");
        System.out.println("=== Paladino: ===");
        paladinoExemplo.status(); // CORREÇÃO: exibirStatus() → status()
        System.out.println("=== Guerreiro: ===");
        guerreiroExemplo.status(); // CORREÇÃO: exibirStatus() → status()
        System.out.println("=== Arqueiro: ===");
        arqueiroExemplo.status(); // CORREÇÃO: exibirStatus() → status()
    }

    // Exibir informações dos monstros - CORRIGIDO
    private static void exibirInfosMonstros() {
        Goblin goblinExemplo = new Goblin("Radbug", 8, 50, null, 20, List.of());
        Orc orcExemplo = new Orc("Gorbag", 12, 80, null, 40, List.of());
        Dragao dragaoExemplo = new Dragao("Ancalagon", 20, 100, null, 100, List.of());

        System.out.println("\n=== INFORMAÇÕES DAS CLASSES DE MONSTROS ===");
        System.out.println("=== Goblin: ===");
        goblinExemplo.status(); // CORREÇÃO: exibirStatus() → status()
        System.out.println("=== Orc: ===");
        orcExemplo.status(); // CORREÇÃO: exibirStatus() → status()
        System.out.println("=== Dragão: ===");
        dragaoExemplo.status(); // CORREÇÃO: exibirStatus() → status()
    }

    // Criar herói aleatório
    private static Heroi criarHeroiAleatorio() {
        int tipoHeroi = (int) (Math.random() * 3);
        switch (tipoHeroi) {
            case 0: 
                return new Guerreiro("Aragorn", 15, 100, null, 0, 0, 100, 0);
            case 1: 
                return new Paladino("Uther", 12, 110, null, 0, 0, 100, 0);
            case 2: 
                return new Arqueiro("Legolas", 10, 90, null, 0, 0, 100, 0);
            default: 
                return new Guerreiro("Aragorn", 15, 100, null, 0, 0, 100, 0);
        }
    }

    // Iniciar o jogo
    private static void iniciarJogo() {
        // Selecionar dificuldade
        Dificuldade dificuldade = selecionarDificuldade();

        // Configurar gerador de fases com dificuldade
        GeradorDeFases geradorFases = new ConstrutorDeCenarioFixo();
        List<Fase> fases = geradorFases.gerar(3, dificuldade);

        Heroi heroi = criarHeroiAleatorio();
        System.out.println("\nHerói selecionado: " + heroi.getNome());
        InputManager.esperarEnter("Pressione ENTER para começar a aventura...");

        for (int i = 0; i < fases.size(); i++) {
            Fase fase = fases.get(i);
            System.out.println("\n=== INICIANDO FASE " + (i + 1) + " ===");
            fase.iniciar(heroi);

            if (fase instanceof ambientacao.fases.FaseDeCombate) {
                ambientacao.fases.FaseDeCombate faseCombate = (ambientacao.fases.FaseDeCombate) fase;
                List<Monstro> monstros = faseCombate.getMonstros();

                for (int j = 0; j < monstros.size(); j++) {
                    Monstro monstro = monstros.get(j);
                    System.out.println("\n--- COMBATE CONTRA: " + monstro.getNome() + " ---");

                    // Combate automático
                    while (heroi.estaVivo() && monstro.estaVivo()) {
                        System.out.println("\n----- INÍCIO DO TURNO -----");
                        
                        // Turno do herói
                        heroi.escolherAcao(monstro);
                        
                        if (!monstro.estaVivo()) {
                            System.out.println("\n⭐ " + monstro.getNome() + " foi derrotado! ⭐");
                            
                            // Menu pós-combate após derrotar monstro
                            if (!processarMenuPosCombate(heroi, monstro)) {
                                return; // Jogador desistiu
                            }
                            break;
                        }
                        
                        // Turno do monstro
                        monstro.escolherAcao(heroi);
                        
                        System.out.println("----- FIM DO TURNO -----");
                    }

                    if (!heroi.estaVivo()) {
                        System.out.println("\n💀 GAME OVER! " + heroi.getNome() + " foi derrotado! 💀");
                        return;
                    }
                }
            }
            
            // Entre fases
            if (i < fases.size() - 1) {
                System.out.println("\n=== FASE " + (i + 1) + " COMPLETADA ===");
                InputManager.esperarEnter("Pressione ENTER para avançar para a próxima fase...");
            }
        }
        
        System.out.println("\n🎉 PARABÉNS! Você completou todas as fases! 🎉");
        InputManager.esperarEnter("Pressione ENTER para voltar ao menu principal...");
    }

    public static void main(String[] args) {
        try {
            System.out.println("=== TERRAS SOMBRIAS – RPG ===");

            while (true) {
                exibirMenuPrincipal();
                int opcao = InputManager.lerInteiro("Digite sua opção > ", 1, 4);
                
                switch (opcao) {
                    case 1:
                        iniciarJogo();
                        break;
                    case 2:
                        exibirInfosHerois();
                        InputManager.esperarEnter("Pressione ENTER para voltar ao menu principal...");
                        break;
                    case 3:
                        exibirInfosMonstros();
                        InputManager.esperarEnter("Pressione ENTER para voltar ao menu principal...");
                        break;
                    case 4:
                        boolean confirmar = InputManager.lerSimNao("Tem certeza que deseja sair? (s/n)");
                        if (confirmar) {
                            System.out.println("Saindo do jogo. Até a próxima!");
                            return;
                        }
                        break;
                }
            }
        } finally {
            InputManager.fecharScanner();
        }
    }
}