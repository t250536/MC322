package ambientacao.cenarios;

// TipoCenario.java
public enum TipoCenario {
    FLORESTA("Uma densa floresta cheia de mistérios e criaturas ocultas") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("O ar puro da floresta revitaliza o herói!");
            // Exemplo: cura 10% da vida máxima
            int cura = (int)(heroi.getVidaMaxima() * 0.1);
            heroi.receberCura(cura);
        }
    },
    
    CAVERNA("Uma caverna escura e úmida com ecos assustadores") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("A escuridão da caverna reduz sua visão e moral!");
            // Exemplo: reduz defesa temporariamente
            System.out.println("Defesa do herói reduzida temporariamente!");
        }
    },
    
    CASTELO("Um castelo imponente com armadilhas e guardas vigilantes") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("A arquitetura do castelo oferece pontos estratégicos!");
            // Exemplo: aumenta chance de crítico
            System.out.println("Chance de acerto crítico aumentada!");
        }
    },
    
    PANTANO("Um pântano traiçoeiro com águas paradas e neblina densa") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("O terreno lamacento do pântano dificulta seus movimentos!");
            // Exemplo: reduz velocidade
            System.out.println("Velocidade do herói reduzida!");
        }
    };
    
    private final String descricao;
    
    TipoCenario(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    // Método abstrato que cada constante deve implementar
    public abstract void aplicarEfeitos(Heroi heroi);
    
    @Override
    public String toString() {
        return name() + ": " + descricao;
    }
}