package entities;

import Interfaces.Operacao;

public class Adicao implements Operacao {
    // Classe para a soma de 2 valores numericos
    @Override
    public double executar(double valor1, double valor2) {
        return valor1 + valor2;
    }
}
