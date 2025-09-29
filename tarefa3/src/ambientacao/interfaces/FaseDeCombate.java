package ambientacao.interfaces;

import entidades.herois.Heroi;
import entidades.monstros.Monstro;
import entidades.interfaces.Combatente;
import entidades.interfaces.Lootavel;
import itens.Item;
import java.util.ArrayList;
import java.util.List;

public class FaseDeCombate implements Fase {
    private TipoCenario cenario;
    private List<Monstro> monstros;
    private boolean concluida;
    private int monstroAtualIndex;
    private String descricao;

    // Construtor
    public FaseDeCombate(TipoCenario cenario, String descricao) {
        this.cenario = cenario;
        this.descricao = descricao;
        this.monstros = new ArrayList<>();
        this.concluida = false;
        this.monstroAtualIndex = 0;
    }

    // Construtor alternativo
    public FaseDeCombate(TipoCenario cenario, String descricao, List<Monstro> monstros) {
        this(cenario, descricao);
        if (monstros != null) {
            this.monstros.addAll(monstros);
        }
    }

    @Override
    public void iniciar(Heroi heroi) {
        System.out.println("\n🎯 ========== INICIANDO FASE ==========");
        System.out.println("🌍 Cenário: " + cenario.getDescricao());
        System.out.println("📝 " + descricao);
        
        // Aplica efeitos do cenário no herói
        cenario.aplicarEfeitos(heroi);
        
        System.out.println("👹 Monstros nesta fase: " + monstros.size());
        for (int i = 0; i < monstros.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + monstros.get(i).getNome() + 
                             " (Vida: " + monstros.get(i).getVida() + ")");
        }
        System.out.println("====================================\n");
        
        monstroAtualIndex = 0;
        concluida = false;
    }

    @Override
    public boolean isConcluida() {
        // A fase está concluída quando todos os monstros foram derrotados
        // OU se não há monstros na fase (caso especial)
        return concluida || monstros.isEmpty() || todosMonstrosDerrotados();
    }

    @Override
    public TipoCenario getTipoDeCenario() {
        return cenario;
    }

    // Métodos para gerenciar monstros
    public void adicionarMonstro(Monstro monstro) {
        monstros.add(monstro);
    }

    public void adicionarMonstros(List<Monstro> monstros) {
        this.monstros.addAll(monstros);
    }

    public List<Monstro> getMonstros() {
        return new ArrayList<>(monstros);
    }

    public Monstro getMonstroAtual() {
        if (monstroAtualIndex < monstros.size()) {
            return monstros.get(monstroAtualIndex);
        }
        return null;
    }

    public boolean temMonstrosVivos() {
        for (Monstro monstro : monstros) {
            if (monstro.estaVivo()) {
                return true;
            }
        }
        return false;
    }

    public boolean todosMonstrosDerrotados() {
        return !temMonstrosVivos();
    }

    public void proximoMonstro() {
        monstroAtualIndex++;
        if (monstroAtualIndex >= monstros.size()) {
            concluida = true;
            System.out.println("🎉 Todos os monstros da fase foram derrotados!");
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Método para executar um turno de combate completo
    public boolean executarTurno(Heroi heroi) {
        Monstro monstroAtual = getMonstroAtual();
        
        if (monstroAtual == null || !monstroAtual.estaVivo()) {
            proximoMonstro();
            monstroAtual = getMonstroAtual();
        }
        
        if (monstroAtual == null) {
            return false; // Não há mais monstros
        }
        
        System.out.println("\n⚔️  ===== COMBATE: " + heroi.getNome() + " vs " + monstroAtual.getNome() + " =====");
        
        // Turno do herói
        if (heroi.estaVivo()) {
            System.out.println("\n🎯 === TURNO DO HERÓI ===");
            executarAcao(heroi, monstroAtual);
        }
        
        // Verifica se o monstro foi derrotado
        if (!monstroAtual.estaVivo()) {
            System.out.println("💀 " + monstroAtual.getNome() + " foi derrotado!");
            
            // Processa loot se o monstro for Lootavel
            if (monstroAtual instanceof Lootavel) {
                Item loot = ((Lootavel) monstroAtual).droparLoot();
                if (loot != null) {
                    System.out.println("🎁 " + heroi.getNome() + " encontrou: " + loot.getNome());
                    // Aqui você poderia adicionar o item ao inventário do herói
                }
            }
            
            // Concede experiência ao herói
            if (heroi instanceof entidades.herois.Heroi) {
                ((entidades.herois.Heroi) heroi).ganharExperiencia(monstroAtual.getXpConcedido());
            }
            
            proximoMonstro();
            return true;
        }
        
        // Turno do monstro
        if (monstroAtual.estaVivo()) {
            System.out.println("\n👹 === TURNO DO MONSTRO ===");
            executarAcao(monstroAtual, heroi);
        }
        
        // Verifica se o herói foi derrotado
        if (!heroi.estaVivo()) {
            System.out.println("💀 " + heroi.getNome() + " foi derrotado!");
            return false;
        }
        
        return true;
    }
    
    private void executarAcao(Combatente atacante, Combatente alvo) {
        try {
            // Escolhe e executa a ação
            entidades.acoes.AcaoDeCombate acao = atacante.escolherAcao(alvo);
            if (acao != null) {
                acao.executar(atacante, alvo);
            } else {
                System.out.println(atacante.getNome() + " não conseguiu escolher uma ação!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao executar ação: " + e.getMessage());
            // Fallback: ataque básico
            System.out.println(atacante.getNome() + " usa um ataque básico!");
            alvo.receberDano(5);
        }
    }

    // Método para executar combate completo até o fim
    public boolean executarCombateCompleto(Heroi heroi) {
        System.out.println("🏁 ===== INICIANDO COMBATE COMPLETO =====");
        
        while (heroi.estaVivo() && temMonstrosVivos()) {
            if (!executarTurno(heroi)) {
                break;
            }
            
            // Pequena pausa visual entre turnos
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Continua normalmente
            }
        }
        
        boolean vitoriaHeroi = heroi.estaVivo() && !temMonstrosVivos();
        
        if (vitoriaHeroi) {
            System.out.println("\n🎊 ===== VITÓRIA! =====");
            System.out.println(heroi.getNome() + " venceu a fase!");
        } else if (!heroi.estaVivo()) {
            System.out.println("\n💀 ===== DERROTA! =====");
            System.out.println(heroi.getNome() + " foi derrotado!");
        }
        
        return vitoriaHeroi;
    }

    @Override
    public String toString() {
        return "FaseDeCombate{" +
                "cenario=" + cenario +
                ", monstros=" + monstros.size() +
                ", concluida=" + concluida +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}