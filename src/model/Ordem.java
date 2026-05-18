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
    public int getId() {
        return id;
    }

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

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTipoOperacao(TipoOperacao tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public void setStatusOrdem(StatusOrdem statusOrdem) {
        this.statusOrdem = statusOrdem;
    }

    public void setQuantidadeOrdem(double quantidadeOrdem) {
        this.quantidadeOrdem = quantidadeOrdem;
    }

    public void setPrecoLimite(double precoLimite) {
        this.precoLimite = precoLimite;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataExecucao(LocalDateTime dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    public void setCriptoativo(Criptoativo criptoativo) {
        this.criptoativo = criptoativo;
    }
}
