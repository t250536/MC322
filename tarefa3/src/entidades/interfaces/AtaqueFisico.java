package entidades.interfaces;

//O construtor recebe dois parâmetros: nome e danoBase, e os atribui aos respectivos atributos da classe.
public class AtaqueFisico implements AcaoDeCombate{
    private String nomeDoAtaque;
    private int danoBase;

    //O construtor recebe dois parâmetros: nome e danoBase, e os atribui aos respectivos atributos da classe.
    public void AtaqueFisico(String nomeDoAtaque, int danoBase){
        this.nomeDoAtaque = nomeDoAtaque;
        this.danoBase = danoBase;
    }

    //Método da Interface
    @Override
    public void executar(Combatente usuario, Combatente alvo){
        int dano = danoBase + (int)(Math.random() * 5);
        System.out.println(usuario.getNome() + " usa " + nomeDoAtaque + " em " + alvo.getNome() + "!");
        System.out.println("Causa " + dano + " de dano!");
        alvo.receberDano = dano;
    }

    //Permite que outras classes acessem o nome do ataque (já que o atributo nomeDoAtaque é private)
    public String getNome() {
        return nomeDoAtaque;
    }

}