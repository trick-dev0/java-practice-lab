package entities;

import Interfaces.Operacao;


public class Multiplicacao implements Operacao {

    @Override
    public double executar(double valor1, double valor2) {
        return valor1 * valor2;
    }
}
