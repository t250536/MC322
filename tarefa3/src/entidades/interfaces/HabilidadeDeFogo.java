public class HabilidadeDeFogo implements AcaoDeCombate {
    private String nome;
    private int dano;

    public HabilidadeDeFogo(String nome, int dano) {
        this.nome = nome;
        this.dano = dano;
    }

    @Override
    public void executar(Combatente usuario, Combatente alvo) {
        int danoFinal = dano + (int)(Math.random() * 8);
        System.out.println("🔥 " + usuario.getNome() + " conjura " + nome + "!");
        System.out.println("💫 Causa " + danoFinal + " de dano mágico!");
        alvo.receberDano(danoFinal);
    }

    public String getNome() {
        return nome;
    }
}