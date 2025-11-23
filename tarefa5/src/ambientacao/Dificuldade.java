package ambientacao;

/**
 * Enum que define os níveis de dificuldade do jogo.
 * Cada dificuldade possui multiplicadores para vida e força dos monstros,
 * afetando o balanceamento do jogo.
 * 
 * @author RPG Team
 * @version 1.0
 */
public enum Dificuldade {
    FACIL(0.7, 0.7, "Fácil"),
    NORMAL(1.0, 1.0, "Normal"),
    DIFICIL(1.5, 1.5, "Difícil");
    
    private final double multiplicadorVida;
    private final double multiplicadorForca;
    private final String nomeExibicao;
    
    /**
     * Construtor do enum Dificuldade.
     *
     * @param multiplicadorVida Multiplicador aplicado na vida dos monstros
     * @param multiplicadorForca Multiplicador aplicado na força dos monstros
     * @param nomeExibicao Nome amigável para exibição
     */
    Dificuldade(double multiplicadorVida, double multiplicadorForca, String nomeExibicao) {
        this.multiplicadorVida = multiplicadorVida;
        this.multiplicadorForca = multiplicadorForca;
        this.nomeExibicao = nomeExibicao;
    }
    
    /**
     * Retorna o multiplicador de vida para esta dificuldade.
     *
     * @return Multiplicador de vida
     */
    public double getMultiplicadorVida() {
        return multiplicadorVida;
    }
    
    /**
     * Retorna o multiplicador de força para esta dificuldade.
     *
     * @return Multiplicador de força
     */
    public double getMultiplicadorForca() {
        return multiplicadorForca;
    }
    
    /**
     * Retorna o nome de exibição da dificuldade.
     *
     * @return Nome para exibição
     */
    public String getNomeExibicao() {
        return nomeExibicao;
    }
    
    /**
     * Retorna a representação em string da dificuldade.
     *
     * @return Nome de exibição
     */
    @Override
    public String toString() {
        return nomeExibicao;
    }
}