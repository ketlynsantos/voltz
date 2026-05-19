package view;

import enums.TipoMovimentacao;
import enums.TipoOperacao;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaInvestimentos {
    private Scanner scanner;

    // Lista de usuários cadastrados no sistema
    private List<Investidor> investidores;

    // Usuário atualmente logado
    private Investidor investidorLogado;

    // Mercado ficticio de criptoativos
    private List<Criptoativo> mercado;

    // Services
    private AuthService authService;
    private EmpresaService empresaService;
    private CarteiraService carteiraService;

    public SistemaInvestimentos() {
        scanner = new Scanner(System.in);
        investidores = new ArrayList<>();
        mercado = new ArrayList<>();

        authService = new AuthService();
        empresaService = new EmpresaService();
        carteiraService = new CarteiraService();

        carregarCriptoativos();
        carregarDadosMockados();
    }

    public void iniciar() {
        int opcao;

        do {
            exibirMenuInicial();
            opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1:
                    authService.cadastrarInvestidor(investidores);
                    break;
                case 2:
                    investidorLogado = authService.login(investidores);

                    if (investidorLogado != null) {
                        menuInvestidor();
                    }
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void exibirMenuInicial() {
        System.out.println("\n --- Voltz Investimentos ---");
        System.out.println(
                        "1. Criar conta \n" +
                        "2. Login \n" +
                        "0. Sair "
        );
        System.out.print("Escolha: ");
    }

    private void exibirPainelInvestidor() {
        System.out.println("\n --- Painel do Investidor ---");
        System.out.println(
                "Escolha uma opção: \n" +
                        "1. Cadastrar empresa \n" +
                        "2. Listar empresas \n" +
                        "3. Depositar saldo \n" +
                        "4. Comprar criptoativo \n" +
                        "5. Vender Criptoativo \n" +
                        "6. Visualizar carteira \n" +
                        "7. Visualizar lucro/prejuízo \n" +
                        "8. Visualizar patrimônio total \n" +
                        "0. Logout "
        );
        System.out.print("Escolha: ");
    }

    private void menuInvestidor() {
        int opcao;

        do {
            exibirPainelInvestidor();
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    empresaService.cadastrarEmpresa(investidorLogado);
                    break;
                case 2:
                    empresaService.listarEmpresas(investidorLogado);
                    break;
                case 3:
                    carteiraService.depositarSaldo(investidorLogado);
                    break;
                case 4:
                    carteiraService.comprarCriptoativo(investidorLogado, mercado);
                    break;
                case 5:
                    carteiraService.venderCriptoativo(investidorLogado, mercado);
                    break;
                case 6:
                    carteiraService.vizualizarCarteira(investidorLogado);
                    break;
                case 7:
                    carteiraService.visualizarResultado(investidorLogado);
                    break;
                case 8:
                    carteiraService.visualizarPatrimonioTotal(investidorLogado);
                    break;
                case 0:
                    investidorLogado = authService.logout();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (investidorLogado != null);
    }

    // Mercado ficticio
    private void carregarCriptoativos() {
        Criptoativo bitcoin = new Criptoativo("Bitcoin", "BTC");
        bitcoin.atualizarCotacao(350_000);

        Criptoativo ethereum = new Criptoativo("Ethereum", "ETH");
        ethereum.atualizarCotacao(180_00);

        Criptoativo solana = new Criptoativo("Solana", "SOL");
        solana.atualizarCotacao(750);

        mercado.add(bitcoin);
        mercado.add(ethereum);
        mercado.add(solana);
    }

    // Busca ativo por simbolo
    private Criptoativo buscarCriptoativo(String simbolo) {
        return mercado.stream().filter(item -> item.getSimbolo().equals(simbolo)).findFirst().orElse(null);
    }

    // Facilitar a vida de quem quer testar
    private void carregarDadosMockados() {
        double TAXA = 0.02; //2%
        Investidor investidor = new Investidor(
                "Tio Patinhas",
                "patinhas@voltz.com",
                "123445678900",
                "123"
        );

        Empresa mineradora = new Empresa("Patinhas Mineração LTDA", "11.111.111/0001-11");
        Empresa holding = new Empresa("Patinhas Holding SA", "22.222.222/0002-22");

        investidor.adicionarEmpresa(mineradora);
        investidor.adicionarEmpresa(holding);

        mineradora.getCarteira().registrarMovimentacao(TipoMovimentacao.DEPOSITO, 1_000_000);

        holding.getCarteira().registrarMovimentacao(TipoMovimentacao.DEPOSITO, 500_000);

        Criptoativo bitcoin = buscarCriptoativo("BTC");
        Criptoativo ethereum = buscarCriptoativo("ETH");
        Criptoativo solana = buscarCriptoativo("SOL");

        // Movimentações - Mineradora
        // Compras
        Ordem ordemBtcMineradora = new Ordem(TipoOperacao.COMPRA, 1.5, bitcoin.getValorAtual(), bitcoin);
        Transacao transacaoBtcMineradora = ordemBtcMineradora.executarOrdem(TAXA);
        mineradora.getCarteira().registrarTransacao(transacaoBtcMineradora);

        Ordem ordemEthMineradora = new Ordem(TipoOperacao.COMPRA, 10, ethereum.getValorAtual(), ethereum);
        Transacao transacaoEthMineradora = ordemEthMineradora.executarOrdem(TAXA);
        mineradora.getCarteira().registrarTransacao(transacaoEthMineradora);

        // Vendas
        Ordem ordemVendaBtcMineradora = new Ordem(TipoOperacao.VENDA, 0.2, bitcoin.getValorAtual(), bitcoin);
        Transacao transacaoVendaBtcMineradora = ordemVendaBtcMineradora.executarOrdem(TAXA);
        mineradora.getCarteira().registrarTransacao(transacaoVendaBtcMineradora);

        // Movimentações - Holding
        // Compras
        Ordem ordemSolHolding = new Ordem(TipoOperacao.COMPRA, 100, solana.getValorAtual(), solana);
        Transacao transacaoSolHolding = ordemSolHolding.executarOrdem(TAXA);
        holding.getCarteira().registrarTransacao(transacaoSolHolding);

        investidores.add(investidor);
    }
}
