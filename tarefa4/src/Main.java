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

import interfaces.combate.Combatente;
import interfaces.mundoCenario.Fase;
import interfaces.mundoCenario.GeradorDefases;
import interfaces.recompensa.Lootavel;
import itens.armas.Arma;
import java.util.List;
import utilidades.InputManager;

public class Main {

    // funcao com as escolhas do menu principal
    private static void menuescolha() {
        System.out.println("=================================");
        System.out.println("1 - INICIAR O JOGO");
        System.out.println("2 - VER INFORMAÇÕES DOS HEROIS");//
        System.out.println("3 - VER INFORMAÇÕES DOS MONSTROS");
        System.out.println("4 - VER INFORMAÇÕES DOS CENARIOS");//
        System.out.println("5 - SAIR DO JOGO");
        System.out.println("=================================");
    }

    // funcao para exibir o menu principal e ler a escolha do usuario
    private static int menuprincial() {
        menuescolha();
        return InputManager.lerInteiro("Escolha uma opção (1-5): ", 1, 5);
    }

    // funcao que cria herois exemplos para menu
    private static void exibirInfosHerois() {
        // criacao de herois exemplos
        Guerreiro guerreiroExemplo = new Guerreiro("Aragorn", 15, 100, null, 0, 0, 100, 0);
        Paladino paladinoExemplo = new Paladino("Uther", 12, 110, null, 0, 0, 100, 0);
        Arqueiro arqueiroExemplo = new Arqueiro("Legolas", 10, 90, null, 0, 0, 100, 0);

        // exibicao das informacoes dos herois
        System.out.println("=== INFORMAÇÕES DOS HERÓIS ===");
        System.out.println("obs: valores de forca e vida iniciais, aumentam conforme o nivel do heroi");
        System.out.println("=== Paladino: ===");
        paladinoExemplo.status();
        System.out.println("==================================");
        System.out.println("=== Guerreiro: ===");
        guerreiroExemplo.status();
        System.out.println("==================================");
        System.out.println("=== Arqueiro: ===");
        arqueiroExemplo.status();
        System.out.println("==================================");
    }

    // funcao que cria monstros exemplos para menu
    private static void exibirInfosMonstros() {
        // criacao de monstros exemplos
        Goblin goblinExemplo = new Goblin("Radbug", 8, 50, null, 20, List.of());
        Orc orcExemplo = new Orc("Gorbag", 12, 80, null, 40, List.of());
        Dragao dragaoExemplo = new Dragao("Ancalagon", 20, 100, null, 100, List.of());

        // exibicao das informacoes dos monstros
        System.out.println("=== INFORMAÇÕES DOS MONSTROS ===");
        System.out.println("obs: valores de forca e vida aumentam conforme a dificuldade do cenario");
        System.out.println("=== Goblin: ===");
        goblinExemplo.status();
        System.out.println("==================================");
        System.out.println("=== Orc: ===");
        orcExemplo.status();
        System.out.println("==================================");
        System.out.println("=== Dragão: ===");
        dragaoExemplo.status();
        System.out.println("==================================");
    }

    // funcao que gera um heroi aleatorio
    private static Heroi criarHeroiAleatorio() {
        int tipoHeroi = (int) (Math.random() * 3); // Gera um número entre 0 e 2
        Heroi heroi;
        switch (tipoHeroi) {
            case 0:
                heroi = new Guerreiro("Aragorn", 15, 100, null, 0, 0, 100, 0);
                break;
            case 1:
                heroi = new Paladino("Uther", 12, 110, null, 0, 0, 100, 0);
                break;
            case 2:
                heroi = new Arqueiro("Legolas", 10, 90, null, 0, 0, 100, 0);
                break;
            default:
                heroi = new Guerreiro("Aragorn", 15, 100, null, 0, 0, 100, 0);
        }
        return heroi;
    }

    // funcao que inicia o jogo
    private static void iniciarJogo() {
        System.out.println("Iniciando o jogo... (em construção)");
        // 1. Criar instância do GeradorDeFases
        GeradorDefases geradorFases = new ConstrutorDeCenarioFixo();
        // 2. Gerar a lista de fases do jogo
        List<Fase> fases = geradorFases.gerar(3);
        // 3. Criar instância do Herói aleatorio
        Heroi heroigerado = criarHeroiAleatorio();
        // 4. Laço principal que passa por cada fase
        for (int i = 0; i < fases.size(); i++) {
            Fase fase = fases.get(i);
            // Iniciar a fase
            fase.iniciar(heroigerado);
            // Obter a lista de monstros da fase (precisamos fazer cast para FaseDeCombate)
            if (fase instanceof ambientacao.fases.FaseDeCombate) {
                ambientacao.fases.FaseDeCombate faseCombate = (ambientacao.fases.FaseDeCombate) fase;
                List<Monstro> monstros = faseCombate.getMonstros();
                // Laço de combate para cada monstro da fase
                for (int j = 0; j < monstros.size(); j++) {
                    Monstro monstro = monstros.get(j);
                    System.out.println("--- COMBATE CONTRA: " + monstro.getNome() + " ---");
                    // Laço while que continua enquanto ambos estiverem vivos
                    while (heroigerado.estaVivo() && monstro.estaVivo()) {
                        System.out.println("----- NOVO TURNO -----");
                        // Turno do herói
                        heroigerado.escolherAcao(monstro);
                        // Se o monstro morreu, sair do loop
                        if (!monstro.estaVivo()) {
                            System.out.println(monstro.getNome() + " foi derrotado!");
                            InputManager.esperarEnter("Pressione ENTER para continuar...");
                            break;
                        }
                        // Turno do monstro
                        monstro.escolherAcao(heroigerado);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            // boas vindas
            System.out.println("=== BEM-VINDO AO RPG - THE GAME! ===");

            // laco que exibe o menu principal e processa as escolhas do usuario
            while (true) {
                int opcao = menuprincial();
                switch (opcao) {
                    case 1:
                        // iniciar o jogo
                        iniciarJogo();
                        break;
                    case 2:
                        // infos herois
                        exibirInfosHerois();
                        InputManager.esperarEnter("Pressione ENTER para voltar...");
                        break;
                    case 3:
                        // infos monstros
                        exibirInfosMonstros();
                        InputManager.esperarEnter("Pressione ENTER para voltar...");
                        break;
                    case 4:// infos cenarios
                        TipoCenario.exibirInformacoesBasicas();
                        InputManager.esperarEnter("Pressione ENTER para voltar...");
                        break;
                    case 5:
                        System.out.println("Saindo do jogo. Até a próxima!");
                        return; // Encerra o programa
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } finally {
            System.out.println("Obrigado por jogar RPG - THE GAME!");
            // InputManager.fecharScanner();
        }
    }
}