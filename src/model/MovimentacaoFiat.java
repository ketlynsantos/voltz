package model;

import enums.TipoMovimentacao;

import java.time.LocalDateTime;

public class MovimentacaoFiat {
    private int id;
    private TipoMovimentacao tipoMovimentacao;
    private double valorMovimentado;
    private LocalDateTime dataMovimentacao;

    public MovimentacaoFiat(TipoMovimentacao tipoMovimentacao, double valorMovimentado) {
        this.tipoMovimentacao = tipoMovimentacao;
        this.valorMovimentado = valorMovimentado;
        this.dataMovimentacao = LocalDateTime.now();
    }

    // valor com impacto no saldo
    public double getValorComSinal() {
        return tipoMovimentacao == TipoMovimentacao.DEPOSITO
                ? valorMovimentado
                : -valorMovimentado;
    }

    // Getters
    public int getId() {
        return id;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public double getValorMovimentado() {
        return valorMovimentado;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public void setValorMovimentado(double valorMovimentado) {
        this.valorMovimentado = valorMovimentado;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
}
