package br.com.iesb.posgraduacao.util;

import java.text.DecimalFormat;
public class CalculoSalario {
    //Constantes
    public static char EMPREGADO = 'E';
    public static char EMPREGADOR = 'P';
    public static char FIMDESEMANA = 'F';
    public static char DURANTEASEMANA = 'S';

    //Modificado para , ou inves de . pois apesar de rodar no emulador, não rodava no celular real.
    //Ver em: http://pt.stackoverflow.com/questions/20729/clique-em-bot%C3%A3o-funciona-no-emulador-mas-n%C3%A3o-funciona-no-celular
    DecimalFormat df = new DecimalFormat("0,00");

    //Variáveis

    //Métodos
    //Verifica o valor do INSS do empregado ou do patrao
    public String calcularINSS(char pagante, float salarioBruto) {

        float valorINSS = 0.0f;

        if (pagante == EMPREGADO) {

            if (salarioBruto < 1399.12f) {
                valorINSS = salarioBruto * 0.08f;
            } else {
                if (salarioBruto < 2331.88f) {
                    valorINSS = salarioBruto * 0.09f;
                } else {
                    valorINSS = salarioBruto * 0.11f;
                }
            }

        }

        if (pagante == EMPREGADOR) {
            valorINSS = salarioBruto * 0.12f;
        }

        return df.format(valorINSS);
    }

    //Calculo o valor do FGTS que deve ser pago pelo Empregador
    public String calcularFGTS(float salarioBruto) {
        return df.format(salarioBruto * 0.08f);
    }

    //Calculo o valor do Desconto que pode ser feito no salário do empregado para o vale transporte
    public String calcularDescontoValeTransporte(float salarioBruto) {
        return df.format(salarioBruto * 0.06f);
    }

    //Calculo do valor a ser pago pelas horas extras totais de um empregado
    public String calcularValorTotalHoraExtra(float salarioBruto, int totalHoraExtra, char diaSemana) {
        float valorTotalHoraExtra = 0.0f;

        valorTotalHoraExtra = valorTotalHoraExtra / 220;
        valorTotalHoraExtra = valorTotalHoraExtra * totalHoraExtra;

        switch (diaSemana) {
            case 'S':
                valorTotalHoraExtra = valorTotalHoraExtra * 1.5f;
                break;

            case 'F':
                valorTotalHoraExtra = valorTotalHoraExtra * 2;
                break;
        }

        return df.format(valorTotalHoraExtra);
    }

    public String calcularSalarioLiquido(float salariobruto) {

        float valorSalarioLiquido;

        valorSalarioLiquido = salariobruto - Float.parseFloat(calcularINSS(EMPREGADO, salariobruto));
        valorSalarioLiquido -= Float.parseFloat(calcularDescontoValeTransporte(salariobruto));

        return df.format(valorSalarioLiquido);
    }

    //Calculo da primeira parcela do 13
    public String calcularPrimeiraParcela(float salarioBruto) {
        return df.format(salarioBruto/2);
    }

    //Calculo da segunda parcela do 13
    public String calcularSegundaParcela(float salarioBruto) {
        return df.format((salarioBruto/2) - Float.parseFloat(calcularINSS(EMPREGADO, salarioBruto)));
    }

    public String calcularSalarioBruto(float salarioBruto) {
        return df.format(salarioBruto);
    }

}
