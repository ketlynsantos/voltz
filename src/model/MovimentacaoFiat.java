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
    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public double getValorMovimentado() {
        return valorMovimentado;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }
}
