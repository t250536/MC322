package ambientacao.interfaces_de_mundo_e_cenario;

public interface Evento {
    boolean verificarGatilho(Heroi heroi); // Corrigido o nome do método (era "verificarCatilho")
    void executar(Heroi heroi);
}