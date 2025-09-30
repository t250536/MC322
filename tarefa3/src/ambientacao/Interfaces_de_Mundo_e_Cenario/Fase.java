package ambientacao.interfaces_de_mundo_e_cenario;

public interface Fase {
    void iniciar(Heroi heroi);
    boolean isConcluida();
    String getTipoDeCenario();
}