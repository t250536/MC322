package com.rpg.entidades.herois;

import com.rpg.exceptions.NivelInsuficienteException;
import com.rpg.exceptions.RecursoInsuficienteException;
import com.rpg.itens.armas.Arma;
import com.rpg.interfaces.combate.AcaoDeCombate;
import com.rpg.interfaces.combate.Combatente;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rpg.entidades.Personagem;

public abstract class Heroi extends Personagem {
    private int nivel;
    private int experiencia;
    private int experienciaParaProximoNivel;
    private int sorte;
    private List<AcaoDeCombate> acoes;
    private Random random;
    private int mana;

    //construtor
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

    // Implementação do método escolherAcao da interface Combatente
    @Override
    public void escolherAcao(Combatente alvo) {
        // Ação normal da lista
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

    // Método para adicionar ações à lista
    public void adicionarAcao(AcaoDeCombate acao) {
        acoes.add(acao);
    }
    
    // Método para obter a lista de ações
    public List<AcaoDeCombate> getAcoes() {
        return acoes;
    }

    //gets e sets
    public int getNivel() {
        return nivel;
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
            nivel+=1;//subir de nivel
            experienciaParaProximoNivel += 150; //experiência necessária para o próximo nível
            System.out.println("================================");
            System.out.println(getNome() + " subiu para o nível " + nivel + "!");
            this.setVida(getVida()+30); // Aumenta a vida ao subir de nível
            this.setForca(getForca()+10); // Aumenta a força ao subir de nível
            System.out.println(getNome() + " ganhou +30 de vida e +10 de força!");
            System.out.println("================================");
        }
    }
    
    public void equiparArma(Arma novaArma) {
        setArma(novaArma);
        System.out.println(getNome() + " equipou a arma: " + novaArma.getNome());
    }

    // Novos métodos para o sistema de exceções
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
    
    public int getMana() {
        return mana;
    }
    
    public void setMana(int mana) {
        this.mana = mana;
    }
  
    @Override
    public void status() {
        super.status();
        System.out.println("nivel: " + nivel);
        System.out.println("Exp total: " + experiencia + "/Exp prox nivel: " + experienciaParaProximoNivel);
        System.out.println("Sorte atual: " + getSorte());
        System.out.println("Mana: " + mana);
        System.out.println("Ações disponíveis: " + acoes.size());
    }
    
    @Override
    public abstract boolean HabilidadeEspecial(Personagem alvo);
}