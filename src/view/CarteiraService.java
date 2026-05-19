package view;

import enums.TipoMovimentacao;
import enums.TipoOperacao;
import model.*;

import java.util.List;
import java.util.Scanner;

public class CarteiraService {
    private Scanner scanner;
    private EmpresaService empresaService;

    private static final double TAXA_PERCENTUAL = 0.02;

    public CarteiraService() {
        scanner = new Scanner(System.in);
        empresaService = new EmpresaService();
    }

    public void depositarSaldo(Investidor investidorLogado) {
        Empresa empresa = empresaService.selecionarEmpresa(investidorLogado);

        if (empresa == null) {
            return;
        }

        System.out.println("\n --- Depósito ---");

        System.out.print("Valor do depósito: R$");
        double valor = Double.parseDouble(scanner.nextLine());

        MovimentacaoFiat movimentacao = new MovimentacaoFiat(TipoMovimentacao.DEPOSITO, valor);
        empresa.getCarteira().registrarMovimentacao(movimentacao);

        System.out.println("\nDepósito realizado com sucesso!");
        System.out.printf("Saldo atual: R$%.2f%n", empresa.getCarteira().getSaldoDisponivelFiat());
    }

    public void comprarCriptoativo(Investidor investidorLogado, List<Criptoativo> mercado) {
        Empresa empresa = empresaService.selecionarEmpresa(investidorLogado);

        if (empresa == null) { return; }

        Criptoativo ativo = selecionarCriptoativo(mercado);

        if (ativo == null) { return ; }

        System.out.println("\n --- Compra ---\n");
        System.out.println(ativo.getDescricao());

        System.out.print("Quantidade: ");
        double quantidade = Double.parseDouble(scanner.nextLine());

        double valorOperacao = quantidade * ativo.getValorAtual();
        double taxa = valorOperacao * TAXA_PERCENTUAL;
        double valorFinal = valorOperacao + taxa;

        if (empresa.getCarteira().getSaldoDisponivelFiat() < valorFinal) {
            System.out.println("\nSaldo insuficiente.");
            return;
        }

        System.out.println("\nResumo:");
        System.out.printf("Valor operação: R$%.2f%n", valorOperacao);
        System.out.printf("Taxa: R$%.2f%n", taxa);
        System.out.printf("Saldo disponível: R$%.2f%n", empresa.getCarteira().getSaldoDisponivelFiat());
        System.out.printf("Valor final: R$%.2f%n", valorFinal);

        System.out.print("\nConfirmar compra? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (!confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Compra cancelada.");
            return;
        }

        Ordem ordem = new Ordem(
                TipoOperacao.COMPRA,
                quantidade,
                ativo.getValorAtual(),
                ativo
        );

        Transacao transacao = ordem.executarOrdem(taxa);
        empresa.getCarteira().registrarTransacao(transacao);

        System.out.println("Compra realizada com sucesso!");
        System.out.printf("\nSaldo restante: R$%.2f%n", empresa.getCarteira().getSaldoDisponivelFiat());
    }

    public void venderCriptoativo(Investidor investidorLogado, List<Criptoativo> mercado) {
        Empresa empresa = empresaService.selecionarEmpresa(investidorLogado);

        if (empresa == null) { return; }

        Criptoativo ativo = selecionarCriptoativo(mercado);

        if (ativo == null) { return ; }

        Posicao posicao = empresa.getCarteira().buscarPosicaoPorAtivo(ativo);
        if (posicao == null) {
            System.out.println("Você não possui este ativo.");
            return;
        }

        System.out.println("\n --- Venda ---\n");
        System.out.println(ativo.getDescricao());
        System.out.println("Quantidade disponível: " + posicao.getQuantidadeAtivo());

        System.out.print("Quantidade para venda: ");
        double quantidade = Double.parseDouble(scanner.nextLine());

        // Validação da quantidade
        if (!posicao.possuiQuantidade(quantidade)) {
            System.out.println(
                    "\nQuantidade insuficiente."
            );
            return;
        }

        double valorOperacao = quantidade * ativo.getValorAtual();
        double taxa = valorOperacao * TAXA_PERCENTUAL;
        double valorFinal = valorOperacao - taxa;

        System.out.println("\nResumo:");
        System.out.printf("Valor bruto: R$ %.2f%n", valorOperacao);
        System.out.printf("Taxa: R$ %.2f%n", taxa);
        System.out.printf("Valor líquido: R$ %.2f%n", valorFinal);

        System.out.print(
                "\nConfirmar venda? (S/N): "
        );

        String confirmacao = scanner.nextLine();

        if (!confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Venda cancelada.");
            return;
        }

        Ordem ordem = new Ordem(
                TipoOperacao.VENDA,
                quantidade,
                ativo.getValorAtual(),
                ativo
        );

        Transacao transacao = ordem.executarOrdem(taxa);
        empresa.getCarteira().registrarTransacao(transacao);

        System.out.println("\nVenda realizada com sucesso!");
        System.out.printf("\nSaldo atual: R$%.2f%n", empresa.getCarteira().getSaldoDisponivelFiat());
    }

    public void vizualizarCarteira(Investidor investidorLogado) {
        Empresa empresa = empresaService.selecionarEmpresa(investidorLogado);

        if (empresa == null) { return; }

        List<Posicao> posicoes = empresa.getCarteira().getPosicoes();

        if (posicoes.isEmpty()) {
            System.out.println("Nenhuma posição encontrada.");
            return;
        }

        System.out.println("\n --- Carteira - " + empresa.getRazaoSocial() + " ---");

        for (Posicao posicao : posicoes) {
            System.out.println(posicao.getDescricao());
        }
    }

    public void visualizarResultado(Investidor investidorLogado) {
        Empresa empresa = empresaService.selecionarEmpresa(investidorLogado);

        if (empresa == null) { return; }

        System.out.println("\n --- Resultado da Carteira ---");

        double resultadoTotal = 0;

        for (Posicao posicao : empresa.getCarteira().getPosicoes()) {
            double resultado = posicao.calcularResultadoNaoRealizado();

            resultadoTotal += resultado;

            System.out.println("\nAtivo: " + posicao.getCriptoativo().getSimbolo());
            System.out.printf("Investido: R$%.2f%n", posicao.getValorTotalInvestido());
            System.out.printf("Atual: R$%.2f%n", posicao.getValorAtual());
            System.out.printf("Resultado: R$%.2f%n", resultado);
            System.out.printf("Rentabilidade: %.2f%%%n", posicao.calcularRentabilidade());
        }
        System.out.printf("Resultado Total: R$%.2f%n", resultadoTotal);
    }

    public void visualizarPatrimonioTotal(Investidor investidorLogado) {
        double patrimonio = investidorLogado.calcularPatrimonioTotal();

        System.out.println("\nPatrimônio por Empresa:");
        for (Empresa empresa : investidorLogado.getEmpresas()) {
            System.out.printf("\n- %s: R$%.2f%n", empresa.getRazaoSocial(), empresa.calcularPatrimonio());
        }
        System.out.printf("Patrimônio total: R$%.2f%n", patrimonio);
    }

    public Criptoativo selecionarCriptoativo(List<Criptoativo> mercado) {
        System.out.println("\n --- Mercado ---");

        for (int i = 0; i < mercado.size(); i++) {
            Criptoativo ativo = mercado.get(i);

            System.out.println((i + 1) + " - " + ativo.getDescricao() + "\n");
        }

        System.out.print("Escolha o ativo: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        if (opcao < 1 || opcao > mercado.size()) {
            System.out.println("Ativo inválido.");
            return null;
        }

        return mercado.get(opcao - 1);
    }
}
