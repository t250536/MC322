package com.rpg.ambientacao.cenarios;

import com.rpg.entidades.herois.Heroi;

public enum TipoCenario {
    FLORESTA("Uma densa floresta cheia de mistérios e criaturas ocultas. O ar puro da floresta revitaliza o herói!") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("O heroi se sente revigorado pela floresta! (+10% de vida)");
            // Cura 10% da vida atual
            int cura = heroi.getVida() / 10;
            if (cura > 0) {
                heroi.receberCura(cura);
            }
        }
    },

    CAVERNA("Uma caverna escura e úmida com ecos assustadores. A escuridão da caverna reduz a forca do herói.") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("O herói sente a escuridão da caverna enfraquecê-lo! (-3 de força).");
            // Reduz a força em 3 pontos
            heroi.setForca(heroi.getForca() - 3);
        }
    },

    CASTELO("Um castelo imponente com armadilhas e guardas vigilantes. A arquitetura do castelo oferece pontos estratégicos.") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("O herói se sente inspirado pelo castelo! (+3 de forca).");
            // bônus de forca
            heroi.setForca(heroi.getForca() + 3);
        }
    },

    PANTANO("Um pântano traiçoeiro com águas toxica e neblina densa. O ambiente reduz a vida o herói.") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("   A agua e neblina reduzem a vida heroi! (-3 de vida).");
            // O pântano pode causar um pequeno dano
            heroi.receberCura(-3);
        }
    };

    private final String descricao;

    TipoCenario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // metodo para exibir informacoes basicas de todos os cenarios
    public static void exibirInformacoesBasicas() {
        System.out.println("\n=== CENÁRIOS DISPONÍVEIS ===");
        for (TipoCenario cenario : values()) {
            System.out.println("+ " + cenario.name() + ": " + cenario.getDescricao());
        }
    }

    // Método abstrato que cada constante deve implementar
    public abstract void aplicarEfeitos(Heroi heroi);

    @Override
    public String toString() {
        return name() + ": " + descricao;
    }
}