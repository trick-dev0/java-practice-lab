package entities;

import Interfaces.Operacao;

public class Subtracao implements Operacao {

    // Classe para a subtarcao de 2 valores numericos
    @Override
    public double executar(double valor1, double valor2) {
        if(valor2 <= 0){
            throw new IllegalArgumentException("Não é permitido subtrair por um número negativo.");
        }
        return valor1 - valor2;
    }
}
