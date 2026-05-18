package model;

import enums.TipoOperacao;

import java.time.LocalDateTime;

public class Transacao {
    private int id;
    private TipoOperacao tipoOperacao;

    private double quantidade;
    private double precoUnitario;
    private double valorTotal;
    private double taxa;

    private Criptoativo criptoativo;
    private LocalDateTime dataTransacao;

    public Transacao() {}

    public Transacao(TipoOperacao tipoOperacao, double quantidade, double precoUnitario, double taxa, Criptoativo criptoativo) {
        this.tipoOperacao = tipoOperacao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.taxa = taxa;
        this.criptoativo = criptoativo;
    }

    // Calcula valor total automaticamente
    private double calcularValorTotal() {
        return (quantidade * precoUnitario) + taxa;
    }

    // Getters
    public int getId() {
        return id;
    }

    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getTaxa() {
        return taxa;
    }

    public Criptoativo getCriptoativo() {
        return criptoativo;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTipoOperacao(TipoOperacao tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public void setCriptoativo(Criptoativo criptoativo) {
        this.criptoativo = criptoativo;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}
