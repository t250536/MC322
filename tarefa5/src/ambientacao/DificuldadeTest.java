package ambientacao;

import entidades.monstros.Monstro;
import ambientacao.cenarios.ConstrutorDeCenarioFixo;
import interfaces.mundoCenario.GeradorDeFases;
import java.util.List;

public class DificuldadeTest {

    public void testDificuldadeAfetaVidaMonstro() {
        GeradorDeFases geradorFacil = new ConstrutorDeCenarioFixo();
        GeradorDeFases geradorDificil = new ConstrutorDeCenarioFixo();

        List<interfaces.mundoCenario.Fase> fasesFacil = geradorFacil.gerar(1, Dificuldade.FACIL);
        List<interfaces.mundoCenario.Fase> fasesDificil = geradorDificil.gerar(1, Dificuldade.DIFICIL);

        // Acessa os monstros através de FaseDeCombate
        ambientacao.fases.FaseDeCombate faseFacil = (ambientacao.fases.FaseDeCombate) fasesFacil.get(0);
        ambientacao.fases.FaseDeCombate faseDificil = (ambientacao.fases.FaseDeCombate) fasesDificil.get(0);

        Monstro monstroFacil = faseFacil.getMonstros().get(0);
        Monstro monstroDificil = faseDificil.getMonstros().get(0);

        System.out.println("Teste: Dificuldade afeta vida - Fácil: " + monstroFacil.getVida() + ", Difícil: " + monstroDificil.getVida());
        System.out.println("Monstro difícil tem mais vida: " + (monstroDificil.getVida() > monstroFacil.getVida()));
    }

    public void testMultiplicadoresDificuldade() {
        System.out.println("Teste: Multiplicadores dificuldade");
        System.out.println("Fácil: " + Dificuldade.FACIL.getMultiplicadorVida());
        System.out.println("Normal: " + Dificuldade.NORMAL.getMultiplicadorVida());
        System.out.println("Difícil: " + Dificuldade.DIFICIL.getMultiplicadorVida());
    }

    public static void main(String[] args) {
        DificuldadeTest test = new DificuldadeTest();
        test.testDificuldadeAfetaVidaMonstro();
        test.testMultiplicadoresDificuldade();
    }
}