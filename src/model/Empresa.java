package model;

public class Empresa {
    private int id;
    private String razaoSocial;
    private String cnpj;
    private Carteira carteira;

    public Empresa(String razaoSocial, String cnpj) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.carteira = new Carteira();
    }

    // Patrimônio total da empresa
    public double calcularPatrimonio() {
        return carteira.calcularValorTotal();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }
}
