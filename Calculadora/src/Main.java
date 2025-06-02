import entities.Adicao;
import entities.Divisao;
import entities.Multiplicacao;
import entities.Subtracao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String corpoDeOpcoes = ("""
                1 - Adição (+)\s
                2 - Subtração (-)\s
                3 - Divisão (/)\s
                4 - Multiplicação (*)\s
                5 - Sair\s
                >\s""");

        System.out.println("Bem vindo ao programa! \nQual a operação que deseja realizar?");
        System.out.print(corpoDeOpcoes);

        int opcao = 0;

        do {
            opcao = sc.nextInt();

            // Caso o cliente digite 5, ja encerra o programa
            if (opcao == 5) {
                System.out.println("Encerrando programa...");
                break;
            }

            // Caso digite um valor fora das opções
            if (opcao <= 0 ||  opcao >= 5) {
                System.out.println("Essa opção não existe, digite uma opção válida!");
                System.out.print(corpoDeOpcoes);
                System.out.println("");
                continue;
            }

            System.out.println(" ");
            System.out.print("Valor 1: ");
            double valor1 = sc.nextDouble();
            System.out.print("Valor 2: ");
            double valor2 = sc.nextDouble();
            System.out.println(" ");

            switch (opcao) {
                case 1:
                    Adicao add = new Adicao();
                    System.out.println("Resultado: " + add.executar(valor1, valor2));
                    break;
                case 2:
                    Subtracao sub = new Subtracao();
                    System.out.println("Resultado: " + sub.executar(valor1, valor2));
                    break;
                case 3:
                    Divisao div = new Divisao();
                    System.out.println("Resultado: " + div.executar(valor1, valor2));
                    break;
                case 4:
                    Multiplicacao multi = new Multiplicacao();
                    System.out.println("Resultado: " + multi.executar(valor1, valor2));
                    break;
            }

            System.out.println(" ");
            System.out.print(corpoDeOpcoes);
        } while (true);
        sc.close();
    }
}