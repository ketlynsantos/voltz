package model;

import enums.TipoMovimentacao;
import enums.TipoOperacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Carteira {
    private int id;
    private double saldoDisponivelFiat;
    private List<MovimentacaoFiat> movimentacoes;
    private List<Transacao> transacoes;
    private List<Posicao> posicoes;
    private HashMap<String, Posicao> posicoesPorAtivo;

    public Carteira() {
        this.movimentacoes = new ArrayList<>();
        this.transacoes = new ArrayList<>();
        this.posicoes = new ArrayList<>();

        posicoesPorAtivo = new HashMap<>();
    }

    // Registra entrada/saida de dinheiro
    public void registrarMovimentacao(MovimentacaoFiat movimentacao) {
        movimentacoes.add(movimentacao);
        saldoDisponivelFiat += movimentacao.getValorComSinal();
    }

    // Overload
    public void registrarMovimentacao(TipoMovimentacao tipoMovimentacao, double valor) {
        MovimentacaoFiat movimentacaoFiat = new MovimentacaoFiat(tipoMovimentacao, valor);
        registrarMovimentacao(movimentacaoFiat);
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
        return posicoesPorAtivo.get(ativo.getSimbolo());
    }

    // Atualiza posição na compra
    private void atualizarPosicaoCompra(Transacao transacao) {
        Posicao posicao = buscarPosicaoPorAtivo(transacao.getCriptoativo());

        if (posicao == null) {
            posicao = new Posicao(transacao.getCriptoativo());
            posicoes.add(posicao);

            posicoesPorAtivo.put(
                    transacao.getCriptoativo().getSimbolo(),
                    posicao
            );
        }

        posicao.adicionarCompra(transacao.getQuantidade(), transacao.getValorTotal());
    }

    // Atualiza posição na venda
    private void atualizarPosicaoVenda(Transacao transacao) {
        Posicao posicao = buscarPosicaoPorAtivo(transacao.getCriptoativo());

        if (posicao != null) {
            posicao.reduzirPosicao(transacao.getQuantidade());

            if (posicao.getQuantidadeAtivo() == 0) {
                posicoes.remove(posicao);

                posicoesPorAtivo.remove(
                        transacao.getCriptoativo().getSimbolo()
                );
            }
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

    public HashMap<String, Posicao> getPosicoesPorAtivo() {
        return posicoesPorAtivo;
    }

    public void setPosicoesPorAtivo(HashMap<String, Posicao> posicoesPorAtivo) {
        this.posicoesPorAtivo = posicoesPorAtivo;
    }
}
