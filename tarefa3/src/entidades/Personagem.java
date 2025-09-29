package entidades;

import itens.armas.Arma;
import acoes.AcaoDeCombate;

public abstract class Personagem implements Combatente {
    private String nome;
    private int forca;
    private int vida;
    private int vidaMaxima; // Adicionei para controlar cura
    private Arma arma;

    //construtor
    public Personagem(String nome, int forca, int vida, Arma arma) {
        this.nome = nome;
        this.forca = forca;
        this.vida = vida;
        this.vidaMaxima = vida; // Inicializa vida máxima
        this.arma = arma;
    }

    //sets e gets
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String getNome() {
        return nome;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getForca() {
        return forca;
    }

    public void setVida(int vida) {
        this.vida = vida;
        if (this.vida < 0) this.vida = 0;
        if (this.vida > vidaMaxima) this.vida = vidaMaxima;
    }

    public int getVida() {
        return vida;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public Arma getArma() {
        return arma;
    }

    // Implementação da interface Combatente
    @Override
    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) {
            vida = 0;
        }
    }

    @Override
    public void receberCura(int cura) {
        vida += cura;
        if (vida > vidaMaxima) {
            vida = vidaMaxima;
        }
    }

    @Override
    public boolean estaVivo() {
        return vida > 0;
    }

    public int getDanototal() {
        if(arma != null) {
            return forca + arma.getDano();
        } else {
            return forca;
        }
    }

    public void status() {
        System.out.println("Nome: " + nome);
        System.out.println("Força: " + forca);
        System.out.println("Pontos de Vida: " + vida + "/" + vidaMaxima);
        if (arma != null) {
            System.out.println("Arma equipada: " + arma.getNome() + " (Dano: " + arma.getDano() + ", Nível Mínimo: " + arma.getminNivel() + ")");
        } else {
            System.out.println("Nenhuma arma equipada.");
        }
    }

    // Métodos abstratos - REMOVIDOS os que conflitam com novo sistema
    // public abstract void atacar(Personagem alvo); // REMOVIDO - substituído por sistema de ações
    // public abstract boolean HabilidadeEspecial(Personagem alvo); // REMOVIDO - substituído por sistema de ações
    
    // Método abstrato da interface Combatente - AGORA CORRETO
    public abstract AcaoDeCombate escolherAcao(Combatente alvo);
}