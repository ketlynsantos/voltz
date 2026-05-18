package model;

import enums.TipoAlerta;
import enums.TipoCondicao;

import java.time.LocalDateTime;

public class Alerta {
    private int id;
    private double valorAlvo;
    private double percentualVariacao;
    private TipoAlerta tipoAlerta;
    private TipoCondicao tipoCondicao;
    private boolean ativo;
    private boolean disparado;
    private Criptoativo criptoativo;
    private LocalDateTime dataDisparo;

    public Alerta(double valorAlvo, double percentualVariacao, TipoAlerta tipoAlerta, TipoCondicao tipoCondicao, Criptoativo criptoativo) {
        this.valorAlvo = valorAlvo;
        this.percentualVariacao = percentualVariacao;
        this.tipoAlerta = tipoAlerta;
        this.tipoCondicao = tipoCondicao;
        this.criptoativo = criptoativo;
        this.ativo = true;
        this.disparado = false;
    }

    // Verifica se o alerta deve ser disparado com base no tipo configurado
    public boolean verificarDisparo(double precoAnterior) {
        if (!ativo || disparado) return false;

        double precoAtual = criptoativo.getValorAtual();

        if (tipoAlerta == TipoAlerta.PRECO) {
            return verificarPorPreco(precoAtual);
        } else {
            return verificarPorVariacao(precoAnterior, precoAtual);
        }
    }

    // Verifica disparado baseado no preço atual do ativo
    private boolean verificarPorPreco(double precoAtual) {
        if (tipoCondicao == TipoCondicao.MAIOR_QUE) {
            return precoAtual >= valorAlvo;
        } else {
            return precoAtual <= valorAlvo;
        }
    }

    // Verifica disparado baseado na variação atual do preço
    private boolean verificarPorVariacao(double precoAnteior, double precoAtual) {
        double variacao = ((precoAtual - precoAnteior) / precoAnteior) * 100;

        if (tipoCondicao == TipoCondicao.MAIOR_QUE) {
            return variacao >= percentualVariacao;
        } else {
            return variacao <= -percentualVariacao;
        }
    }

    // Marca o alerta como ja disparado
    // (Evita disparos repetidos)
    public void marcarComoDisparado() {
        this.disparado = true;
        this.dataDisparo = LocalDateTime.now();
    }

    // Ativa o alerta
    public void ativar() {
        this.ativo = true;
    }

    // Desativa o alerta
    public void desativar() {
        this.ativo = false;
    }

    // Getters
    public double getValorAlvo() {
        return valorAlvo;
    }

    public double getPercentualVariacao() {
        return percentualVariacao;
    }

    public TipoAlerta getTipoAlerta() {
        return tipoAlerta;
    }

    public TipoCondicao getTipoCondicao() {
        return tipoCondicao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public boolean isDisparado() {
        return disparado;
    }

    public Criptoativo getCriptoativo() {
        return criptoativo;
    }

    public LocalDateTime getDataDisparo() {
        return dataDisparo;
    }

}
