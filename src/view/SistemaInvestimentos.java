package view;

import model.Criptoativo;
import model.Empresa;
import model.Investidor;

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

    public SistemaInvestimentos() {
        scanner = new Scanner(System.in);
        investidores = new ArrayList<>();
        mercado = new ArrayList<>();

        carregarCriptoativos();
    }

    public void iniciar() {
        int opcao;

        do {
            exibirMenuInicial();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarInvestidor();
                    break;
                case 2:
                    login();
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
                        "3. Comprar criptoativo \n" +
                        "4. Vender Criptoativo \n" +
                        "5. Visualizar carteira \n" +
                        "6. Visualizar lucro/prejuízo \n" +
                        "7. Visualizar patrimônio total \n" +
                        "0. Logout "
        );
        System.out.print("Escolha: ");
    }

    private void menuInvestidor() {
        int opcao;

        do {
            exibirPainelInvestidor();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarEmpresa();
                    break;
                case 2:
                    listarEmpresas();
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
                    logout();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (investidorLogado != null);
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

        Investidor novoInvestidor = new Investidor(nome, email, cpf, senha);
        this.investidores.add(novoInvestidor);
        this.investidorLogado = novoInvestidor;
        System.out.println("Conta criada com sucesso!");
    }

    private void login() {
        scanner.nextLine();
        System.out.println("\n --- Login ---");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Investidor investidor : investidores) {
            System.out.println("passa aqui" + investidor);
            boolean emailValido = investidor.getEmail().equals(email);
            boolean senhaValida = investidor.getSenhaHash().equals(senha);

            if (emailValido && senhaValida) {
                this.investidorLogado = investidor;

                System.out.println("\nBem vindo, " + investidorLogado.getNome() + "!");

                menuInvestidor();
                return;
            }
        }

        System.out.println("Email ou senha inválidos.");
    }

    private void logout() {
        this.investidorLogado = null;

        System.out.println("Logout realizado.");
    }

    private void cadastrarEmpresa() {
        if (this.investidorLogado == null) {
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
        this.investidorLogado.adicionarEmpresa(empresa);

        System.out.println("Empresa cadastrada com sucesso!");
    }

    private void listarEmpresas() {
        List<Empresa> empresas = this.investidorLogado.getEmpresas();

        if (empresas.isEmpty()) {
            System.out.println("Nenhuma empresa cadastrada.");
            return;
        }

        for (int i = 0; i < empresas.size(); i++) {
            Empresa empresa = empresas.get(i);

            System.out.println((i + 1) + " - " + empresa.getRazaoSocial());
        }
    }
}
