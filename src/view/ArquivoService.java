package view;

import model.Empresa;
import model.Investidor;
import model.Posicao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoService {
    public void salvarInvestidor(Investidor investidor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("investidor_logado.txt"))) {
            writer.write("--- DADOS DO INVESTIDOR ---");
            writer.newLine();

            writer.write("Nome: " + investidor.getNome());
            writer.newLine();

            writer.write("Email: " + investidor.getEmail());
            writer.newLine();

            writer.write("CPF" + investidor.getCpf());
            writer.newLine();

            writer.newLine();

            writer.write("--- Empresas ---");
            writer.newLine();

            for (Empresa empresa : investidor.getEmpresas()) {
                writer.write("Razão Social: " + empresa.getRazaoSocial());
                writer.newLine();

                writer.write("CNPJ: " + empresa.getCnpj());
                writer.newLine();

                writer.write(String.format("Saldo disponível: R$ %.2f", empresa.getCarteira().getSaldoDisponivelFiat()));
                writer.newLine();

                writer.write(String.format("Patrimônio: R$ %.2f", empresa.calcularPatrimonio()));
                writer.newLine();

                writer.write("Posições:");
                writer.newLine();
                writer.newLine();

                for (Posicao posicao : empresa.getCarteira().getPosicoes()) {
                    writer.write(posicao.getCriptoativo().getSimbolo() + " | Quantidade: " + posicao.getQuantidadeAtivo());
                    writer.newLine();
                }
                writer.newLine();
            }
            System.out.println("\nArquivo investidor_logado.txt gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("\nErro ao salvar arquivo.");
        }
    }
}
