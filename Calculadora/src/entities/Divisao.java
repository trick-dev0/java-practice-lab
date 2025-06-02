package entities;

import Interfaces.Operacao;


public class Divisao implements Operacao {

    @Override
    public double executar(double valor1, double valor2) {
        return valor1 / valor2;
    }
}
