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

            System.out.println(" ");
            System.out.print("Valor 1: ");
            double valor1 = sc.nextDouble();
            System.out.print("Valor 2: ");
            double valor2 = sc.nextDouble();
            System.out.println(" ");

            switch (opcao) {
                case 1:
                    Adicao operacaoSoma = new Adicao();
                    System.out.println("Resultado: " + operacaoSoma.executar(valor1, valor2));
                    break;
                case 2:
                    Subtracao operacaoSubtracao = new Subtracao();
                    System.out.println("Resultado: " + operacaoSubtracao.executar(valor1, valor2));
                    break;
                case 3:
                    Divisao operacaoDivisao = new Divisao();
                    System.out.println("Resultado: " + operacaoDivisao.executar(valor1, valor2));
                    break;
                case 4:
                    Multiplicacao operacaoMultiplicacao = new Multiplicacao();
                    System.out.println("Resultado: " + operacaoMultiplicacao.executar(valor1, valor2));
                    break;
                case 5:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Essa opção não existe, digite uma opção válida!");
                    break;
            }

        } while (opcao != 5);
        sc.close();
    }
}