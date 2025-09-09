import entidades.Personagem;
import entidades.herois.Arqueiro;
import entidades.herois.Guerreiro;
import entidades.herois.Paladino;
import entidades.monstros.Dragao;
import entidades.monstros.Globin;
import entidades.monstros.Monstro;
import entidades.monstros.Orc;

//gerar numero aleatorio
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws Exception {
        int contadorturno = 1;
        Personagem copiamonstro;
        System.out.println("Bem vindo ao RPG!");
        tempoespera(3);
        // criando um heroi
        Personagem heroi = criarHeroi();

        // criando um array de monstros
        Personagem[] monstros = criarMonstros();

        // batalhando o heroi com o array de monstros
        ///introducao do heroi no cenario
        System.out.println("O heroi " + heroi.getNome() + " entra na floresta encantada e se depara com 3 monstros!");
        tempoespera(1);
        System.out.println("Ele ira batalhar contra os monstros Goblin, Dragao e Orc, boa sorte!");
        tempoespera(3);
        System.out.println("Status inicial do Heroi");
        heroi.status();
        tempoespera(2);

        for (Personagem monstro : monstros) {
            System.out.println("========= Turno " + contadorturno + " =========");
            contadorturno += 1;
            tempoespera(2);

            // condicao que verifica o resultado da batalha
            if (batalha(heroi, monstro)) {
                // ganho de experiencia quando vence um monstro
                heroi.ganharExperiencia(((Monstro) monstro).getXpConcedido());

            }
            // status apos a batalha
            exibeStatusTurno(heroi, monstro);
            // verifica quem ganhou
            if (heroi.getVida() <= 0) {
                System.out.println("Game Over!");
                break;
            }
        }
        if (heroi.getVida() > 0) {
            System.out.println("************************************************************");
            System.out.println("    Parabéns! " + heroi.getNome() + " derrotou todos os monstros!     ");
            System.out.println("************************************************************");
        }
    }

    // funcao que exibe o status dos personagens
    public static void exibeStatusTurno(Personagem heroi, Personagem monstro) {
        System.out.println(" ===== Status do Heroi apos a batalha: =====");
        heroi.status();
        System.out.println("====================================");
        System.out.println(" ===== Status do Monstro apos a batalha: =====");
        monstro.status();
        System.out.println("====================================");
        tempoespera(2);
    }

    // funcao que cria um heroi aleatorio e retorna o personagem
    public static Personagem criarHeroi() {
        int x = ThreadLocalRandom.current().nextInt(3);
        switch (x) {
            case 0:
                return new Guerreiro("Aragorn", 35, 150, 0, 0);
            case 1:
                return new Arqueiro("Legolas", 40, 200, 0, 0);
            case 2:
                return new Paladino("Valky", 45, 180, 0, 0);
            default:
                return null;
        }
    }

    // funcao que gera 3 monstros e retorna um array de personagens
    public static Personagem[] criarMonstros() {
        Personagem[] monstros = new Personagem[3];
        monstros[0] = new Globin("Chefe Goblin", 35, 80, 30, 0);
        monstros[1] = new Dragao("Dragao Smaug", 20, 130, 50, 0);
        monstros[2] = new Orc("Orc Gorbag", 30, 100, 35, 0);
        return monstros;
    }

    // função para esperar um certo tempo entre as ações
    public static void tempoespera(int time) {// onde o dobro de time eh 1 segundo
        try {
            Thread.sleep(time * 500);// multiplica por 500 para ficar mais rapido
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

    // funcao que simula a batalha entre heroi e monstro
    public static boolean batalha(Personagem heroi, Personagem monstro) {
        int contador = 1;
        System.out.println("A batalha entre " + heroi.getNome() + " e " + monstro.getNome() + " começou!");
        while (heroi.getVida() > 0 && monstro.getVida() > 0) {

            // contador de rodadas
            System.out.println("--- Rodada: " + contador + " ---");
            contador += 1;
            tempoespera(1);

            // funcao de ataque intercalado
            ataque(heroi, monstro);
            ataque(monstro, heroi);
            // verifica o vencedor
            System.out.println("--------------------");
            tempoespera(4);
        }

        // verifica quem venceu
        if (heroi.getVida() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
