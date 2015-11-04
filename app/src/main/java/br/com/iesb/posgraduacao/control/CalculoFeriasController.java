package br.com.iesb.posgraduacao.control;
/**
 * Created by braiannunes on 11/2/15.
 */
public class CalculoFeriasController {

    public double calcularValorFeriasSemDesconto(double salarioBruto, int qtdDiasFerias) {
        double salarioDiario = salarioBruto / 30;
        return salarioDiario * qtdDiasFerias;
    }

    public double calcular13Ferias(double valorFerias) {
        return valorFerias / 3;
    }

    public double calcularAbonoPecuniario(double salarioBruto, int qtdDiasFerias, final boolean abonoPecuniario) {
        if (abonoPecuniario && qtdDiasFerias <= 20) {
            double salarioDiario = salarioBruto / 30;
            return salarioDiario * 10;
        }
        return 0;
    }

    public double calcularUmTercoAbonoPecuniario(double abonoPecuniario) {
        if (abonoPecuniario > 0) {
            return abonoPecuniario / 3;
        }
        return 0;
    }

    public double calcularDeducaoPorDependente(int qtdDependente) {
        return 0;
    }

    public double calcularImpostoDeRenda(double salarioBruto) {
        return 0;
    }

    public double calcularAdiantamento13(final double salarioBruto) {
        return salarioBruto / 2;
    }

    public double calcularDeducaoINSS(final double salarioBruto, final double valorFerias, final double _13Ferias) {
        double aliquota = obterAliquotaINSS(salarioBruto);
        double totalFerias = valorFerias + _13Ferias;
        return totalFerias * aliquota;
    }

    private double obterAliquotaINSS(double salarioBruto) {
        if (salarioBruto <= 1399.12) {
            return 0.08;

        } else if (salarioBruto > 1299.12 && salarioBruto <= 2331.88) {
            return 0.09;

        } else if (salarioBruto > 2331.88 && salarioBruto <= 4663.75) {
            return 0.11;
        }

        return 0.11;
    }

    /*
    *
    * Salário de Contribuição (R$)	Alíquota (%)
        Até 1.399,12	8
        De 1.399,13 até 2.331,88	9
        De 2.331,89 até 4.663,75	11
    * */

}
