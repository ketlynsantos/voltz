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

    public String getDescricao() {
        return "Ativo: " + nome +
                " (" + simbolo + ")" +
                "\nPreço atual: R$" + valorAtual;
    }

    // Getters
    public int getId() {
        return id;
    }

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

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public void setHistorico(List<HistoricoCotacao> historico) {
        this.historico = historico;
    }
}
