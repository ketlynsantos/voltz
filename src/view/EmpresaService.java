package view;

import model.Empresa;
import model.Investidor;

import java.util.List;
import java.util.Scanner;

public class EmpresaService {
    private Scanner scanner;

    public EmpresaService() {
        scanner = new Scanner(System.in);
    }

    public void cadastrarEmpresa(Investidor investidorLogado) {
        if (investidorLogado == null) {
            System.out.println("Cadastre um investidor primeiro!");
            return;
        }

        System.out.println("\n --- Cadastro de Empresa ---");

        System.out.print("Razão social: ");
        String razaoSocial = scanner.nextLine();

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        Empresa empresa = new Empresa(razaoSocial, cnpj);
        investidorLogado.adicionarEmpresa(empresa);

        System.out.println("Empresa cadastrada com sucesso!");
    }

    public void listarEmpresas(Investidor investidorLogado) {
        List<Empresa> empresas = investidorLogado.getEmpresas();

        if (empresas.isEmpty()) {
            System.out.println("Nenhuma empresa cadastrada.");
            return;
        }

        for (int i = 0; i < empresas.size(); i++) {
            Empresa empresa = empresas.get(i);

            System.out.println((i + 1) + " - " + empresa.getRazaoSocial());
        }
    }

    public Empresa selecionarEmpresa(Investidor investidorLogado) {
        List<Empresa> empresas = investidorLogado.getEmpresas();

        if (empresas.isEmpty()) {
            System.out.println("Nenhuma empresa cadastrada.");
            return null;
        }

        System.out.println("\n --- Empresas ---");

        for (int i = 0; i < empresas.size(); i++) {
            Empresa empresa = empresas.get(i);

            System.out.println((i + 1) + " - " + empresa.getRazaoSocial());
        }

        System.out.print("Escolha a empresa: ");
        int opcao = scanner.nextInt();

        if (opcao < 1 || opcao > empresas.size()) {
            System.out.println("Empresa inválida.");
            return null;
        }

        return empresas.get(opcao - 1);
    }
}
