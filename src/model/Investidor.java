package model;

import java.util.ArrayList;
import java.util.List;

public class Investidor {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String senhaHash;
    private List<Empresa> empresas;

    public Investidor() {
        this.empresas = new ArrayList<>();
    }

    public Investidor(String nome, String email, String cpf, String senhaHash) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senhaHash = senhaHash;
        this.empresas = new ArrayList<>();
    }

    // Adiciona empresa
    public void adicionarEmpresa(Empresa novaEmpresa) {
        empresas.add(novaEmpresa);
    }

    // Patrimônio total
    public double calcularPatrimonioTotal() {
        return empresas.stream().mapToDouble(Empresa::calcularPatrimonio)
                .sum();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}
