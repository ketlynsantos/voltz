package view;

import enums.TipoOperacao;
import model.*;

import java.util.List;
import java.util.Scanner;

public class CarteiraService {
    private Scanner scanner;
    private EmpresaService empresaService;

    public CarteiraService() {
        scanner = new Scanner(System.in);
        empresaService = new EmpresaService();
    }

    public void comprarCriptoativo(Investidor investidorLogado, List<Criptoativo> mercado) {
        Empresa empresa = empresaService.selecionarEmpresa(investidorLogado);

        if (empresa == null) { return; }

        Criptoativo ativo = selecionarCriptoativo(mercado);

        if (ativo == null) { return ; }

        System.out.print("Quantidade: ");
        double quantidade = scanner.nextDouble();

        System.out.print("Taxa: ");
        double taxa = scanner.nextDouble();

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

        System.out.print("Quantidade: ");
        double quantidade = scanner.nextDouble();

        System.out.print("Taxa: ");
        double taxa = scanner.nextDouble();

        Ordem ordem = new Ordem(
                TipoOperacao.VENDA,
                quantidade,
                ativo.getValorAtual(),
                ativo
        );

        Transacao transacao = ordem.executarOrdem(taxa);
        empresa.getCarteira().registrarTransacao(transacao);

        System.out.println("Venda realizada com sucesso!");
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
            System.out.println(posicao.getCriptoativo().getNome() + " | Quantidade: " + posicao.getQuantidadeAtivo() + " | Valor Atual: R$ " + posicao.getValorAtual());
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

            System.out.println((i + 1) + " - " + ativo.getNome() + " (" + ativo.getSimbolo() + ") | R$ " + ativo.getValorAtual());
        }

        System.out.print("Escolha o ativo: ");
        int opcao = scanner.nextInt();

        if (opcao < 1 || opcao > mercado.size()) {
            System.out.println("Ativo inválido.");
            return null;
        }

        return mercado.get(opcao - 1);
    }
}
