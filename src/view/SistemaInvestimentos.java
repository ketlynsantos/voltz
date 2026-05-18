package view;

import model.Criptoativo;
import model.Investidor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaInvestimentos {
    private Scanner scanner;
    private Investidor investidor;
    private List<Criptoativo> mercado;

    public SistemaInvestimentos() {
        scanner = new Scanner(System.in);
        mercado = new ArrayList<>();

        carregarCriptoativos();
    }

    public void iniciar() {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\n --- Menu ---");
        System.out.println(
                "Escolha uma opção: \n" +
                        "1. Cadastrar investidor \n" +
                        "2. Cadastrar empresa \n" +
                        "3. Listar empresas \n" +
                        "4. Executar transação \n" +
                        "5. Agendar Ordem \n" +
                        "6. Visualizar lucro/prejuízo \n" +
                        "7. Visualizar patrimônio total \n" +
                        "0. Sair "
        );
        System.out.print("Escolha: ");
    }

    private void carregarCriptoativos() {
        Criptoativo bitcoin = new Criptoativo("Bitcoin", "BTC");
        bitcoin.atualizarCotacao(350000);

        Criptoativo ethereum = new Criptoativo("Ethereum", "ETH");
        ethereum.atualizarCotacao(18000);

        Criptoativo solana = new Criptoativo("Solana", "SOL");
        solana.atualizarCotacao(750);

        mercado.add(bitcoin);
        mercado.add(ethereum);
        mercado.add(solana);
    }
}
