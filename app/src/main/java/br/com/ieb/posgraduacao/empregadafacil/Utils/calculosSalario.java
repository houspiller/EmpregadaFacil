package br.com.ieb.posgraduacao.empregadafacil.Utils;

public class calculosSalario {
    //Constantes
    public static char EMPREGADO = 'E';
    public static char EMPREGADOR = 'P';
    public static char FIMDESEMANA = 'F';
    public static char DURANTEASEMANA = 'S';


    //Variáveis

    //Métodos
    //Verifica o valor do INSS do empregado ou do patrao
    public float calcularINSS(char pagante, float salarioBruto){

        float valorINSS = 0.0f;

        if(pagante == EMPREGADO){

            if(salarioBruto < 1399.12f){
                valorINSS = salarioBruto*0.08f;
            }else{
                if(salarioBruto < 2331.88f){
                    valorINSS = salarioBruto*0.09f;
                }else{
                    valorINSS = salarioBruto*0.11f;
                }
            }

        }

        if (pagante == EMPREGADOR){
            valorINSS = salarioBruto*0.12f;
        }

        return valorINSS;
    }


    //Calculo o valor do FGTS que deve ser pago pelo Empregador
    public float calcularFGTS(float salarioBruto){
        return salarioBruto*0.08f;
    }

    //Calculo o valor do Desconto que pode ser feito no salário do empregado para o vale transporte
    public float calcularDescontoValeTransporte(float salarioBruto){
        return salarioBruto*0.06f;
    }

    //Calculo do valor a ser pago pelas horas extras totais de um empregado
    public float calcularValorTotalHoraExtra(float salarioBruto, int totalHoraExtra, char diaSemana){
        float valorTotalHoraExtra = 0.0f;

        valorTotalHoraExtra = valorTotalHoraExtra/220;
        valorTotalHoraExtra = valorTotalHoraExtra*totalHoraExtra;

        switch (diaSemana){
            case 'S':
                valorTotalHoraExtra = valorTotalHoraExtra*1.5f;
                break;

            case 'F':
                valorTotalHoraExtra = valorTotalHoraExtra*2;
                break;
        }

        return valorTotalHoraExtra;

    }
}
