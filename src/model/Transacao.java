package model;

import enums.TipoOperacao;

import java.time.LocalDateTime;

public class Transacao extends OperacaoFinanceira {
    private double precoUnitario;
    private double valorTotal;
    private double taxa;

    private LocalDateTime dataTransacao;

    public Transacao(TipoOperacao tipoOperacao, double quantidade, double precoUnitario, double taxa, Criptoativo criptoativo) {
        super(tipoOperacao, quantidade, criptoativo);
        this.precoUnitario = precoUnitario;
        this.taxa = taxa;
        this.valorTotal = calcularValorTotal();
    }

    // Calcula valor total automaticamente
    private double calcularValorTotal() {
        double valorBruto = quantidade * precoUnitario;

        if (tipoOperacao == TipoOperacao.COMPRA) {
            return valorBruto + taxa;
        }
        return valorBruto - taxa;
    }

    // Getters
    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getTaxa() {
        return taxa;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    // Setters
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}
