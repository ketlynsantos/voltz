package view;

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

        System.out.println("\nResumo:");
        System.out.println("Valor operação: R$" + valorOperacao);
        System.out.println("Taxa: R$" + taxa);
        System.out.println("Valor final: R$" + valorFinal);

        Ordem ordem = new Ordem(
                TipoOperacao.COMPRA,
                quantidade,
                ativo.getValorAtual(),
                ativo
        );

        Transacao transacao = ordem.executarOrdem(taxa);
        empresa.getCarteira().registrarTransacao(transacao);

        System.out.println("Compra realizada com sucesso!");
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
        System.out.println("Valor bruto: R$ " + valorOperacao);
        System.out.println("Taxa: R$ " + taxa);
        System.out.println("Valor líquido: R$ " + valorFinal);

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

        double resultado = empresa.getCarteira().calcularResultado();

        System.out.println("\nResultado da carteira: R$ " + resultado);
    }

    public void visualizarPatrimonioTotal(Investidor investidorLogado) {
        double patrimonio = investidorLogado.calcularPatrimonioTotal();

        System.out.println("\nPatrimônio total: R$ " + patrimonio);
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
