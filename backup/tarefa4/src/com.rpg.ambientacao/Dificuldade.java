package com.rpg.ambientacao;

public enum Dificuldade {
    FACIL(0.7, 0.7, "Fácil"),
    NORMAL(1.0, 1.0, "Normal"),
    DIFICIL(1.5, 1.5, "Difícil");
    
    private final double multiplicadorVida;
    private final double multiplicadorForca;
    private final String nomeExibicao;
    
    Dificuldade(double multiplicadorVida, double multiplicadorForca, String nomeExibicao) {
        this.multiplicadorVida = multiplicadorVida;
        this.multiplicadorForca = multiplicadorForca;
        this.nomeExibicao = nomeExibicao;
    }
    
    public double getMultiplicadorVida() {
        return multiplicadorVida;
    }
    
    public double getMultiplicadorForca() {
        return multiplicadorForca;
    }
    
    public String getNomeExibicao() {
        return nomeExibicao;
    }
    
    @Override
    public String toString() {
        return nomeExibicao;
    }
}