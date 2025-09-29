package entidades.interfaces

public class Curar implements AcaoDeCombate {
    private String nome;
    private int cura;

    public Curar(String nome, int cura) {
        this.nome = nome;
        this.cura = cura;
    }

    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        System.out.println("✨ " + usuario.getNome() + " usa " + nome + "!");
        System.out.println("❤️  Restaura " + cura + " pontos de vida!");
        alvo.receberCura(cura);
    }

    public String getNome() {
        return nome;
    }
}