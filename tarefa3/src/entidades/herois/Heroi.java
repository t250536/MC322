package entidades.herois;

import entidades.Personagem;
import itens.armas.Arma;
import entidades.interfaces.Combatente;
import entidades.acoes.AcaoDeCombate;
import java.util.ArrayList;
import java.util.List; // Import adicionado

public abstract class Heroi extends Personagem {
    private int nivel;
    private int experiencia;
    private int experienciaParaProximoNivel;
    private int sorte;
    private List<AcaoDeCombate> acoesDeCombate;
    private int indiceAcaoAtual = 0; // Para simular escolha do jogador

    //construtor
    public Heroi(String nome, int forca, int vida, Arma arma, int nivel, int experiencia, 
                 int experienciaParaProximoNivel, int sorte) {
        super(nome, forca, vida, arma);
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.experienciaParaProximoNivel = experienciaParaProximoNivel;
        this.sorte = sorte;
        this.acoesDeCombate = new ArrayList<>();
        // REMOVIDO: escolherAcao() do construtor - agora é responsabilidade das subclasses
    }

    //gets e sets
    public int getNivel() {
        return nivel;
    }
    
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public int getExperiencia() {
        return experiencia;
    }
    
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    
    public int getExperienciaParaProximoNivel() {
        return experienciaParaProximoNivel;
    }
    
    public void setExperienciaParaProximoNivel(int experienciaParaProximoNivel) {
        this.experienciaParaProximoNivel = experienciaParaProximoNivel;
    }

    //seta a sorte como um valor aleatório entre 0 e 1
    private void setSorte() {
        this.sorte = (int) (Math.random() * 2);
    }
    
    public int getSorte() {
        this.setSorte();
        return sorte;
    } 

    //metodos
    public void ganharExperiencia(int pt_exp) {
        experiencia += pt_exp;
        nivelUp();
    }

    public void nivelUp() {
        if (experiencia >= experienciaParaProximoNivel) {
            nivel += 1;
            experienciaParaProximoNivel += 150;
            System.out.println("================================");
            System.out.println(getNome() + " subiu para o nível " + nivel + "!");
            this.setVida(getVida() + 30);
            this.setForca(getForca() + 10);
            System.out.println(getNome() + " ganhou +30 de vida e +10 de força!");
            System.out.println("================================");
        }
    }

    public void equiparArma(Arma novaArma) {
        // Verifica se o herói tem nível suficiente para a arma
        if (nivel >= novaArma.getminNivel()) {
            setArma(novaArma);
            System.out.println(getNome() + " equipou a arma: " + novaArma.getNome());
        } else {
            System.out.println(getNome() + " não tem nível suficiente para equipar " + novaArma.getNome());
            System.out.println("Nível necessário: " + novaArma.getminNivel() + ", seu nível: " + nivel);
        }
    }

    // IMPLEMENTAÇÃO CORRETA do método da interface Combatente
    @Override
    public AcaoDeCombate escolherAcao(Combatente alvo) {
        // Simula escolha do jogador alternando entre ações
        if (acoesDeCombate.isEmpty()) {
            return null;
        }
        
        AcaoDeCombate acaoEscolhida = acoesDeCombate.get(indiceAcaoAtual);
        indiceAcaoAtual = (indiceAcaoAtual + 1) % acoesDeCombate.size();
        
        System.out.println(getNome() + " escolheu: " + acaoEscolhida.getClass().getSimpleName());
        return acaoEscolhida;
    }

    // Método para adicionar ações (usado pelas subclasses)
    public void adicionarAcao(AcaoDeCombate acao) {
        acoesDeCombate.add(acao);
    }
    
    public List<AcaoDeCombate> getAcoesDeCombate() {
        return new ArrayList<>(acoesDeCombate); // Retorna cópia para proteger a lista original
    }

    @Override
    public void status() {
        super.status();
        System.out.println("Nível: " + nivel);
        System.out.println("Exp total: " + experiencia + "/Exp prox nível: " + experienciaParaProximoNivel);
        System.out.println("Sorte atual: " + getSorte());
        System.out.println("Ações disponíveis: " + acoesDeCombate.size());
    }

    // REMOVIDO: método abstrato HabilidadeEspecial - substituído pelo sistema de ações
    // @Override
    // public abstract boolean HabilidadeEspecial(Personagem alvo);
}