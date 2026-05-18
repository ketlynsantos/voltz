package view;

import model.Criptoativo;
import model.Empresa;
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
                    cadastrarInvestidor();
                    break;
                case 2:
                    cadastrarEmpresa();
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

    private void cadastrarInvestidor() {
        scanner.nextLine();
        System.out.println("\n --- Cadastro de Investidor ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        this.investidor = new Investidor(nome, email, cpf, senha);
        System.out.println("Investidor cadastrado com sucesso!");
    }

    private void cadastrarEmpresa() {
        if (investidor == null) {
            System.out.println("Cadastre um investidor primeiro!");
            return;
        }

        scanner.nextLine();
        System.out.println("\n --- Cadastro de Empresa ---");

        System.out.print("Razão social: ");
        String razaoSocial = scanner.nextLine();

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        Empresa empresa = new Empresa(razaoSocial, cnpj);
        investidor.adicionarEmpresa(empresa);

        System.out.println("Empresa cadastrada com sucesso!");
    }
}
