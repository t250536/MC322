package ambientacao.cenarios;

import ambientacao.Dificuldade;
import ambientacao.fases.FaseDeCombate;
import entidades.monstros.Monstro;
import entidades.monstros.Goblin;
import entidades.monstros.Orc;
import entidades.monstros.Dragao;
import interfaces.mundoCenario.Fase;
import interfaces.mundoCenario.GeradorDeFases;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementação de GeradorDeFases que cria fases aleatórias com monstros
 * baseados na dificuldade selecionada.
 * Gera ambientes e monstros variados para cada fase.
 * 
 * @author RPG Team
 * @version 1.0
 */
public class ConstrutorDeCenarioFixo implements GeradorDeFases {
    
    private Random random = new Random();
    
    /**
     * Gera uma lista de fases aleatórias baseadas na quantidade e dificuldade especificadas.
     * Cada fase possui um ambiente aleatório e monstros com estatísticas ajustadas pela dificuldade.
     *
     * @param quantidadeDeFases Número de fases a serem geradas
     * @param dificuldade Nível de dificuldade que afeta os monstros
     * @return Lista de fases geradas
     */
    @Override
    public List<Fase> gerar(int quantidadeDeFases, Dificuldade dificuldade) {
        List<Fase> fases = new ArrayList<>();
        
        System.out.println("🎮 Gerando " + quantidadeDeFases + " fases ALEATÓRIAS");
        
        for (int i = 1; i <= quantidadeDeFases; i++) {
            FaseDeCombate fase = criarFaseAleatoria(i, dificuldade);
            fases.add(fase);
        }
        
        System.out.println("✅ " + fases.size() + " fases geradas com sucesso!");
        return fases;
    }
    
    /**
     * Cria uma fase aleatória com ambiente e monstros baseados no número da fase e dificuldade.
     *
     * @param numeroFase Número da fase (afeta a força dos monstros)
     * @param dificuldade Dificuldade atual do jogo
     * @return Fase de combate gerada
     */
    private FaseDeCombate criarFaseAleatoria(int numeroFase, Dificuldade dificuldade) {
        String[] ambientes = {"FLORESTA", "CAVERNA", "CASTELO", "PÂNTANO", "RUÍNAS", "MONTANHA"};
        String ambiente = ambientes[random.nextInt(ambientes.length)];
        FaseDeCombate fase = new FaseDeCombate(ambiente);
        
        int quantidadeMonstros = 1 + random.nextInt(4);
        
        for (int i = 0; i < quantidadeMonstros; i++) {
            Monstro monstro = criarMonstroAleatorio(numeroFase, dificuldade);
            fase.adicionarMonstro(monstro);
        }
        
        return fase;
    }
    
    /**
     * Cria um monstro aleatório com estatísticas baseadas na fase e dificuldade.
     *
     * @param numeroFase Número da fase (monstros ficam mais fortes em fases posteriores)
     * @param dificuldade Dificuldade que ajusta vida e força
     * @return Monstro gerado
     */
    private Monstro criarMonstroAleatorio(int numeroFase, Dificuldade dificuldade) {
        int tipo = random.nextInt(3);
        
        int vidaBase = 30 + (numeroFase * 10);
        int forcaBase = 8 + (numeroFase * 3);
        int xpBase = 20 + (numeroFase * 5);
        
        int vida = (int)(vidaBase * dificuldade.getMultiplicadorVida());
        int forca = (int)(forcaBase * dificuldade.getMultiplicadorForca());
        int xp = xpBase;
        
        if (tipo == 0) {
            return new Goblin("Goblin", forca, vida, null, xp, new ArrayList<>());
        } else if (tipo == 1) {
            return new Orc("Orc", forca, vida, null, xp, new ArrayList<>());
        } else {
            return new Dragao("Dragão", forca, vida, null, xp, new ArrayList<>());
        }
    }
}