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

    public double totalDeDesconto() {
        return 0;
    }

    public double calcularAdiantamento13(final double salarioBruto) {
        return salarioBruto / 2;
    }
}
