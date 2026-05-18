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
    public int getId() {
        return id;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public double getValorCotacao() {
        return valorCotacao;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setValorCotacao(double valorCotacao) {
        this.valorCotacao = valorCotacao;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
