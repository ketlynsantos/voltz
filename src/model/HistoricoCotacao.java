package model;

import java.time.LocalDateTime;

public class HistoricoCotacao {
    private int id;
    private double valorCotacao;
    private LocalDateTime dataRegistro;

    public HistoricoCotacao(double valorCotacao) {
        this.valorCotacao = valorCotacao;
        this.dataRegistro = LocalDateTime.now();
    }

    // Getters
    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public double getValorCotacao() {
        return valorCotacao;
    }
}
