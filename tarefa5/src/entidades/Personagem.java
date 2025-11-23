package entidades;

import itens.armas.Arma;
import interfaces.combate.Combatente;

/**
 * Classe abstrata que representa um personagem no jogo RPG.
 * Implementa a interface Combatente e serve como base para heróis e monstros.
 * Gerencia atributos básicos como nome, força, vida e arma equipada.
 * 
 * @author RPG Team
 * @version 1.0
 */
public abstract class Personagem implements Combatente {
    private String nome;
    private int forca;
    private int vida;
    private Arma arma;

    /**
     * Construtor da classe Personagem.
     *
     * @param nome Nome do personagem
     * @param forca Valor da força do personagem
     * @param vida Pontos de vida do personagem
     * @param arma Arma equipada pelo personagem (pode ser null)
     */
    public Personagem(String nome, int forca, int vida, Arma arma) {
        this.nome = nome;
        this.forca = forca;
        this.vida = vida;
        this.arma = arma;
    }

    /**
     * Retorna o nome do personagem.
     *
     * @return Nome do personagem
     */
    @Override
    public String getNome() {
        return nome;
    }

    /**
     * Verifica se o personagem está vivo.
     *
     * @return true se o personagem tem vida maior que 0, false caso contrário
     */
    @Override
    public boolean estaVivo() {
        return vida > 0;
    }

    /**
     * Aplica dano ao personagem, reduzindo seus pontos de vida.
     * Garante que a vida não fique negativa.
     *
     * @param dano Quantidade de dano a ser aplicada
     */
    @Override
    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) {
            vida = 0;
        }
        System.out.println(nome + " recebeu " + dano + " de dano! Vida: " + vida);
    }

    /**
     * Aplica cura ao personagem, aumentando seus pontos de vida.
     *
     * @param cura Quantidade de cura a ser aplicada
     */
    @Override
    public void receberCura(int cura) {
        vida += cura;
        System.out.println(nome + " recebeu " + cura + " de cura! Vida: " + vida);
    }

    /**
     * Escolhe e executa uma ação de combate contra o alvo.
     * Método abstrato que deve ser implementado pelas subclasses.
     *
     * @param alvo Alvo da ação de combate
     */
    @Override
    public abstract void escolherAcao(Combatente alvo);

    /**
     * Ataca um personagem alvo, causando dano.
     * Método abstrato que deve ser implementado pelas subclasses.
     *
     * @param alvo Alvo do ataque
     */
    public abstract void atacar(Personagem alvo);

    /**
     * Retorna o dano total do personagem (força + dano da arma).
     *
     * @return Dano total calculado
     */
    public int getDanoTotal() {
        int danoBase = getForca();
        int danoArma = (getArma() != null) ? getArma().getDano() : 0;
        return danoBase + danoArma;
    }

    // Métodos getters e setters
    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Exibe o status do personagem.
     */
    public void status() {
        System.out.println("Nome: " + nome);
        System.out.println("Força: " + forca);
        System.out.println("Vida: " + vida);
        System.out.println("Arma: " + (arma != null ? arma.getNome() : "Nenhuma"));
        System.out.println("Dano Total: " + getDanoTotal());
    }

    /**
     * Método abstrato para habilidade especial.
     * Deve ser implementado pelas subclasses.
     *
     * @param alvo Alvo da habilidade especial
     * @return true se a habilidade foi executada com sucesso
     */
    public abstract boolean HabilidadeEspecial(Personagem alvo);
}