package model;

import enums.StatusOrdem;
import enums.TipoOperacao;

import java.time.LocalDateTime;

public class Ordem extends OperacaoFinanceira {
    private StatusOrdem statusOrdem;
    private double precoLimite;
    private LocalDateTime dataExecucao;

    public Ordem(TipoOperacao tipoOperacao, double quantidadeOrdem, double precoLimite, Criptoativo criptoativo) {
        super(tipoOperacao, quantidadeOrdem, criptoativo);
        this.precoLimite = precoLimite;
        this.statusOrdem = StatusOrdem.PENDENTE;
    }

    // Executa a ordem e gera uma transação
    public Transacao executarOrdem(double taxa) {
        if (statusOrdem != StatusOrdem.PENDENTE) {
            throw new RuntimeException("Ordem já foi processada!");
        }

        this.statusOrdem = StatusOrdem.EXECUTADA;
        this.dataExecucao = LocalDateTime.now();

        return new Transacao(
                tipoOperacao,
                quantidade,
                precoLimite,
                taxa,
                criptoativo
        );
    }

    @Override
    public String getDescricao() {
        return "Ordem de " + tipoOperacao + " de " + quantidade + " " + criptoativo.getSimbolo();
    }

    // Getters
    public StatusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public double getPrecoLimite() {
        return precoLimite;
    }

    public LocalDateTime getDataExecucao() {
        return dataExecucao;
    }

    // Setters
    public void setStatusOrdem(StatusOrdem statusOrdem) {
        this.statusOrdem = statusOrdem;
    }

    public void setPrecoLimite(double precoLimite) {
        this.precoLimite = precoLimite;
    }

    public void setDataExecucao(LocalDateTime dataExecucao) {
        this.dataExecucao = dataExecucao;
    }
}
