package model;

import enums.StatusOrdem;
import enums.TipoOperacao;

import java.time.LocalDateTime;

public class Ordem {
    private int id;
    private TipoOperacao tipoOperacao;
    private StatusOrdem statusOrdem;
    private double quantidadeOrdem;
    private double precoLimite;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExecucao;
    private Criptoativo criptoativo;

    public Ordem(TipoOperacao tipoOperacao, double quantidadeOrdem, double precoLimite, Criptoativo criptoativo) {
        this.tipoOperacao = tipoOperacao;
        this.quantidadeOrdem = quantidadeOrdem;
        this.precoLimite = precoLimite;
        this.criptoativo = criptoativo;
        this.statusOrdem = StatusOrdem.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
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
                quantidadeOrdem,
                precoLimite,
                taxa,
                criptoativo
        );
    }

    // Getters
    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public StatusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public double getQuantidadeOrdem() {
        return quantidadeOrdem;
    }

    public double getPrecoLimite() {
        return precoLimite;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataExecucao() {
        return dataExecucao;
    }

    public Criptoativo getCriptoativo() {
        return criptoativo;
    }
}
