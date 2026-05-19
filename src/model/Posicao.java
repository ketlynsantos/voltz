package model;

public class Posicao {
    private int id;
    private Criptoativo criptoativo;
    private double quantidadeAtivo;
    private double precoMedioCompra;
    private double valorTotalInvestido;

    public Posicao(Criptoativo ativo) {
        this.criptoativo = ativo;
        this.quantidadeAtivo = 0;
        this.precoMedioCompra = 0;
        this.valorTotalInvestido = 0;
    }

    // Compra (atualiza preço médio)
    public void adicionarCompra(double quantidade, double valorTotalCompra) {
        valorTotalInvestido += valorTotalCompra;
        quantidadeAtivo += quantidade;

        precoMedioCompra = valorTotalInvestido / quantidadeAtivo;
    }

    // Venda
    public void reduzirPosicao(double quantidadeVendida) {
        if (quantidadeVendida > quantidadeAtivo) {
            throw new RuntimeException("Quantidade maior que a disponível");
        }

        double custoMedio = precoMedioCompra * quantidadeVendida;

        quantidadeAtivo -= quantidadeVendida;
        valorTotalInvestido -= custoMedio;

        if (quantidadeAtivo == 0) {
            precoMedioCompra = 0;
            valorTotalInvestido = 0;
        }
    }

    // Valor atual baseado no mercado
    public double getValorAtual() {
        return quantidadeAtivo * criptoativo.getValorAtual();
    }

    // Lucro/prejuízo não realizado
    public double calcularResultadoNaoRealizado() {
        return getValorAtual() - valorTotalInvestido;
    }

    // Percentual de lucro
    public double calcularRentabilidade() {
        if (valorTotalInvestido == 0) return 0;

        return (calcularResultadoNaoRealizado() / valorTotalInvestido) * 100;
    }

    // Verifica se o ativo possui quantidade requisitada
    public boolean possuiQuantidade(double quantidade) {
        return quantidade <= quantidadeAtivo;
    }

    // Descrição
    public String getDescricao() {
        return criptoativo.getNome() + " | Quantidade: " + quantidadeAtivo + " | Valor Atual: R$" + getValorAtual();
    }

    // Getters
    public int getId() {
        return id;
    }

    public Criptoativo getCriptoativo() {
        return criptoativo;
    }

    public double getQuantidadeAtivo() {
        return quantidadeAtivo;
    }

    public double getPrecoMedioCompra() {
        return precoMedioCompra;
    }

    public double getValorTotalInvestido() {
        return valorTotalInvestido;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCriptoativo(Criptoativo criptoativo) {
        this.criptoativo = criptoativo;
    }

    public void setQuantidadeAtivo(double quantidadeAtivo) {
        this.quantidadeAtivo = quantidadeAtivo;
    }

    public void setPrecoMedioCompra(double precoMedioCompra) {
        this.precoMedioCompra = precoMedioCompra;
    }

    public void setValorTotalInvestido(double valorTotalInvestido) {
        this.valorTotalInvestido = valorTotalInvestido;
    }
}
