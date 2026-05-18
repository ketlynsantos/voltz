package model;

public abstract class Ativo {
    protected int id;
    protected String nome;
    protected String simbolo;

    public Ativo(String nome, String simbolo) {
        this.nome = nome;
        this.simbolo = simbolo;
    }

    // Descrição genérica do ativo
    public String getDescricao() {
        return nome + " (" + simbolo + ")";
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
}
