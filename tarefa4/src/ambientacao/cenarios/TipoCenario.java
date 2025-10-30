package ambientacao.cenarios;

import entidades.herois.Heroi;

public enum TipoCenario {
    FLORESTA("Uma densa floresta cheia de mistérios e criaturas ocultas") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("🌳 " + getDescricao());
            System.out.println("   O ar puro da floresta revitaliza o herói!");
            // Cura 10% da vida atual
            int cura = heroi.getVida() / 10;
            if (cura > 0) {
                heroi.receberCura(cura);
            }
        }
    },
    
    CAVERNA("Uma caverna escura e úmida com ecos assustadores") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("🕳️ " + getDescricao());
            System.out.println("   A escuridão da caverna reduz sua visão e moral!");
            // A caverna não tem efeitos negativos no exemplo simples
            // Em uma versão mais complexa, poderia reduzir atributos
        }
    },
    
    CASTELO("Um castelo imponente com armadilhas e guardas vigilantes") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("🏰 " + getDescricao());
            System.out.println("   A arquitetura do castelo oferece pontos estratégicos!");
            // O castelo inspira coragem - pequeno bônus de vida
            int bonusVida = 5;
            heroi.receberCura(bonusVida);
        }
    },
    
    PANTANO("Um pântano traiçoeiro com águas paradas e neblina densa") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("🌫️ " + getDescricao());
            System.out.println("   O terreno lamacento do pântano dificulta seus movimentos!");
            // O pântano pode causar um pequeno dano
            int dano = 3;
            System.out.println("   O herói sofre " + dano + " de dano pelo ambiente hostil!");
            heroi.receberDano(dano);
        }
    };
    
    private final String descricao;
    
    TipoCenario(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }

    //metodo para exibir informacoes basicas de todos os cenarios
    public static void exibirInformacoesBasicas() {
        System.out.println("\n=== CENÁRIOS DISPONÍVEIS ===");
        for (TipoCenario cenario : values()) {
            System.out.println("• " + cenario.name() + ": " + cenario.getDescricao());
        }
    }
    
    // Método abstrato que cada constante deve implementar
    public abstract void aplicarEfeitos(Heroi heroi);
    
    @Override
    public String toString() {
        return name() + ": " + descricao;
    }
}