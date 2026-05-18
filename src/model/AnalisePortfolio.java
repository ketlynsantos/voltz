package model;

import enums.NivelRisco;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnalisePortfolio {
    private int id;
    private LocalDateTime dataAnalise;
    private NivelRisco nivelRisco;

    public AnalisePortfolio() {
        this.dataAnalise = LocalDateTime.now();
    }

    // Define nível de risco baseado na carteira
    public void analisarRisco(Carteira carteira) {
        double total = carteira.calcularValorTotal();

        if (total < 1000) {
            nivelRisco = NivelRisco.BAIXO;
        } else if (total < 10000) {
            nivelRisco = NivelRisco.MEDIO;
        } else {
            nivelRisco = NivelRisco.ALTO;
        }
    }

    // Gerar sugestões
    public List<String> gerarSugestoes(Carteira carteira) {
        List<String> sugestoes = new ArrayList<>();

        if (carteira.calcularValorTotal() == 0) {
            sugestoes.add("Comece investindo em algum ativo");
        }

        if (carteira.calcularResultado() < 0) {
            sugestoes.add("Reavaliar estratégia, carteira em prejuízo");
        }

        sugestoes.add("Diversificar investimentos");

        return sugestoes;
    }

    // Getters
    public LocalDateTime getDataAnalise() {
        return dataAnalise;
    }

    public NivelRisco getNivelRisco() {
        return nivelRisco;
    }
}
