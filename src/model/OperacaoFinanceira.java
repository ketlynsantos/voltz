package model;

import enums.StatusOrdem;
import enums.TipoOperacao;

import java.time.LocalDateTime;

public abstract class OperacaoFinanceira {
    protected int id;
    protected TipoOperacao tipoOperacao;
    protected double quantidade;
    protected Criptoativo criptoativo;
    protected LocalDateTime dataOperacao;

    public OperacaoFinanceira(TipoOperacao tipoOperacao, double quantidade, Criptoativo criptoativo) {
        this.tipoOperacao = tipoOperacao;
        this.quantidade = quantidade;
        this.criptoativo = criptoativo;
        this.dataOperacao = LocalDateTime.now();
    }

    public abstract String getDescricao();

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

    public Criptoativo getCriptoativo() {
        return criptoativo;
    }

    public LocalDateTime getDataOperacao() {
        return dataOperacao;
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

    public void setCriptoativo(Criptoativo criptoativo) {
        this.criptoativo = criptoativo;
    }

    public void setDataOperacao(LocalDateTime dataOperacao) {
        this.dataOperacao = dataOperacao;
    }
}
