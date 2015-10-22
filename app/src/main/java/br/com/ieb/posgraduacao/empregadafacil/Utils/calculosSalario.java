package br.com.ieb.posgraduacao.empregadafacil.Utils;

public class calculosSalario {
    //Constantes
    public static char EMPREGADO = 'E';
    public static char EMPREGADOR = 'P';

    //Variáveis

    //Métodos

    //Verifica o valor do INSS do empregado ou do patrao
    public float calcularINSS(char pagante, float salarioBruto){

        if(pagante == EMPREGADO){

            if(salarioBruto < 1399.12f){
                return salarioBruto*0.8f;
            }else{
                if(salarioBruto < 2331.88f){
                    return salarioBruto*0.9f;
                }else{
                    return salarioBruto*0.11f;
                }
            }

        }

        if (pagante == EMPREGADOR){
            return salarioBruto*0.12f;
        }

        return 0f;
    }
}
