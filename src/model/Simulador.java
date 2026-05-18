package model;

public class Simulador {
    private int id;
    private double saldoSimulado;

    public Simulador(double saldoSimulado) {
        this.saldoSimulado = saldoSimulado;
    }

    // Simula variação/desvalorização de um ativo
    public double simularCenario(Criptoativo ativo, double percentualVariacao) {
        double precoAtual = ativo.getValorAtual();
        double precoSimulado = precoAtual * (1 + percentualVariacao / 100);

        return saldoSimulado * (precoSimulado / precoAtual);
    }

    // Simula carteira inteira
    public double simularCarteira(Carteira carteira, double percentualVariacao) {
        double valorAtual = carteira.calcularValorTotal();
        return valorAtual * (1 + percentualVariacao / 100);
    }

    // Getters
    public int getId() {
        return id;
    }

    public double getSaldoSimulado() {
        return saldoSimulado;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSaldoSimulado(double saldoSimulado) {
        this.saldoSimulado = saldoSimulado;
    }
}
