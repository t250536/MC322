package entidades.herois;

import entidades.Personagem;
import itens.armas.Arma;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Import necessário para a interface AcaoDeCombate
import entidades.interface_de_combate.AcaoDeCombate;

public abstract class Heroi extends Personagem {
    private int nivel;
    private int experiencia;
    private int experienciaParaProximoNivel;
    private int sorte;
    private List<AcaoDeCombate> acoes;
    private Random random;

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
    }

    // Método para adicionar ações à lista
    public void adicionarAcao(AcaoDeCombate acao) {
        acoes.add(acao);
    }
    
    // Método para obter a lista de ações (pode ser útil)
    public List<AcaoDeCombate> getAcoes() {
        return acoes;
    }

    // Implementação do método escolherAcao da interface Combatente
    @Override
    public void escolherAcao(Combatente alvo) {
        if (acoes.isEmpty()) {
            System.out.println(getNome() + " não tem ações disponíveis! Usando ataque básico.");
            // Fallback para o ataque normal se não houver ações
            atacar((Personagem) alvo);
            return;
        }
        
        // Simula escolha do jogador sem entrada de dados
        // Estratégia: prioriza habilidades especiais com base na sorte, senão usa ação aleatória
        if (getSorte() == 1 && HabilidadeEspecial((Personagem) alvo)) {
            // Se tiver sorte, tenta usar habilidade especial
            System.out.println(getNome() + " teve sorte e usou uma habilidade especial!");
        } else {
            // Escolhe uma ação aleatória da lista
            int indiceAcao = random.nextInt(acoes.size());
            AcaoDeCombate acaoEscolhida = acoes.get(indiceAcao);
            
            System.out.println(getNome() + " escolheu: " + acaoEscolhida.getClass().getSimpleName());
            acaoEscolhida.executar(this, alvo);
        }
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
  
    @Override
    public void status() {
        super.status(); // Chama o método da superclasse Personagem
        System.out.println("nivel: " + nivel);
        System.out.println("Exp total: " + experiencia + "/Exp prox nivel: " + experienciaParaProximoNivel);
        System.out.println("Sorte atual: " + getSorte());
        System.out.println("Ações disponíveis: " + acoes.size());
    }
    
    @Override
    public abstract boolean HabilidadeEspecial(Personagem alvo);//metodo abstrato para habilidade especial
}