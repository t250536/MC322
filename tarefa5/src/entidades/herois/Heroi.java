package entidades.herois;

import entidades.Personagem;
import exceptions.NivelInsuficienteException;
import exceptions.RecursoInsuficienteException;
import itens.armas.Arma;
import interfaces.combate.AcaoDeCombate;
import interfaces.combate.Combatente;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe abstrata que representa um herói no jogo RPG.
 * Estende Personagem e adiciona sistema de nível, experiência, mana e ações de combate.
 * 
 * @author RPG Team
 * @version 1.0
 */
public abstract class Heroi extends Personagem {
    private int nivel;
    private int experiencia;
    private int experienciaParaProximoNivel;
    private int sorte;
    private List<AcaoDeCombate> acoes;
    private Random random;
    private int mana;

    /**
     * Construtor da classe Heroi.
     *
     * @param nome Nome do herói
     * @param forca Força do herói
     * @param vida Vida máxima do herói
     * @param arma Arma equipada
     * @param nivel Nível atual do herói
     * @param experiencia Experiência acumulada
     * @param experienciaParaProximoNivel Experiência necessária para próximo nível
     * @param sorte Valor de sorte do herói
     */
    public Heroi(String nome, int forca, int vida, Arma arma, int nivel, int experiencia, 
               int experienciaParaProximoNivel, int sorte) {
        super(nome, forca, vida, arma);
        this.experiencia = experiencia;
        this.nivel = nivel;
        this.experienciaParaProximoNivel = experienciaParaProximoNivel;
        this.sorte = sorte;
        this.acoes = new ArrayList<>();
        this.random = new Random();
        this.mana = 100;
    }

    /**
     * Escolhe aleatoriamente uma ação da lista de ações disponíveis e executa no alvo.
     * Se não houver ações disponíveis, usa o ataque básico.
     *
     * @param alvo Alvo da ação escolhida
     */
    @Override
    public void escolherAcao(Combatente alvo) {
        if (acoes.isEmpty()) {
            System.out.println(getNome() + " não tem ações disponíveis! Usando ataque básico.");
            atacar((Personagem) alvo);
            return;
        }
        
        int indiceAcao = random.nextInt(acoes.size());
        AcaoDeCombate acaoEscolhida = acoes.get(indiceAcao);
        
        System.out.println(getNome() + " escolheu ação: " + acaoEscolhida.getClass().getSimpleName());
        acaoEscolhida.executar(this, alvo);
    }

    /**
     * Adiciona uma ação à lista de ações disponíveis do herói.
     *
     * @param acao Ação de combate a ser adicionada
     */
    public void adicionarAcao(AcaoDeCombate acao) {
        acoes.add(acao);
    }

    /**
     * Retorna a lista de ações do herói.
     *
     * @return Lista de ações de combate
     */
    public List<AcaoDeCombate> getAcoes() {
        return acoes;
    }

    /**
     * Adiciona pontos de experiência ao herói e verifica se subiu de nível.
     *
     * @param pt_exp Pontos de experiência a serem adicionados
     */
    public void ganharExperiencia(int pt_exp) {
        experiencia += pt_exp;
        nivelUp();
    }

    /**
     * Verifica se o herói atingiu experiência suficiente para subir de nível.
     * Se sim, aumenta o nível, vida, força e ajusta a experiência para o próximo nível.
     */
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

    /**
     * Equipa uma arma no herói sem verificação de nível.
     *
     * @param novaArma Arma a ser equipada
     */
    public void equiparArma(Arma novaArma) {
        setArma(novaArma);
        System.out.println(getNome() + " equipou a arma: " + novaArma.getNome());
    }

    /**
     * Equipa uma arma no herói verificando se o nível é suficiente.
     * Lança exceção se o nível for insuficiente.
     *
     * @param novaArma Arma a ser equipada
     * @throws NivelInsuficienteException Se o nível do herói for menor que o nível mínimo da arma
     */
    public void equiparArmaComVerificacao(Arma novaArma) throws NivelInsuficienteException {
        if (nivel < novaArma.getminNivel()) {
            throw new NivelInsuficienteException(
                "Nível insuficiente para equipar " + novaArma.getNome() + 
                ". Necessário: " + novaArma.getminNivel() + ", seu nível: " + nivel
            );
        }
        setArma(novaArma);
        System.out.println(getNome() + " equipou a arma: " + novaArma.getNome());
    }

    /**
     * Usa a habilidade especial do herói, consumindo mana.
     * Lança exceção se não houver mana suficiente.
     *
     * @param alvo Alvo da habilidade especial
     * @throws RecursoInsuficienteException Se a mana atual for menor que o custo da habilidade
     */
    public void usarHabilidadeEspecial(Personagem alvo) throws RecursoInsuficienteException {
        int custoMana = 30;
        if (mana < custoMana) {
            throw new RecursoInsuficienteException(
                "Mana insuficiente! Necessário: " + custoMana + ", disponível: " + mana
            );
        }
        
        mana -= custoMana;
        System.out.println(getNome() + " usou habilidade especial! (-" + custoMana + " mana)");
        HabilidadeEspecial(alvo);
    }

    /**
     * Retorna a mana atual do herói.
     *
     * @return Mana atual
     */
    public int getMana() {
        return mana;
    }

    /**
     * Define o valor atual de mana do herói.
     *
     * @param mana Novo valor de mana
     */
    public void setMana(int mana) {
        this.mana = Math.max(0, mana);
    }

    /**
     * Recupera mana do herói.
     *
     * @param quantidade Quantidade de mana a recuperar
     */
    public void recuperarMana(int quantidade) {
        this.mana = Math.min(mana + quantidade, 100);
        System.out.println(getNome() + " recuperou " + quantidade + " de mana! Mana: " + mana);
    }

    // Getters e setters
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

    public int getSorte() {
        return sorte;
    }

    public void setSorte(int sorte) {
        this.sorte = sorte;
    }

    /**
     * Exibe o status completo do herói.
     */
    @Override
    public void status() {
        super.status();
        System.out.println("Nível: " + nivel);
        System.out.println("Experiência: " + experiencia + "/" + experienciaParaProximoNivel);
        System.out.println("Mana: " + mana);
        System.out.println("Sorte: " + sorte);
        System.out.println("Ações disponíveis: " + acoes.size());
        for (AcaoDeCombate acao : acoes) {
            System.out.println(" - " + acao.getClass().getSimpleName());
        }
    }
}