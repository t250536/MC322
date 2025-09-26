import entidades.Personagem;
import entidades.herois.Heroi;
import entidades.herois.Guerreiro;
import entidades.herois.Arqueiro;
import entidades.herois.Paladino;
import entidades.monstros.Monstro;
import entidades.monstros.Orc;
import ambientacao.fases.Fase;
import ambientacao.cenarios.ConstrutorDeCenario;
import itens.armas.melee.Espada;
import itens.armas.melee.Faca;
import itens.armas.melee.Machado;
import itens.armas.Arma;

import java.util.List;
//gerar numero aleatorio
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws Exception {
        int contadorturno = 1;
        System.out.println("Bem vindo ao RPG!");
        tempoespera(3);
        // criando um heroi
        Personagem heroi = criarHeroi();

        // criando as fases e seus monstros
        List<Fase> fases = ConstrutorDeCenario.gerarFases(3);

        // batalhando o heroi
        for (Fase fase : fases) {
            System.out.println("============================================================");
            System.out.println("============ Fase de nivel " + fase.getLevel() + " esta comecando! ============");
            // anuncio da batalha
            System.out.println("O heroi " + heroi.getNome()
                    + " chegou ao seu proximo local de batalha: " + fase.getAmbiente()
                    + " de nivel " + fase.getLevel());

            // status inicial do heroi
            System.out.println("----------------------------");
            System.out.println("Status inicial do Heroi");
            heroi.status();
            System.out.println("----------------------------");
            tempoespera(2);

            // status dos monstros e batalha
            List<Monstro> monstros = fase.getMonstros();
            for (Monstro monstro : monstros) {
                System.out.println("========= Turno " + contadorturno + " =========");
                contadorturno += 1;
                tempoespera(2);

                // condicao que verifica o resultado da batalha
                if (batalha(heroi, monstro)) {
                    // ganho de experiencia quando vence um monstro
                    // downcast
                    if (heroi instanceof Heroi) {
                        Heroi hero = (Heroi) heroi;
                        hero.ganharExperiencia(monstro.getXpConcedido());
                    }
                }
                // status apos a batalha
                exibeStatusTurno(heroi, monstro);
                // verifica quem ganhou
                if (heroi.getVida() <= 0) {
                    System.out.println("Game Over!");
                    return;
                }
            }
            System.out.println("Parabéns! " + heroi.getNome() + " completou a fase de nivel " + fase.getLevel() + " !");
            tempoespera(2);
        }

        if (heroi.getVida() > 0) {
            System.out.println("************************************************************");
            System.out.println("    Parabéns! " + heroi.getNome()
                    + " derrotou todos os monstros e passou em todas as fases!     ");
            System.out.println("************************************************************");
        }
    }

    // funcao que exibe o status dos personagens
    public static void exibeStatusTurno(Personagem heroi, Personagem monstro) {
        System.out.println(" ..... Status do Heroi apos a batalha: .....");
        heroi.status();
        System.out.println("....................................................");
        System.out.println("..... Status do Monstro apos a batalha: .....");
        monstro.status();
        System.out.println("....................................................");
        tempoespera(2);
    }

    // funcao que cria um heroi aleatorio e retorna o personagem
    public static Personagem criarHeroi() {
        int x = ThreadLocalRandom.current().nextInt(3);
        switch (x) {
            case 0:
                // nivel, experiencia,experienciaParaProximoNivel,sorte
                return new Guerreiro("Aragorn", 85, 150,
                        new Espada(10, 0, "Espada Flamejante"),
                        0, 0, 170, 0, 0);
            case 1:
                return new Arqueiro("Legolas", 70, 200,
                        new Faca(7, 0, "Faca duplamente laminada"), 0, 0, 190, 0, 0);
            case 2:
                return new Paladino("Valky", 75, 180,
                        new Machado(25, 0, "Machado de Lenhador"), 0, 0, 175, 0, 0);
            default:
                return null;
        }
    }

    // função para esperar um certo tempo entre as ações
    public static void tempoespera(int time) {// onde o dobro de time eh 1 segundo
        try {
            Thread.sleep(time * 250);// multiplica por 250 para ficar mais rapido
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // funcao que realiza os ataques dos personagens
    public static void ataque(Personagem atacante, Personagem alvo) {
        if (atacante.HabilidadeEspecial(alvo)) {
            // System.out.println("Habilidade especial de "+atacante.getNome()+" usada!");
        } else {
            atacante.atacar(alvo);
        }

    }

    // funcao para dropar arma de monstro e equipar no heroi caso o dano for maior e
    // min nivel tambem
    private static void droparEEquiparArma(Personagem heroi, Monstro monstro) {
        Arma armaDropada = monstro.droparArma();
        if (armaDropada != null) {
            System.out.println("** " + monstro.getNome() + " dropou a arma: " + armaDropada.getNome() + " **");
            if (armaDropada.getDano() > heroi.getArma().getDano() && heroi instanceof Heroi
                    && ((Heroi) heroi).getNivel() >= armaDropada.getminNivel()) {
                System.out.println("** " + heroi.getNome() + " equipou a nova arma: " + armaDropada.getNome() + " **");
                heroi.setArma(armaDropada);
            } else {
                System.out.println("** " + heroi.getNome()
                        + " não equipou a nova arma devido ao nivel da arma minimo ou dano inferior ou igual. **");
            }
        } else {
            System.out.println("** " + monstro.getNome() + " não dropou nenhuma arma. **");
        }
    }

    // funcao que simula a batalha entre heroi e monstro
    public static boolean batalha(Personagem heroi, Personagem monstro) {
        int contador = 1;
        System.out.println("** A batalha entre " + heroi.getNome() + " e " + monstro.getNome() + " começou! **");
        System.out.println("Status inicial do Monstro:");
        monstro.status();
        System.out.println(".................");
        tempoespera(2);
        while (heroi.getVida() > 0 && monstro.getVida() > 0) {

            // contador de rodadas
            System.out.println("--- Rodada: " + contador + " ---");
            contador += 1;
            tempoespera(1);

            // funcao de ataque intercalado
            ataque(heroi, monstro);

            if (monstro.getVida() > 0) {
                ataque(monstro, heroi);
            }
            // verifica o vencedor
            System.out.println("--------------------");
            tempoespera(4);
        }
        // verifica quem venceu
        if (heroi.getVida() > 0) {
            System.out.println("** " + heroi.getNome() + " venceu a batalha! **");
            // testar a sorte para dropar arma
            int aleatorio = (int) (Math.random() * 2);
            int sorteHeroi = 0;
            if (heroi instanceof Heroi) {
                sorteHeroi = ((Heroi) heroi).getSorte();
                if (aleatorio == sorteHeroi) {
                    droparEEquiparArma(heroi, (Monstro) monstro);
                } else {
                    System.out.println("** " + heroi.getNome() + " nao teve sorte para o drop! **");
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
