package view;

import model.Investidor;

import java.util.List;
import java.util.Scanner;

public class AuthService {
    private Scanner scanner;

    public AuthService() {
        scanner = new Scanner(System.in);
    }

    public Investidor cadastrarInvestidor(List<Investidor> investidores) {
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
        investidores.add(novoInvestidor);
        System.out.println("\nConta criada com sucesso!");
        return novoInvestidor;
    }

    public Investidor login(List<Investidor> investidores) {
        System.out.println("\n --- Login ---");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Investidor investidor : investidores) {
            boolean emailValido = investidor.getEmail().equals(email);
            boolean senhaValida = investidor.getSenhaHash().equals(senha);

            if (emailValido && senhaValida) {
                System.out.println("\nBem vindo, " + investidor.getNome() + "!");

                return investidor;
            }
        }

        System.out.println("Email ou senha inválidos.");
        return null;
    }

    public Investidor logout() {
        System.out.println("Logout realizado.");
        return null;
    }
}
