package model;

import enums.TipoOperacao;

import java.util.ArrayList;
import java.util.List;

public class Carteira {
    private int id;
    private double saldoDisponivelFiat;
    private List<MovimentacaoFiat> movimentacoes;
    private List<Transacao> transacoes;
    private List<Posicao> posicoes;

    public Carteira() {
        this.movimentacoes = new ArrayList<>();
        this.transacoes = new ArrayList<>();
        this.posicoes = new ArrayList<>();
    }

    // Registra entrada/saida de dinheiro
    public void registrarMovimentacao(MovimentacaoFiat movimentacao) {
        movimentacoes.add(movimentacao);
        saldoDisponivelFiat += movimentacao.getValorComSinal();
    }

    // Executar transação (compra/venda)
    public void registrarTransacao(Transacao transacao) {
        if (transacao.getTipoOperacao() == TipoOperacao.COMPRA) {
            saldoDisponivelFiat -= transacao.getValorTotal();
            atualizarPosicaoCompra(transacao);
        } else {
            saldoDisponivelFiat += transacao.getValorTotal();
            atualizarPosicaoVenda(transacao);
        }

        transacoes.add(transacao);
    }

    // Calcula valor total da carteira
    public double calcularValorTotal() {
        double totalCripto = posicoes.stream()
                .mapToDouble(Posicao::getValorAtual)
                .sum();

        return saldoDisponivelFiat + totalCripto;
    }

    // Retorna lucro/prejuízo
    public double calcularResultado() {
        double totalInvestido = transacoes.stream()
                .filter(transacao -> transacao.getTipoOperacao() == TipoOperacao.COMPRA)
                .mapToDouble(Transacao::getValorTotal)
                .sum();

        return calcularValorTotal() - totalInvestido;
    }

    // Busca posição por ativo
    public Posicao buscarPosicaoPorAtivo(Criptoativo ativo) {
        return posicoes.stream()
                .filter(posicao -> posicao.getCriptoativo().equals(ativo))
                .findFirst()
                .orElse(null);
    }

    // Atualiza posição na compra
    private void atualizarPosicaoCompra(Transacao transacao) {
        Posicao posicao = buscarPosicao(transacao.getCriptoativo());

        if (posicao == null) {
            posicao = new Posicao(transacao.getCriptoativo());
            posicoes.add(posicao);
        }

        posicao.adicionarCompra(transacao.getQuantidade(), transacao.getValorTotal());
    }

    // Atualiza posição na venda
    private void atualizarPosicaoVenda(Transacao transacao) {
        Posicao posicao = buscarPosicao(transacao.getCriptoativo());

        if (posicao != null) {
            posicao.reduzirPosicao(transacao.getQuantidade());
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public double getSaldoDisponivelFiat() {
        return saldoDisponivelFiat;
    }

    public List<MovimentacaoFiat> getMovimentacoes() {
        return movimentacoes;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public List<Posicao> getPosicoes() {
        return posicoes;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSaldoDisponivelFiat(double saldoDisponivelFiat) {
        this.saldoDisponivelFiat = saldoDisponivelFiat;
    }

    public void setMovimentacoes(List<MovimentacaoFiat> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public void setPosicoes(List<Posicao> posicoes) {
        this.posicoes = posicoes;
    }
}
