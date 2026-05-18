package model;

import java.util.ArrayList;
import java.util.List;

public class Criptoativo {
    private int id;
    private String nome;
    private String simbolo;
    private double valorAtual;
    private List<HistoricoCotacao> historico;

    public Criptoativo(String nome, String simbolo) {
        this.nome = nome;
        this.simbolo = simbolo;
        this.historico = new ArrayList<>();
    }

    // Atualiza cotação e salva histórico
    public void atualizarCotacao(double novoValor) {
        this.valorAtual = novoValor;

        historico.add(new HistoricoCotacao(novoValor));
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public List<HistoricoCotacao> getHistorico() {
        return historico;
    }
}
