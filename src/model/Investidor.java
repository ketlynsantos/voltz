package model;

import java.util.ArrayList;
import java.util.List;

public class Investidor {
    int id;
    String nome;
    String email;
    String cpf;
    String senhaHash;
    List<Empresa> empresas;

    public Investidor() {
        this.empresas = new ArrayList<>();
    }

    public Investidor(int id, String nome, String email, String cpf, String senhaHash) {
        this.id = id;
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
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }
}
