import view.SistemaInvestimentos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            SistemaInvestimentos sistema = new SistemaInvestimentos();
            sistema.iniciar();
        } catch (Exception exception) {
            System.out.println("\nErro no sistema: " + exception.getMessage());
        }
    }
}
